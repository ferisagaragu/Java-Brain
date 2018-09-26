package org.javabrain.annotation;

import org.javabrain.util.data.Json;
import org.omg.CORBA.Object;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FrutoSeco {

    int calorias();

    String tipoArbol() default "albedo";

}
