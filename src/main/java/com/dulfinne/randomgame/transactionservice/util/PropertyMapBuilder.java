package com.dulfinne.randomgame.transactionservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyMapBuilder {

  public static Map<String, Object> fromRecord(Object record) {
    RecordComponent[] components = record.getClass()
                                         .getRecordComponents();

    return Arrays.stream(components)
                 .map(component -> {
                   Method method = component.getAccessor();
                   Object value = ReflectionUtils.invokeMethod(method, record);
                   return value != null ? Map.entry(component.getName(), value) : null;
                 })
                 .filter(Objects::nonNull)
                 .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
