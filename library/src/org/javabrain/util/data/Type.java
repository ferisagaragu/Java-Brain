package org.javabrain.util.data;

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

    @Override
    public String toString() {
        return out.toString();
    }
    
    public static Type parse(Object obj){
        return new Type(obj);
    }
}
