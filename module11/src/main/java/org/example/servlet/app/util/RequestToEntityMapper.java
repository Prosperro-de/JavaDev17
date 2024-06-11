package org.example.servlet.app.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Map;

@UtilityClass
public class RequestToEntityMapper {

    public <T> T mapToEntity(Map<String, String[]> params, Class<T> type) throws Exception{
        T result = type.getConstructor().newInstance();
        for (Field declaredField : type.getDeclaredFields()) {
            if (params.containsKey(declaredField.getName())) {
                declaredField.setAccessible(true);
                declaredField.set(result, params.get(declaredField.getName())[0]);
                declaredField.setAccessible(false);
            }
        }
        return result;
    }
}
