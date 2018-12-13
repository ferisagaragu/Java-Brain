package org.javabrain.annotation;

import org.javabrain.enums.JsonId;
import org.javabrain.enums.JsonRestriction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Id {
    String name() default "";
    JsonId type() default JsonId.KEY;
    JsonRestriction[]  restriction() default {JsonRestriction.NOT_RESTRICTION};
}
