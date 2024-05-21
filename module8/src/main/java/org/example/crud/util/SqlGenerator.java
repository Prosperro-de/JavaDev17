package org.example.crud.util;

import lombok.experimental.UtilityClass;
import org.example.crud.annotation.Column;
import org.example.crud.annotation.Id;
import org.example.crud.annotation.Table;
import org.example.crud.model.Customer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

@UtilityClass
public class SqlGenerator {
    private static final String SELECT_FROM_TABLE_BY_ID = "SELECT * FROM %s WHERE %s = ?";

    public static String createSelectByIdQuery(Class<?> type) {
        String tableName = resolveTableName(type);
        String idColumnName = resolveIdColumnName(type);

        return SELECT_FROM_TABLE_BY_ID.formatted(tableName, idColumnName);
    }

    public static String resolveColumnName(Field field) {
        return Optional.ofNullable(field.getAnnotation(Column.class))
                .map(Column::name)
                .orElseGet(() -> field.getName().toLowerCase());
    }

    private static String resolveTableName(Class<?> type) {
        return Optional.ofNullable(type.getAnnotation(Table.class))
                .map(Table::value)
                .orElseGet(() -> type.getSimpleName().toLowerCase());
    }

    private static String resolveIdColumnName(Class<?> type) {
        return resolveColumnName(getIdField(type));
    }

    private static Field getIdField(Class<?> type) {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findAny()
                .orElseThrow();
    }
}
