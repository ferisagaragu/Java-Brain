package org.javabrain.util.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Type extends Object{

    public int INTEGER;
    public float FLOAT;
    public double DOUBLE;
    public byte BYTE;
    public char CHARACTER;
    public boolean BOOLEAN;
    public short SHORT;
    private long LONG;

    public String STRING;
    public Object OBJECT;
    
    public Json JSON;
    
    public Object STATUS;
    
    private Object out;

    public Type(Object value, Object status) {
        getType(value);
        this.STATUS = status;
    }

    public Type(Object obj) {
        getType(obj);
    }

    private void getType(Object o) {
        this.out = o;
        switch (o.getClass().getTypeName()) {
            case "java.lang.Integer": INTEGER = Integer.parseInt(o.toString()); break;
            case "java.lang.Float": FLOAT = Float.parseFloat(o.toString()); break;
            case "java.lang.Double": DOUBLE = Double.parseDouble(o.toString()); break;
            case "java.lang.Byte": BYTE = Byte.parseByte(o.toString()); break;
            case "java.lang.Character": CHARACTER = CHARACTER = (Character) o; break;
            case "java.lang.Boolean": BOOLEAN = Boolean.parseBoolean(o.toString()); break;
            case "java.lang.Short": SHORT = Short.parseShort(o.toString()); break;
            case "java.lang.Long": LONG = Long.parseLong(o.toString()); break;
            case "java.lang.String": 
                if (Json.isJSON(o)) {
                    JSON = Json.parseJson(o.toString());
                } else {
                    STRING = String.valueOf(o); 
                }
            break;
            case "org.javabrain.util.data.Json": JSON = Json.parseJson(o.toString()); break;
            default: OBJECT = o;
        }
    }

    public static String convertType(String type) {

        if (type == null) {
            return "null";
        }
        
        if (type.contains("$") || type.toLowerCase().contains("mnx") || type.toLowerCase().contains("usd")) {
            return "Money";
        }
        
        if (type.contains("/") && type.length() == 10) {
            return "Date";
        }
        
        try {
            if (!type.contains(".")) {
                Integer.parseInt(type);
                return "Integer";
            }
        } catch (Exception e) {}
        
        try {
            Double.parseDouble(type);
            return "Double";
        } catch (Exception e) {}

        if (type.length() == 1) {
            return "Character";
        }
        
        if (Json.isJSONObject(type)) {
            return "JSON Object";
        }
        
        if (Json.isJSONArray(type)) {
            return "JSON Array";
        }
        
        if (type.contains("[") && type.contains("]") && !(type.contains("{\""))) {
            return "Array";
        }
        
        if (Type.isUrl(type)) {
            return "Url";
        }
        
        return "String";
    }
    
    public static boolean isUrl(String s) {
        String regex = "^(https?://)?(([\\w!~*'().&=+$%-]+: )?[\\w!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([\\w!~*'()-]+\\.)*([\\w^-][\\w-]{0,61})?[\\w]\\.[a-z]{2,6})(:[0-9]{1,4})?((/*)|(/+[\\w!~*'().;?:@&=+$,%#-]+)+/*)$";

        try {
            Pattern patt = Pattern.compile(regex);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return out.toString();
    }
    
    public static Type parse(Object obj){
        return new Type(obj);
    }
}
