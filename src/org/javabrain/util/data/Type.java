package org.javabrain.util.data;

public class Type extends Object{

    public int INTEGER;
    public float FLOAT;
    public double DOUBLE;
    public byte BYTE;
    public char CHARACTER;

    public String STRING;
    public Json JSON;
    public Object OBJECT;

    public Object VALUE;
    public Object STATUS;

    public Type(Object value, Object status) {
        this.VALUE = value;
        this.STATUS = status;
    }

    public Type(Object obj) {

        try {
            INTEGER = Integer.parseInt(obj.toString());
        } catch (Exception e){
            INTEGER = 0;
        }

        try {
            FLOAT = Float.parseFloat(obj.toString());
        } catch (Exception e){
            FLOAT = 0;
        }

        try {
            DOUBLE = Double.parseDouble(obj.toString());
        } catch (Exception e){
            DOUBLE = 0;
        }

        try {
            BYTE = Byte.parseByte(obj.toString());
        } catch (Exception e){
            BYTE = 0;
        }

        try {
            CHARACTER = obj.toString().charAt(0);
        } catch (Exception e){
            CHARACTER = 0;
        }

        try {
            STRING = obj.toString();
        } catch (Exception e){
            STRING = "";
        }

        try {
            JSON = new Json(obj);
        } catch (Exception e) {
            JSON = null;
        }

        try {
            OBJECT = obj;
        } catch (Exception e){
            OBJECT = null;
        }
    }

    public static Type parse(Object obj){
        return new Type(obj);
    }
}
