package org.javabrain.util.data;

import org.javabrain.util.resource.Path;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/***
 * @author Fernando García
 * @version 0.0.2
 */
public class Json extends Object{

    //STATICS
    private static Json ourInstance = new Json();
    public static Json getInstance() {
        return ourInstance;
    }
    //==========================================================================

    //Variable privadas
    private JSONParser parser;
    private JSONObject obj;
    private JSONArray array;
    //===========================================================================

    //CONSTRUCTORES
    public Json(){
        parser = new JSONParser();
        try {
            obj = (org.json.simple.JSONObject) parser.parse("{}");
        } catch (ParseException e) {}
    }

    public Json(Object json) {
        parser = new JSONParser();
        if(json.toString().charAt(0) == '['){
            try {
                array = (org.json.simple.JSONArray) parser.parse(json.toString());
            } catch (ParseException e) {}
        }else {
            try {
                obj = (org.json.simple.JSONObject) parser.parse(json.toString());
            } catch (ParseException e) {}
        }

    }

    public Json(InputStream inputStream) {
        parser = new JSONParser();

        String out = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String sCadena = "";

            while ((sCadena = in.readLine())!=null) {
                out += sCadena;
            }

        }catch (Exception e){}

        if(out.toString().charAt(0) == '['){
            try {
                array = (org.json.simple.JSONArray) parser.parse(out);
            } catch (ParseException e) {}
        }else {
            try {
                obj = (org.json.simple.JSONObject) parser.parse(out);
            } catch (ParseException e) {}
        }
    }

    //================================================================

    //METODOS GET

    public String getString(Object key){
        return obj.get(key).toString();
    }

    public int getInteger(Object key){
        return Integer.parseInt(obj.get(key).toString());
    }

    public float getFloat(Object key){
        return Float.parseFloat(obj.get(key).toString());
    }

    public boolean getBoolean(Object key){
        return Boolean.parseBoolean(obj.get(key).toString());
    }

    public char getCharacter(Object key){
        return obj.get(key).toString().charAt(0);
    }

    public double getDouble(Object key){
        return Double.parseDouble(obj.get(key).toString());
    }

    public Object getObject(Object key){
        return obj.get(key);
    }

    public Json getJSON(Object key){
        return new Json(obj.get(key));
    }

    public Json getJSONArray(Object key, int index){
        JSONArray array = null;

        try{array = (JSONArray) parser.parse(obj.get(key).toString());}catch (Exception e){}

        return new Json(array.get(index));
    }

    public Json getJSONArray(int index){
        return new Json(array.get(index));
    }

    public Json getJSONArray(Object key){
        JSONArray array = null;
        try{array = (JSONArray) parser.parse(obj.get(key).toString());}catch (Exception e){}
        return new Json(array);
    }

    public Object[] getArray(Object key){
        Object[] dat = null;
        if(getString(key).charAt(0) == '['){
           String dato = getString(key).replace("[","").replace("]","");
           dat = dato.split(",");
            for (int i = 0; i < dat.length; i++) {
                if(dat[i].toString().equals("\"\"")){
                    dat[i] = "";
                }else {
                    dat[i] = dat[i].toString().replace("\"","");
                }
            }
        }
        return dat;
    }

    public ArrayList<Object> getList(Object key){
        ArrayList<Object> list = new ArrayList<>();

        for (Object o:getArray(key)) {
            list.add(o);
        }
        return list;
    }

    public Timestamp getTimestamp(Object key){
        return Timestamp.valueOf(obj.get(key).toString());
    }

    //===============================================================

    //METODOS SET

    public void setJSON(Object json) {
        if(json.toString().charAt(0) == '['){
            try {
                array = (org.json.simple.JSONArray) parser.parse(json.toString());
            } catch (ParseException e) {}
        }else {
            try {
                obj = (org.json.simple.JSONObject) parser.parse(json.toString());
            } catch (ParseException e) {}
        }
    }

    //===============================================================

    //todo revisar el por que regresan unos metodo object
    //METODOS DE ACCION

    public void remove(Object key){
        obj.remove(key);
    }

    public int size(){

        if(obj == null){
            return array.size();
        }

        return obj.size();
    }

    public Json replace(Object key, Object value){
        obj.replace(key,value);
        return new Json(obj);
    }

    public Json replaceJSONArray(Object key, Map<Object,Object> objects){
        String data = "";
        Iterator it = objects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            data += "{\""+e.getKey()+"\":\"" + e.getValue()+"\"},";
        }
        data = "["+data.substring(0,data.length()-1)+"]";

        try {
            obj.replace(key,(JSONArray) parser.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  new Json(obj);
    }

    public Json putJSONInJSONArray(Object key,Map<Object,Object> objects){
        String data = obj.get(key).toString().substring(1,obj.get(key).toString().length()-1);
        Iterator it = objects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            data += "{\""+e.getKey()+"\":\"" + e.getValue()+"\"},";
        }
        data = data.replace(",","");
        data = data.replace("}","},");
        data = data.substring(0,data.length()-1);
        data = "["+data+"]";
        try {
            obj.replace(key,(JSONArray) parser.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Json(obj);
    }

    //todo poner nuevo objeto dentro de Array
    //FALTA LA ADICION DE CODIGO ESTE METODO AÑADIRA UN JSON DENTRO DE UN JSONARRAY ASI
    //[{"hours":"2018-03-01 13:28:00","costo":36,"liters":25,"id":1,"namea":"maquina1","gasid": "1"}]
    //Donde idgas fue añadido dentro del JSON original
    public Object putObjectInJSONArray(Object key,Map<Object,Object> objects,int index){
        String data = obj.get(key).toString().substring(1,obj.get(key).toString().length()-1);
        Iterator it = objects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            data += "\""+e.getKey()+"\":\"" + e.getValue()+"\",";
        }
        data = data.replace("}","");
        data = data.substring(0,data.length()-1);
        data = "["+data+"}]";
        System.out.println(data);
        try {
            obj.replace(key,(JSONArray) parser.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Json(obj);
    }

    public String getKey(int index) {
        int i = 0;
        for (Object sets:obj.keySet()) {
            if(i == index){
                return sets.toString();
            }
            i++;
        }
        return null;
    }

    public boolean existKey(Object key) {
        for (Object sets:getKeys()) {
            if (sets.toString().equals(key.toString())){
                return true;
            }
        }
        return false;
    }

    public Collection getKeys(){
        Collection collection = new ArrayList();
        for (Object sets:obj.keySet()) {
            collection.add(sets);
        }
        return collection;
    }

    public String toJSONString(){

        if(obj == null){
            return array.toJSONString();
        }

        return obj.toJSONString();
    }

    public String toString(){

        if(obj == null){
            return array.toJSONString();
        }

        return obj.toJSONString();
    }

    public Json putJSON(Object key, Object value){
        obj.put(key,value);
        return new Json(obj);
    }

    public Json putJSONArray(Object key, Map<Object,Object> objects){
        String data = "";
        Iterator it = objects.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            data += "{\""+e.getKey()+"\":\"" + e.getValue()+"\"},";
        }
        data = "["+data.substring(0,data.length()-1)+"]";

        try {
            obj.put(key,(JSONArray) parser.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }

         return new Json(obj);
    }

    public ArrayList<Json> values(){
        ArrayList<Json> list = new ArrayList<>();

        if (obj == null){
            for (Object object:array.toArray()) {
                list.add(new Json(object));
            }
        }else {
            for (Object object:obj.values()) {
                list.add(new Json(object));
            }
        }

        return list;
    }

    public boolean write(String path){
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
            out.write(toJSONString().toString().replace("\\","").replace("\"{","{").replace("}\"","}"));
            out.close();
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void read(InputStream inputStream){
        String out = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String sCadena = "";

            while ((sCadena = in.readLine())!=null) {
                out += sCadena;
            }

        }catch (Exception e){}

        if(out.toString().charAt(0) == '['){
            try {
                array = (org.json.simple.JSONArray) parser.parse(out);
            } catch (ParseException e) {}
        }else {
            try {
                obj = (org.json.simple.JSONObject) parser.parse(out);
            } catch (ParseException e) {}
        }
    }

    public void read(String jsonFile){
        String out = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(Path.getJson(jsonFile), "utf-8"));
            String sCadena = "";

            while ((sCadena = in.readLine())!=null) {
                out += sCadena;
            }

        }catch (Exception e){}

        if(out.toString().charAt(0) == '['){
            try {
                array = (org.json.simple.JSONArray) parser.parse(out);
            } catch (ParseException e) {}
        }else {
            try {
                obj = (org.json.simple.JSONObject) parser.parse(out);
            } catch (ParseException e) {}
        }
    }

    public ArrayList<Json> exclude(int index){
        int i = 0;
        ArrayList<Json> al = new ArrayList<>();
        for (Json json : values()) {
            if(i != index){
                al.add(json);
            }
            i++;
        }
        return al;
    }

    public Json select(String[] keys){

        String out = "{";
        String js = "";
        if(obj.toString().equals("{}")) {
            for (Object array : array) {
                try {
                    obj = (JSONObject) parser.parse(array.toString());
                    int a = 1;
                    for (String s : keys) {
                        if (existKey(s)) {
                            js += "\"" + s + "\":\"" + obj.get(s) + "\",";
                            out += js;
                            js = "";
                        }

                        if (keys.length == a) {
                            out = out.substring(0, out.length() - 1) + "},{";
                        }

                        a++;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                int a = 1;
                for (String s : keys) {
                    if (existKey(s)) {
                        js += "\"" + s + "\":\"" + obj.get(s) + "\",";
                        out += js;
                        js = "";
                    }

                    if (keys.length == a) {
                        out = out.substring(0, out.length() - 1) + "},{";
                    }
                    a++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out = out.substring(0,out.length()-2);
            return new Json(out);
        }

        out = "["+out.substring(0,out.length()-2)+"]";
        return new Json(out);
    }


    //===============================================================

    //METODOS PRIVADOS
    //==============================================================

    /*todo Versión 0.0.2
    Versión 0.0.2 ->
    -AGREGAR JSONSELECT    _/ Completo
    -AGREGAR JSONJOIN
    -METODO PARA ORDENAR EL JSON
    -TIPEAR EL BSON "HACER EN OTRA CLACE"
    -METODOS PARA CONVERTIR SQL EN JSON Y BISEVERSA
    -HasMap a JSON*/
}
