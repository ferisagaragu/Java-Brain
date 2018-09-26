package org.javabrain.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Alimento {

    @FrutoSeco(calorias = 33,tipoArbol = "")
    private String alimento;

    @FrutoSeco(calorias = 14,tipoArbol = "")
    private int numero;


    public Alimento() {
        Class<?> aliClass = this.getClass();
        Field[] field = aliClass.getDeclaredFields();

        for (Field fld:field) {
            System.out.print(fld.getName() + ":");
            Annotation annotation = fld.getAnnotation(FrutoSeco.class);
            if (annotation != null && annotation instanceof FrutoSeco) {
                FrutoSeco frutoSeco = (FrutoSeco) annotation;
                System.out.println(frutoSeco.calorias());
            }
        }
    }
}
