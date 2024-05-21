package org.example.crud.dao;

import lombok.Data;
import org.example.crud.config.DataSource;
import org.example.crud.model.Customer;
import org.example.crud.model.Product;
import org.example.crud.util.SqlGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
