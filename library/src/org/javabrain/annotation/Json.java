package org.javabrain.annotation;

import org.javabrain.enums.JsonDataType;
import org.javabrain.enums.JsonType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Json {
    String load() default "";
    JsonType type();
    JsonDataType dataType() default JsonDataType.JSON_ARRAY;
}
