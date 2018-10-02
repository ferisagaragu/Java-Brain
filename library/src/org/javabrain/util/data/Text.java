package org.javabrain.util.data;

public class Text {

    public static String upperFirst(String text) {
        String out = "";
        try {
            char charAt = text.charAt(0);
            out = String.valueOf(charAt).toUpperCase() + text.substring(1,text.length());
        } catch (Exception e) {}
        return out;
    }


}
