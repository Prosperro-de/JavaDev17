package org.example.context;

import lombok.SneakyThrows;
import org.example.annotation.Autowired;
import org.example.annotation.Component;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ApplicationContextImpl implements ApplicationContext {
    private Map<String, Object> contex = new HashMap<>();

    public ApplicationContextImpl(String path) {
        Reflections reflections = new Reflections(path);
        reflections.getTypesAnnotatedWith(Component.class).forEach(this::insertInContext);
        autowirePostProcessing();
    }

    @Override
    public <T> T getBean(Class<T> type) {
        Map<String, T> allBeans = getAllBeans(type);
        if (allBeans.size() > 1) {
            throw new RuntimeException("Multiple beans for the type found : " + type.getSimpleName());
        }
        return allBeans.values().stream().findAny().orElseThrow();
    }

    @Override
    public <T> T getBean(Class<T> type, String name) {
        return type.cast(contex.get(name));
    }

    @Override
    public <T> Map<String, T> getAllBeans(Class<T> type) {
//        GenericDao g = new CustomerDao()

//        CustomerDao
//        OrderDao
        return contex.entrySet().stream()
                .filter(entry -> type.isAssignableFrom(entry.getValue().getClass()))
                .collect(toMap(
                        Map.Entry::getKey, e -> type.cast(e.getValue()))
                );
    }

    @SneakyThrows
    private void insertInContext(Class<?> type) {
        String beanName = resolveBeanName(type);
        Object bean = type.getConstructor().newInstance();
        contex.put(beanName, bean);
    }

    private String resolveBeanName(Class<?> type) {
        String providedName = type.getAnnotation(Component.class).name();
        return providedName.isEmpty() ? type.getSimpleName() : providedName;
    }

    @SneakyThrows
    private void autowirePostProcessing() {
        for (var entry : contex.entrySet()) {
            Object bean = entry.getValue();
            Class<?> beanType = bean.getClass();
            for (Field field : beanType.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object dependencyObject = getBean(field.getType());
                    field.setAccessible(true);
                    field.set(bean, dependencyObject);
                    field.setAccessible(false);
                }
            }
        }
    }
}
