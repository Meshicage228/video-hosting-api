package ru.clevertec.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class FieldSetterService {
    public <T> void setFields(T entity, Map<Object, Object> fieldValueMap) {
        fieldValueMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(entity.getClass(), key.toString());
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
                field.setAccessible(false);
            }
        });
    }
}
