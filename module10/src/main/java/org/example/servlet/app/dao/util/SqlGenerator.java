package org.example.servlet.app.dao.util;

import lombok.experimental.UtilityClass;
import org.example.servlet.app.dao.annotation.Column;
import org.example.servlet.app.dao.annotation.Id;
import org.example.servlet.app.dao.annotation.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class SqlGenerator {
    private static final String SELECT_FROM_TABLE_BY_ID = "SELECT * FROM %s WHERE %s = ?";
    private static final String INSERT_INTO_TABLE = "INSERT INTO %s(%s) VALUES(%s)";
    private static final String UPDATE_TABLE = "UPDATE %s SET %s WHERE %s = ?";
    private static final String DELETE_FROM_TABLE_BY_ID = "DELETE FROM %s WHERE %s = ?";

    public static String createSelectByIdQuery(Class<?> type) {
        String tableName = resolveTableName(type);
        String idColumnName = resolveIdColumnName(type);

        return SELECT_FROM_TABLE_BY_ID.formatted(tableName, idColumnName);
    }

    public static String createInsertQuery(Class<?> type) {
        String tableName = resolveTableName(type);
        List<String> columnNames = getColumnsForInsert(type);
        List<String> columnValuePlaceholders = Collections.nCopies(columnNames.size(), "?");
        return INSERT_INTO_TABLE.formatted(
                tableName,
                String.join(",", columnNames),
                String.join(",", columnValuePlaceholders)
        );
    }

    public static String createUpdateQuery(Class<?> type) {
        String tableName = resolveTableName(type);
        List<String> columnNames = getColumnsForInsert(type);
        List<String> columnValuePairs = new ArrayList<>();
        for (String columnName : columnNames) {
            columnValuePairs.add(columnName + "=?");
        }
        return UPDATE_TABLE.formatted(
                tableName,
                String.join(",", columnValuePairs),
                resolveIdColumnName(type)
        );
    }

    public static String createDeleteQuery(Class<?> type) {
        String tableName = resolveTableName(type);
        String idColumnName = resolveIdColumnName(type);
        return DELETE_FROM_TABLE_BY_ID.formatted(tableName, idColumnName);
    }

    public static String resolveIdColumnName(Class<?> type) {
        return resolveColumnName(getIdField(type));
    }

    public static String resolveColumnName(Field field) {
        return Optional.ofNullable(field.getAnnotation(Column.class))
                .map(Column::name)
                .orElseGet(() -> field.getName().toLowerCase());
    }

    public static List<Field> getInsertableFields(Class<?> type) {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .toList();
    }


    public static Field getIdField(Class<?> type) {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findAny()
                .orElseThrow();
    }

    private static List<String> getColumnsForInsert(Class<?> type) {
        return getInsertableFields(type).stream()
                .map(SqlGenerator::resolveColumnName)
                .toList();
    }

    private static String resolveTableName(Class<?> type) {
        return Optional.ofNullable(type.getAnnotation(Table.class))
                .map(Table::value)
                .orElseGet(() -> type.getSimpleName().toLowerCase());
    }
}
