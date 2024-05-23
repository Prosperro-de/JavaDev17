package org.example.crud.dao;

import lombok.Data;
import org.example.crud.annotation.Id;
import org.example.crud.config.DataSource;
import org.example.crud.model.Customer;
import org.example.crud.model.Product;
import org.example.crud.util.SqlGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.example.crud.util.SqlGenerator.getIdField;
import static org.example.crud.util.SqlGenerator.resolveColumnName;

@Data
public class GenericDao {
    private Connection connection = DataSource.getInstance().getConnection();

    public GenericDao() throws SQLException {
    }

    public <T> T findById(Long id, Class<T> type) {
        String selectByIdQuery = SqlGenerator.createSelectByIdQuery(type);
        try (PreparedStatement statement = connection.prepareStatement(selectByIdQuery)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return mapResultSetToObject(resultSet, type);
            }
        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public  <T> void save(T object) throws SQLException {
        String insertQuery = SqlGenerator.createInsertQuery(object.getClass());
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            setFieldValuesInPreparedStatement(preparedStatement, object);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                Field idField = SqlGenerator.getIdField(object.getClass());
                idField.setAccessible(true);
                idField.set(object, generatedKeys.getObject(1, Long.class));
                idField.setAccessible(false);
            }
            connection.commit();
        } catch (Exception ex) {
            connection.rollback();
            throw new RuntimeException(ex);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public <T> void update(T object) throws SQLException {
        String updateQuery = SqlGenerator.createUpdateQuery(object.getClass());
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            connection.setAutoCommit(false);
            setFieldValuesInPreparedStatement(preparedStatement, object);
            List<Field> insertableFields = SqlGenerator.getInsertableFields(object.getClass());
            Field idField = getIdField(object.getClass());
            idField.setAccessible(true);
            preparedStatement.setObject(insertableFields.size() + 1, idField.get(object));
            idField.setAccessible(false);

            int updatedRow = preparedStatement.executeUpdate();
            if (updatedRow != 1) {
                throw new RuntimeException("Failed to update object");
            }
            connection.commit();
        } catch (Exception ex) {
            connection.rollback();
            throw new RuntimeException(ex);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public <T> void delete(T object) throws SQLException {
        String deleteQuery = SqlGenerator.createDeleteQuery(object.getClass());
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            connection.setAutoCommit(false);
            Field idField = getIdField(object.getClass());
            idField.setAccessible(true);
            preparedStatement.setObject(1, idField.get(object));
            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows != 1) {
                throw new RuntimeException("Failed to delete object");
            }
            connection.commit();
        } catch (Exception ex) {
            connection.rollback();
            throw new RuntimeException(ex);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private <T> void setFieldValuesInPreparedStatement(PreparedStatement preparedStatement, T object) throws IllegalAccessException, SQLException {
        List<Field> fields = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .toList();
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            field.setAccessible(true);
            preparedStatement.setObject(i + 1, field.get(object));
            field.setAccessible(false);
        }
    }


    private <T> T mapResultSetToObject(ResultSet resultSet, Class<T> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T emptyObject = type.getConstructor().newInstance();
        try {
            for (Field declaredField : type.getDeclaredFields()) {
                declaredField.setAccessible(true);
                declaredField.set(emptyObject,
                        resultSet.getObject(resolveColumnName(declaredField)));
                declaredField.setAccessible(false);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return emptyObject;
    }


}
