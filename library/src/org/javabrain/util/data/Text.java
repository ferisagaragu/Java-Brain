package org.javabrain.util.data;

public class Text {

    public static String upperFirst(String text) {
        char charAt = text.charAt(0);
        String out = String.valueOf(charAt).toUpperCase() + text.substring(1,text.length());
        return out;
    }


}
