package org.javabrain.annotation.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * @author Fernando García
 * @version 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Json {
    String path() default "";
    String get() default "";
    String post() default "";
    String put() default "";
    String delete() default "";
}
