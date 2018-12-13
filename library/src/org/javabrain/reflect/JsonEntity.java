package org.javabrain.reflect;

import org.javabrain.annotation.*;
import org.javabrain.enums.JsonDataType;
import org.javabrain.enums.JsonRestriction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class JsonEntity {

    public static List inject(Class clazz) {
        return recursiveInject(clazz);
    }

    public static Object simpleInject(Class clazz) {
        return recursiveInject(clazz).get(0);
    }

    public static String stringify(Object o) {
        return recursiveStringify(o,"");
    }

    public static boolean merge(Object o) {

        Object finalObj = o;
        if (o.getClass().getName().equals("java.util.ArrayList")){
            List list = (ArrayList) o;
            o = list.get(0);
        }

        Json json = (Json) o.getClass().getAnnotation(Json.class);

        if (json != null) {
            switch (json.type()) {
                case EXTERNAL:

                    if (isRestrictionsCorrect(finalObj)) {
                        String path = json.load().replace(".json", "*json").replace(".", "/").replace("{", "").replace("}", "").replace("*", ".");
                        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF8"))) {
                            out.append(stringify(finalObj));
                            out.flush();
                        } catch (IOException ioe) {
                            System.err.println(ioe.getMessage());
                        }
                    }

                    return true;

                case INTERNAL:
                    System.err.println("It can not be merged with a file loaded from the internal resources of the project.");
                    break;
            }
        } else {
            System.err.println("The Json annotation is not found in the entity");
        }
        return false;
    }




    //==========================================PRIVATE METHODS=====================================================

    //HERE USE A RECURSIVE METHODS AFTER TO USE FOR THE FINAL DEVELOP
    private static List recursiveInject(Class clazz, Object... objects) {

        List list = new ArrayList();
        Json json = (Json) clazz.getAnnotation(Json.class);

        switch (json.type()) {
            case INTERNAL:

                switch (json.dataType()) {
                    case JSON_ARRAY:
                        JSONArray array = getJsonArrayData(json.load());
                        putJsonArrayInObject(clazz, array, list);
                        break;
                    case JSON_OBJECT:
                        JSONObject object = getJsonObjectData(json.load());
                        putJsonObjectInObject(clazz, object, list);
                        break;
                }

                break;

            case EXTERNAL:

                switch (json.dataType()) {
                    case JSON_ARRAY:
                        JSONArray array = getExternalJsonArrayData(json.load());
                        putJsonArrayInObject(clazz, array, list);
                        break;
                    case JSON_OBJECT:
                        JSONObject object = getExternalJsonObjectData(json.load());
                        putJsonObjectInObject(clazz, object, list);
                        break;
                }

                break;

            /*case DATA_BASE:
                break;
            case WEB_SERVICE:
                break;

            case OBJECT:
                break;*/

            case NESTED:

                try {
                    JSONParser parser = new JSONParser();

                    switch (json.dataType()) {
                        case JSON_ARRAY:
                            JSONArray array = (JSONArray) parser.parse(objects[0].toString());
                            putJsonArrayInObject(clazz, array, list);
                            break;

                        case JSON_OBJECT:
                            JSONObject object = (JSONObject) parser.parse(objects[0].toString());
                            putJsonObjectInObject(clazz, object, list);
                            break;
                    }

                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }

                break;
        }


        return list;
    }

    private static String recursiveStringify(Object o, String data) {

        String out = "";
        try {
            if (o.getClass().getName().equals("java.util.ArrayList")) {

                ArrayList list = (ArrayList) o;
                List list1 = new ArrayList();

                for (Object o1 : list) {
                    Map<Object,Object> map = new LinkedHashMap<>();
                    for(Field field : o1.getClass().getDeclaredFields()) {
                        putStringifyData(field,map,o1,out);
                    }
                    list1.add(map.toString().replace("=",":"));
                }

                return list1.toString();
            } else {

                Map<Object,Object> map = new LinkedHashMap<>();

                for (Field field : o.getClass().getDeclaredFields()) {

                    field.setAccessible(true);

                    if (!field.getType().getName().equals("java.util.List")) {
                        putStringifyData(field,map,o,out);
                    } else {
                        Key key = (Key) field.getAnnotation(Key.class);
                        map.put("\""+key.name()+"\"", recursiveStringify(field.get(o),out));
                    }

                }

                out += map.toString().replace("=",":");
            }
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        }

        return formatJSONString(out);
    }
    //=======================================




    //HERE PUT THE FORMAT ON THE INJECTED CLASS
    private static String formatJSONString(String str) {
        StringBuilder r = new StringBuilder();
        formatJSON(JSONValue.parse(str), r, "");
        return r.toString();
    }

    private static void formatJSON(Object obj, StringBuilder r,String indent) {
        if (obj == null) {
            r.append("null");
        } else if (obj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map map = (Map) obj;
            Object[] keys = map.keySet().toArray();
            Arrays.sort(keys);
            r.append("{\n");
            String indentTab = indent + "  ";
            for (int i = 0; i < keys.length; i++) {
                if (i > 0) {
                    r.append(",\n");
                }
                r.append(indentTab);
                r.append(JSONValue.toJSONString(keys[i]));
                r.append(": ");
                formatJSON(map.get(keys[i]), r, indentTab);
            }
            if (keys.length > 0) {
                r.append("\n");
            }
            r.append(indent);
            r.append("}");
        } else if (obj instanceof List) {
            @SuppressWarnings("unchecked")
            Iterator it = ((List) obj).iterator();
            r.append("[\n");
            if (it.hasNext()) {
                String indentTab = indent + "  ";
                r.append(indentTab);
                formatJSON(it.next(), r, indentTab);
                while (it.hasNext()) {
                    r.append(",\n");
                    r.append(indentTab);
                    formatJSON(it.next(), r, indentTab);
                }
                r.append("\n");
            }
            r.append(indent);
            r.append("]");
        } else {
            r.append(JSONValue.toJSONString(obj));
        }
    }

    private static void putStringifyData(Field field,Map map,Object o,String out) throws IllegalAccessException {

        field.setAccessible(true);
        Key key = (Key) field.getAnnotation(Key.class);
        Inject inject = (Inject) field.getAnnotation(Inject.class);

        if (key != null && inject == null) {
            map.put("\""+key.name()+"\"","\""+field.get(o)+"\"");
        } else {
            if (key != null) {
                map.put("\"" + key.name() + "\"", recursiveStringify(field.get(o), out));
            }
        }

    }
    //======================================




    //HERE INJECT DATA IN A SPECIFIC CLASS
    private static void putJsonArrayInObject(Class clazz, JSONArray array, List list) {
        int i = 1;
        try {
            for (Object data : array) {
                JSONObject dataJ = (JSONObject) data;
                putDataInClass(clazz,dataJ,list,i);
                i++;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void putJsonObjectInObject(Class clazz, JSONObject object, List list) {
        int i = 1;
        try {
            putDataInClass(clazz,object,list,i);
            i++;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void putDataInClass(Class clazz, JSONObject dataJ, List list,int i) throws IllegalAccessException, InstantiationException {
        
        Object entity = clazz.newInstance();

        for (Field field : entity.getClass().getDeclaredFields()) {

            Key key = (Key) field.getAnnotation(Key.class);
            Inject inject = (Inject) field.getAnnotation(Inject.class);
            Type type = (Type) field.getAnnotation(Type.class);
            Id id = (Id) field.getAnnotation(Id.class);

            field.setAccessible(true);

            if (id != null) {
                switch (id.type()){

                    case AUTO_GENERATE:
                        if (id.name().isEmpty()) {
                            field.set(entity,i);
                        }
                        break;

                    case KEY:
                        field.set(entity,dataType(field.getType(), dataJ.get(id.name())));
                        break;
                }
            }

            if (key != null) {
                if (inject != null) {
                    if (((Json) inject.classType().getAnnotation(Json.class)).dataType() != JsonDataType.JSON_OBJECT) {
                        field.set(entity, JsonEntity.recursiveInject(inject.classType(), dataJ.get(key.name())));
                    } else {
                        field.set(entity, JsonEntity.recursiveInject(inject.classType(), dataJ.get(key.name())).get(0));
                    }
                } else {
                    if (type != null) {
                        field.set(entity, dataType(type.classType(), dataJ.get(key.name())));
                    } else {
                        field.set(entity, dataType(field.getType(), dataJ.get(key.name())));
                    }
                }
            }
        }

        list.add(entity);
    }
    //=====================================




    //HERE THE CORRESPONDING DATA TYPES ARE ASSIGNED
    private static Object dataType(Class clazz, Object object) {

        Object o = null;

        switch (clazz.toString()) {

            //NUMERICOS ENTEROS
            case "byte":
                o = Byte.parseByte(object.toString());
                break;

            case "short":
                o = Short.parseShort(object.toString());
                break;

            case "int":
                o = Integer.parseInt(object.toString());
                break;

            case "long":
                o = Long.parseLong(object.toString());
                break;

            //NUMERICOS FLOTANTES
            case "float":
                o = Float.parseFloat(object.toString());
                break;

            case "double":
                o = Double.parseDouble(object.toString());
                break;

            //BOOLEANOS
            case "boolean":
                o = Boolean.parseBoolean(object.toString());
                break;

            //CARACTERES
            case "char":
                o = object.toString().charAt(0);
                break;

            //ESTRUCTURADOS
            case "java.lang.String":
                o = object.toString();
                break;

            default:
                o = clazz.cast(object);
        }

        return o;
    }
    //=====================================




    //HERE CHECK IF THE RESTRICTIONS ARE CORRECT
    private static boolean isNull(Object o) {

        if (o != null) {
            switch (o.getClass().getName()) {

                //NUMERICOS ENTEROS
                case "java.lang.Byte":
                    byte b = Byte.parseByte(o.toString());
                    return b != 0;

                case "java.lang.Short":
                    short s = Short.parseShort(o.toString());
                    return s != 0;

                case "java.lang.Integer":
                    int i = Integer.parseInt(o.toString());
                    return i != 0;

                case "java.lang.Long":
                    long l = Long.parseLong(o.toString());
                    return  l != 0;

                //NUMERICOS FLOTANTES
                case "java.lang.Float":
                    float f = Float.parseFloat(o.toString());
                    return f != 0.0;

                case "java.lang.Double":
                    double d = Double.parseDouble(o.toString());
                    return d != 0.0;

                //CARACTERES
                case "java.lang.Character":
                    try {
                        o.toString().charAt(0);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }

                //ESTRUCTURADOS
                case "java.lang.String":
                    String st = o.toString();
                    o.toString().charAt(0);
                    return st != null || st.isEmpty();

                default:
                    return o != null;
            }
        }

        return false;
    }

    private static boolean isRestrictionsCorrect(Object o) {

        boolean out = true;

        try {

            if (o.getClass().getName().equals("java.util.ArrayList")) {

                ArrayList ids = new ArrayList();
                ArrayList list = (ArrayList) o;

                for (Object o1 : list) {

                    for (Field field : o1.getClass().getDeclaredFields()) {

                        field.setAccessible(true);
                        Id id = (Id) field.getAnnotation(Id.class);

                        if (id != null) {
                            for (JsonRestriction restriction : id.restriction()) {
                                switch (restriction) {
                                    case NOT_NULL:
                                        if (!isNull(field.get(o1))) {
                                            System.err.println("Null identifier in: " + field.getName() + "field whit the key - " + id.name());
                                            return false;
                                        }
                                        break;
                                    case NOT_REPEAT:
                                        if (ids.contains(field.get(o1))) {
                                            System.err.println("Identify repeated in: " + field.getName() + " field whit the key - " + id.name());
                                            return false;
                                        }
                                        ids.add(field.get(o1));
                                        break;
                                }
                            }
                        }

                    }

                }

            } else {

                for (Field field : o.getClass().getDeclaredFields()) {

                    field.setAccessible(true);
                    ArrayList ids = new ArrayList();
                    Key key = (Key) field.getAnnotation(Key.class);
                    Inject inject = (Inject) field.getAnnotation(Inject.class);

                    if (field.getType().getName().equals("java.util.List")) {

                        if (key != null) {
                            out = isRestrictionsCorrect(field.get(o));
                        }

                    } else {

                        if (key != null && inject == null) {

                            Id id = (Id) field.getAnnotation(Id.class);

                            if (id != null) {
                                for (JsonRestriction restriction : id.restriction()) {
                                    switch (restriction) {
                                        case NOT_NULL:
                                            if (!isNull(field.get(o))) {
                                                System.err.println("Null identifier in: " + field.getName() + "field whit the key - " + id.name());
                                                return false;
                                            }
                                            break;
                                        case NOT_REPEAT:
                                            if (ids.contains(field.get(o))) {
                                                System.err.println("Identify repeated in: " + field.getName() + " field whit the key - " + id.name());
                                                return false;
                                            }
                                            ids.add(field.get(o));
                                            break;
                                        case NOT_RESTRICTION: break;
                                    }
                                }
                            }

                        } else {
                            if (key != null) {
                                out = isRestrictionsCorrect(field.get(o));
                            }
                        }

                    }
                }

            }

        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        }
        return out;
    }
    //=====================================




    //GET FILE DATAS
    private static JSONArray getJsonArrayData(String path) {

        String out = "";
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        String pathF = "/" + path.replace(".json", "*json").replace(".", "/").replace("{", "").replace("}", "").replace("*", ".");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(JsonEntity.class.getResourceAsStream(pathF), "utf-8"));
            String string;
            while ((string = in.readLine()) != null) {
                out += string;
            }

            array = (JSONArray) parser.parse(out);
            in.close();
            return array;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return array;
    }

    private static JSONObject getJsonObjectData(String path) {

        String out = "";
        JSONParser parser = new JSONParser();
        JSONObject object = null;
        String pathF = "/" + path.replace(".json", "*json").replace(".", "/").replace("{", "").replace("}", "").replace("*", ".");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(JsonEntity.class.getResourceAsStream(pathF), "utf-8"));
            String string;
            while ((string = in.readLine()) != null) {
                out += string;
            }

            object = (JSONObject) parser.parse(out);
            in.close();
            return object;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return object;
    }

    private static JSONArray getExternalJsonArrayData(String path) {

        String out = "";
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        String pathF = path.replace(".json", "*json").replace(".", "/").replace("{", "").replace("}", "").replace("*", ".");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(pathF), "utf-8"));
            String string;
            while ((string = in.readLine()) != null) {
                out += string;
            }

            array = (JSONArray) parser.parse(out);
            in.close();
            return array;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return array;
    }

    private static JSONObject getExternalJsonObjectData(String path) {

        String out = "";
        JSONParser parser = new JSONParser();
        JSONObject object = null;
        String pathF = path.replace(".json", "*json").replace(".", "/").replace("{", "").replace("}", "").replace("*", ".");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(pathF), "utf-8"));
            String string;
            while ((string = in.readLine()) != null) {
                out += string;
            }

            object = (JSONObject) parser.parse(out);
            in.close();
            return object;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return object;
    }
    //======================================

}
