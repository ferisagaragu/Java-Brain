package org.javabrain.util.data;

import org.javabrain.util.resource.Path;
import org.javabrain.util.web.service.Petition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * @author Fernando García
 * @version 0.0.2
 */
public class Json extends Object{

    //STATICS 2
    private static Json ourInstance = new Json();
    public static Json getInstance() {
        return ourInstance;
    }
    //==========================================================================

    //Variable privadas
    private JSONParser parser;
    private JSONObject obj;
    private JSONArray array;
    private Object destructurinOunt = null;
    private Map<Object,String> jsons;
    private int mapCount = 0;
    private boolean isDataBase = false;
    private String path = "";
    private String secName = "";
    //===========================================================================

    //Constantes privadas
    private final String TAB = "\t";
    //===========================================================================

    //CONSTRUCTORES
    public Json(){
        parser = new JSONParser();
        try {
            obj = (org.json.simple.JSONObject) parser.parse("{}");
        } catch (ParseException e) {}
    }

    public  Json(Json json){
        if (json.isJSONArray()){
            array = json.array;
        }else {
            obj = json.obj;
        }
    }

    public Json(Map<Object,Object> json){
        String out = "{";
        if(!json.isEmpty()) {
            for (Object key : json.keySet()) {
                out += "\"" + key + "\"" + ":\"" + json.get(key) + "\",";
            }
            out = out.substring(0, out.length() - 1) + "}";
        }else {
            out = "{}";
        }
        try {
            parser = new JSONParser();
            obj = (org.json.simple.JSONObject) parser.parse(out);
        } catch (ParseException e) {}
    }

    public Json(ArrayList list){
        String out = "{";
        int i = 0;
        if (!list.isEmpty()){
            for (Object object:list) {
                out += "\"item " + i + "\":\"" + object + "\",";
                i++;
            }
            out = out.substring(0,out.length() - 1) + "}";
        } else {
            out = "{}";
        }
        try {
            parser = new JSONParser();
            obj = (org.json.simple.JSONObject) parser.parse(out);
        } catch (ParseException e) {}
    }

    public Json(URL json){
        if (json != null) {
            String out = Petition.doGet(json.toString());
            parser = new JSONParser();
            if (Json.isJSONArray(out)) {
                try {
                    array = (org.json.simple.JSONArray) parser.parse(out);
                } catch (ParseException e) {
                }
            } else {
                try {
                    obj = (org.json.simple.JSONObject) parser.parse(out);
                } catch (ParseException e) {
                }
            }
        }else {
            parser = new JSONParser();
            try {
                obj = (org.json.simple.JSONObject) parser.parse("{}");
            } catch (ParseException e) {}
        }
    }

    public  Json(InputStream json){
        if (json != null) {
            try {
                parser = new JSONParser();
                BufferedReader in = new BufferedReader(new InputStreamReader(json, "utf-8"));
                String sCadena;
                String out = "";
                while ((sCadena = in.readLine()) != null) {
                    out += sCadena;
                }

                if (Json.isJSONArray(out)) {
                    array = (JSONArray) parser.parse(out);
                } else {
                    obj = (JSONObject) parser.parse(out);
                }
            } catch (Exception e) {}
        }else {
            parser = new JSONParser();
            try {
                obj = (JSONObject) parser.parse("{}");
            } catch (ParseException e) {}
        }
    }

    public Json(File json){
        if (json.isDirectory()){
            File[] files = json.listFiles();
            jsons = new HashMap<>();
            for (File file:files){
                parser = new JSONParser();
                BufferedReader ino = null;
                try {
                    ino = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                    String sCadena;
                    String out = "";
                    while ((sCadena = ino.readLine()) != null) {
                        out += sCadena;
                    }
                    jsons.put(file.getName().replace(".json",""),out);
                }catch (Exception e){}
            }
            return;
        }
        if (json != null) {
            try {
                parser = new JSONParser();
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(json), "utf-8"));
                String sCadena;
                String out = "";
                while ((sCadena = in.readLine()) != null) {
                    out += sCadena;
                }

                if (Json.isJSONArray(out)) {
                    array = (JSONArray) parser.parse(out);
                } else {
                    obj = (JSONObject) parser.parse(out);
                }
            } catch (Exception e) {}
        }else {
            parser = new JSONParser();
            try {
                obj = (JSONObject) parser.parse("{}");
            } catch (ParseException e) {}
        }
    }

    public Json(ResultSet json){
        try {
            array = new JSONArray();
            ResultSetMetaData rsmd = json.getMetaData();
            while (json.next()) {
                JSONObject obje = new JSONObject();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String columnName = rsmd.getColumnName(i).toLowerCase();
                    obje.put(columnName, json.getString(i));
                }
                array.add(obje);
            }
        }catch (Exception e){}
    }

    /*todo falta leer archivos base 64 y encriptados version 4
    FUNCIONAN - EXTERNOS
    Json json = new Json(new File("C:\\Users\\QualtopGroup\\Desktop\\test.json"));
    Json json = new Json("C:\\Users\\QualtopGroup\\Desktop\\test.json");

    Json json = new Json("[DB.nueva]");
    Console.black(json.use("acciones"));
    Falta el Json json = new Json("[C:\\Users\\QualtopGroup\\Desktop\\]");
    Falta sql a Json json = new Json("select * from example" o statemen);
    Falta iniciar con hasmap
    Falta iniciar apartir de un arraylist o arra en los cuales el json se formara de item1: valor item2: valor
    Falta iniciar json con petition solo get Json json = new Json("http://www.javabrain.com/example-data");
    Falta iniciar json con inputString
    Falta iniciar json encriptado
    
    FUNCIONAN - INTERNOS
    Json json = new Json("org.javabrain.test.{test.json}");
    Json json = new Json("org/javabrain/test/test.json");
    Json json = new Json("/org/javabrain/test/test.json");
    */
    public Json(Object json) {

        try {
            Object[] result = (Object[]) json;
            isDataBase = (Boolean) result[1];
            json = (Object) result[0];
            path = String.valueOf(result[2]);
            secName = String.valueOf(result[3]);
        }catch (Exception e){}

        //Cargar Json desde un URL
        if (isUrl(json.toString())){
            if (json != null) {
                String out = Petition.doGet(json.toString());
                parser = new JSONParser();
                if (Json.isJSONArray(out)) {
                    try {
                        array = (org.json.simple.JSONArray) parser.parse(out);
                    } catch (ParseException e) {
                    }
                } else {
                    try {
                        obj = (org.json.simple.JSONObject) parser.parse(out);
                    } catch (ParseException e) {
                    }
                }
                return;
            }
        }
        //==================================

        //Carga un Json apartir de un String
        parser = new JSONParser();
        if(json.toString().charAt(0) == '['){
            try {
                array = (org.json.simple.JSONArray) parser.parse(json.toString());
                return;
            } catch (ParseException e) {}
        }else {
            try {
                obj = (org.json.simple.JSONObject) parser.parse(json.toString());
                return;
            } catch (ParseException e) {}
        }
        //==========================================

        //Crea el json con rutas
        if(!(json.toString().charAt(0) == '[')){
            if (new java.io.File(json.toString()).isFile()) {
                java.io.File fil = new java.io.File(json.toString());
                String out = "";
                try {
                    BufferedReader in2 = new BufferedReader(new InputStreamReader(new FileInputStream(fil), "utf-8"));
                    String sCadena = "";

                    while ((sCadena = in2.readLine()) != null) {
                        out += sCadena;
                    }

                } catch (Exception e) {
                }
                if (out.charAt(0) == '[') {
                    try {
                        array = (org.json.simple.JSONArray) parser.parse(out);
                    } catch (ParseException e) {
                    }
                } else {
                    try {
                        obj = (org.json.simple.JSONObject) parser.parse(out);
                    } catch (ParseException e) {
                    }
                }
                return;
            }

            if (json.toString().charAt(0) == '/') {

                String out = "";
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(json.toString()), "utf-8"));
                    String sCadena = "";

                    while ((sCadena = in.readLine())!=null) {
                        out += sCadena;
                    }

                }catch (Exception e){}

                if(out.charAt(0) == '['){
                    try {
                        array = (org.json.simple.JSONArray) parser.parse(out);
                    } catch (ParseException e) {}
                }else {
                    try {
                        obj = (org.json.simple.JSONObject) parser.parse(out);
                    } catch (ParseException e) {}
                }
                return;
            } else {
                String path = "",fileName = "";
                try{
                    path = json.toString();
                    fileName = json.toString().split("\\{")[1].replace("}", "");
                    path = path.replace("{" + fileName + "}", "").replace(".", "/");
                }catch(Exception e){
                    path = json.toString();
                }

                String out = "";
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + path + fileName), "utf-8"));
                    String sCadena = "";

                    while ((sCadena = in.readLine())!=null) {
                        out += sCadena;
                    }

                }catch (Exception e){}
                if (!out.isEmpty()) {
                    if (out.charAt(0) == '[') {
                        try {
                            array = (org.json.simple.JSONArray) parser.parse(out);
                        } catch (ParseException e) {
                        }
                    } else {
                        try {
                            obj = (org.json.simple.JSONObject) parser.parse(out);
                        } catch (ParseException e) {
                        }
                    }
                    return;
                }
            }
        }else{
            String path = json.toString().replace("[", "").replace("]", "");
            if(path.charAt(0) == '/' || path.charAt(0) == '.'){
                path = path.replace(".", "/");
                path = path.substring(1, path.length());
            }else{
                path = path.replace(".", "/");
            }

            jsons = new HashMap();
            java.io.File fil = new java.io.File(path);
            for (Object files : fil.list()) {
                jsons.put(files.toString().replace(".json",""),path+"/"+files);
            }
        }
    }
    //================================================================

    //-METODOS GET

    //MJSONG-0001
    public String getString(Object key){
        try{
            return obj.get(key).toString();
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0002
    public int getInteger(Object key){
        try {
            return Integer.parseInt(obj.get(key).toString());
        }catch (Exception e){}
        return -1;
    }

    //MJSONG-0003
    public float getFloat(Object key){
        try {
            return Float.parseFloat(obj.get(key).toString());
        }catch (Exception e){}
        return -1f;
    }

    //MJSONG-0004
    public boolean getBoolean(Object key){
        try {
            return Boolean.parseBoolean(obj.get(key).toString());
        }catch (Exception e){}
        return false;
    }

    //MJSONG-0005
    public char getCharacter(Object key){
        try{
            return obj.get(key).toString().charAt(0);
        }catch (Exception e){}
        return ' ';
    }

    //MJSONG-0006
    public double getDouble(Object key){
        try {
            return Double.parseDouble(obj.get(key).toString());
        }catch (Exception e){}
        return -1;
    }

    //MJSONG-0007
    public Object getObject(Object key){
        try {
            return obj.get(key);
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0008
    public Json getJSON(Object key){
        try {
            return new Json(obj.get(key));
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0009
    public Object[] getArray(Object key){
        try {
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
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0010
    public ArrayList<Object> getList(Object key){
        try {
            ArrayList<Object> list = new ArrayList<>();
            for (Object o:getArray(key)) {
                list.add(o);
            }
            return list;
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0011
    public Timestamp getTimestamp(Object key){
        try {
            return Timestamp.valueOf(obj.get(key).toString());
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0012
    public Collection getKeys(){
        try {
            Collection collection = new ArrayList();
            for (Object sets:obj.keySet()) {
                collection.add(sets);
            }
            return collection;
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0013
    public String getKey(int index) {
        try {
            int i = 0;
            for (Object sets:obj.keySet()) {
                if(i == index){
                    return sets.toString();
                }
                i++;
            }
            return null;
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0014
    public Json getJSONArray(Object key, int index){
        try{
            JSONArray array = null;
            try{array = (JSONArray) parser.parse(obj.get(key).toString());}catch (Exception e){}
            return new Json(array.get(index));
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0015
    public Json getJSONArray(int index){
        try {
            return new Json(array.get(index));
        }catch (Exception e){}
        return null;
    }

    //MJSONG-0016
    public Json getJSONArray(Object key){
        JSONArray array = null;
        try{array = (JSONArray) parser.parse(obj.get(key).toString());}catch (Exception e){return null;}
        return new Json(array);
    }

    public BigDecimal getBigDecimal(Object key){
        try {
            return BigDecimal.valueOf(Double.parseDouble(obj.get(key).toString()));
        }catch (Exception e){}
        return null;
    }

    public Byte getByte(Object key){
        try {
            return Byte.parseByte(obj.get(key).toString());
        }catch (Exception e){}
        return null;
    }

    public Long getLong(Object key){
        try {
            return Long.parseLong(obj.get(key).toString());
        }catch (Exception e){}
        return null;
    }

    public String getDate(Object key, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(obj.get(key).toString());
            String sdate = formatter.format(date);
            return sdate;
        } catch (Exception e) {}
        return null;
    }

    public String getDecimal(Object key, String format){
        try{
            DecimalFormat format1 = new DecimalFormat(format);
            return format1.format(obj.get(key));
        }catch (Exception e){}
        return null;
    }
    //===============================================================

    //METODOS SET 1

    //MJSONS-0001
    public void setJSON(Object json) {
        //Carga un Json apartir de un String
        parser = new JSONParser();
        if(json.toString().charAt(0) == '['){
            try {
                array = (org.json.simple.JSONArray) parser.parse(json.toString());
                return;
            } catch (ParseException e) {}
        }else {
            try {
                obj = (org.json.simple.JSONObject) parser.parse(json.toString());
                return;
            } catch (ParseException e) {}
        }
        
        //Crea el json con rutas
        if(!(json.toString().charAt(0) == '[')){
            if (new java.io.File(json.toString()).isFile()) {
                java.io.File fil = new java.io.File(json.toString());
                String out = "";
                try {
                    BufferedReader in2 = new BufferedReader(new InputStreamReader(new FileInputStream(fil), "utf-8"));
                    String sCadena = "";

                    while ((sCadena = in2.readLine()) != null) {
                        out += sCadena;
                    }

                } catch (Exception e) {
                }
                if (out.charAt(0) == '[') {
                    try {
                        array = (org.json.simple.JSONArray) parser.parse(out);
                    } catch (ParseException e) {
                    }
                } else {
                    try {
                        obj = (org.json.simple.JSONObject) parser.parse(out);
                    } catch (ParseException e) {
                    }
                }
                return;
            }

            if (json.toString().charAt(0) == '/') {

                String out = "";
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(json.toString()), "utf-8"));
                    String sCadena = "";

                    while ((sCadena = in.readLine())!=null) {
                        out += sCadena;
                    }

                }catch (Exception e){}

                if(out.charAt(0) == '['){
                    try {
                        array = (org.json.simple.JSONArray) parser.parse(out);
                    } catch (ParseException e) {}
                }else {
                    try {
                        obj = (org.json.simple.JSONObject) parser.parse(out);
                    } catch (ParseException e) {}
                }
                return;
            } else {
                String path = "",fileName = "";
                try{
                    path = json.toString();
                    fileName = json.toString().split("\\{")[1].replace("}", "");
                    path = path.replace("{" + fileName + "}", "").replace(".", "/");  
                }catch(Exception e){
                    path = json.toString();
                }

                String out = "";
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + path + fileName), "utf-8"));
                    String sCadena = "";

                    while ((sCadena = in.readLine())!=null) {
                        out += sCadena;
                    }

                }catch (Exception e){}

                if(out.charAt(0) == '['){
                    try {
                        array = (org.json.simple.JSONArray) parser.parse(out);
                    } catch (ParseException e) {}
                }else {
                    try {
                        obj = (org.json.simple.JSONObject) parser.parse(out);
                    } catch (ParseException e) {}
                }
                return;
            }
        }else{
            String path = json.toString().replace("[", "").replace("]", "");
            if(path.charAt(0) == '/' || path.charAt(0) == '.'){
                path = path.replace(".", "/");
                path = path.substring(1, path.length());
            }else{
                path = path.replace(".", "/");
            }
            
            jsons = new HashMap();
            java.io.File fil = new java.io.File(path);
            for (Object files : fil.list()) {
                jsons.put(files.toString().replace(".json",""),path+"/"+files);
            }
        }  
    }

    //===============================================================

    //METODOS DE ACCION
    public void remove(Object key){
        if (isJSONArray()) {
            array.remove(key);
        } else {
            obj.remove(key);
        }
    }

    public void remove(int index){

        if (isJSONArray()){
            array.remove(index);
        }else {
            obj.remove(getKey(index));
        }
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

    public Json replaceJSONArray(Object key,Json json){
        try {
            if('[' == json.toJSONString().charAt(0)) {
                obj.replace(key, (JSONArray) parser.parse(json.toJSONString()));
            }
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

    public Json putJSONInJSONArray(Object key,Json json){
        String data = obj.get(key).toString().substring(1,obj.get(key).toString().length()-1);
        Iterator it = json.toMap().entrySet().iterator();
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

    public Json putObjectInJSONArray(Object key,Map<Object,Object> objects,int index){

        try {
            JSONObject object = (JSONObject) parser.parse(getJSONArray(key,index).toString());
            array = (JSONArray) parser.parse(getJSONArray(key).toString());
            object.putAll(objects);
            array.set(index,object);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Json(replace(key,array));
    }

    public boolean existKey(Object key) {
        try {
            for (Object sets:getKeys()) {
                if (sets.toString().equals(key.toString())){
                    return true;
                }
            }
        }catch (Exception e){}
        return false;
    }

    public Json put(Object key, Object value){
        obj.put(key,value);
        return new Json(obj);
    }

    public Json putJSON(Map<Object,Object> map){
        obj.putAll(map);
        return  new Json(obj);
    }

    public Json putJSON(Json json){
        int i = 0;
        if (json.isJSONArray()){
            Date date = new Date();
            Long j = new Long(date.getTime());
            if (json.existKey("item"+j)){
                obj.put("item"+(j+1),json);
            }else {
                obj.put("item"+j,json);
            }
            return this;
        }

        for (Object object:json.getKeys()){

            if (this.existKey(object)){
                obj.put(object.toString()+i,json.getObject(object));
            }else {
                obj.put(object.toString(),json.getObject(object));
            }
        }
        return this;
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

    public Json putJSONArray(Object key,Json json){
        try {
            obj.put(key,(JSONArray) parser.parse("["+json.toJSONString()+"]"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Json(obj);
    }

    public Json putJSONArray(Json json){
        array.add(json);
        return this;
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
            out.write(toJSONString().replace("\\","").replace("\"{","{").replace("}\"","}"));
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
            BufferedReader in = new BufferedReader(new InputStreamReader(Path.get(jsonFile), "utf-8"));
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

    public Json exclude(int index){

        if(isJSONArray()){
            int i = 0;
            String out = "";
            for (Json json : values()) {
                if(i != index){
                    out += json.toString();
                }
                i++;
            }
            return new Json("["+out+"]");
        }else {
            try {
                int i = 0;
                JSONObject out = (JSONObject) parser.parse(obj.toString());
                for (Object jsons : obj.keySet()) {
                    if(i == index){
                       out.remove(jsons);
                       return new Json(out);
                    }
                    i++;
                }
            }catch (Exception e){}
        }
        return new Json();
    }

    public Json exclude(Object key){
        if(!isJSONArray()){
            try {
                JSONObject out = (JSONObject) parser.parse(obj.toString());
                for (Object jsons : obj.keySet()) {
                    if(jsons.toString().equals(key.toString())){
                        out.remove(jsons);
                        return new Json(out);
                    }
                }
            }catch (Exception e){}
        }
        return new Json();
    }

    public Json select(String[] keys){
        String out = "{";
        String js = "";
        if(isJSONArray()) {
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

    public Json where(Object key,Object match){
        if(isJSONArray()){
            try {
                Json out = new Json("[]");
                parser = new JSONParser();
                for (Object data : array) {
                    JSONObject obj = (JSONObject) parser.parse(data.toString());
                    if (obj.get(key).toString().equals(match.toString())){
                        out.putJSONArray(new Json(obj));
                    }
                }
                return new Json(out);
            }catch (Exception e){e.getCause();e.getMessage();}
        } else {
            try {
                Json out = new Json("[]");
                if (obj.get(key).toString().equals(match.toString())){
                    out.putJSONArray(new Json(obj));
                }
                return new Json(out);
            }catch (Exception e){}
        }
        return null;
    }

    //todo este metodo es inperfecto
    public Json join(Json arrayJoin,Object matchKey){
        Json out = null;
        Json outArray = new Json("[]");
        if (this.isJSONArray() && arrayJoin.isJSONArray()){
            for (Json json:this.values()){
                for (Json json1:arrayJoin.values()){
                    if (json.getObject(matchKey) == json1.getObject(matchKey)){
                        out = json.as(matchKey,matchKey+"Join").putJSON(json1);
                        out.remove(matchKey);
                        outArray.putJSONArray(out);
                    }
                }
            }
        }
        return outArray;
    }

    public Json leftJoin(Json arrayJoin,Object matchKey){
        Json outArray = arrayJoin;
        if (this.isJSONArray() && arrayJoin.isJSONArray()){
            for (Json json:this.values()){
                int i = 0;
                for (Json json1:arrayJoin.values()){
                    if (json.getObject(matchKey) == json1.getObject(matchKey)){
                        outArray.remove(i);
                    }
                    i++;
                }
            }
        }else{
            return null;
        }
        return outArray;
    }

    public Json orderBy(Object key){

        JSONParser parser = new JSONParser();
        JSONArray jsonArr = null;
        JSONArray sortedJsonArray = null;
        try {
            jsonArr = (JSONArray) parser.parse(array.toJSONString());
            sortedJsonArray = new JSONArray();

            List<JSONObject> jsonValues = new ArrayList<JSONObject>();
            for (int i = 0; i < jsonArr.size(); i++) {
                jsonValues.add((JSONObject) parser.parse(jsonArr.get(i).toString()));
            }
            Collections.sort( jsonValues, new Comparator<JSONObject>() {
                private final String KEY_NAME = key.toString();

                @Override
                public int compare(JSONObject a, JSONObject b) {
                    String valA = new String();
                    String valB = new String();

                    try {
                        valA = (String) a.get(KEY_NAME);
                        valB = (String) b.get(KEY_NAME);
                    }
                    catch (Exception e) {}

                    return valA.compareTo(valB);
                }
            });

            for (int i = 0; i < jsonArr.size(); i++) {
                sortedJsonArray.add(jsonValues.get(i));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Json(sortedJsonArray);
    }

    public void val(String var, Object value){
        try{
            if(isJSONObject()){
                String out = obj.toString().replace("{"+var+"}",value.toString());
                obj = (JSONObject) parser.parse(out);
            }else{
                String out2 = array.toString().replace("{"+var+"}",value.toString());
                array = (JSONArray) parser.parse(out2);
            }
        }catch (Exception e){}

    }
    
    public Json use(Object jsonFile){
        try{
            Json out = new Json(jsons.get(jsonFile));
            Object[] o = {out,true,
            jsons.get(jsonFile).replace(jsonFile.toString()+".json","")
            .replace(jsonFile.toString(),""),
            jsonFile.toString()};
            return new Json(o);
        }catch (Exception e){}
        return null;
    }

    public Json as(int index,Object newKey){
        if (isJSONObject()){
            Json out = this;
            Object data = null;
            data = out.getObject(getKey(index));
            out.remove(getKey(index));
            out.put(newKey,data);
            return out;
        }
        return null;
    }

    public Json as(Object key,Object newKey){
        if (isJSONObject()){
            Json out = this;
            Object data = null;
            data = out.getObject(key);
            out.remove(key);
            out.put(newKey,data);
            return out;
        }
        return null;
    }

    public long id(){
        if (isDataBase){
            File file = new File(path + "secuences");
            File file1 = new File(path +"/secuences/"+ secName + ".seq");
            if ((!file.exists()) || (!file1.exists())) {
                file.mkdirs();
                createSeq();
            }
        }
        return sumSeq();
    }
    //===============================================================

    //METODOS PRIVADOS

    private void createSeq(){
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path +"/secuences/"+ secName + ".seq"), "utf-8"));
            out.write("0");
            out.close();
        } catch (Exception e){}
    }

    private long sumSeq(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path +"/secuences/"+ secName + ".seq"), "utf-8"));
            String sCadena;
            String outs = "";
            while ((sCadena = in.readLine())!=null) {
                outs += sCadena;
            }
            long result = Long.parseLong(outs) + 1;
            in.close();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path +"/secuences/"+ secName + ".seq"), "utf-8"));
            out.write(String.valueOf(result));
            out.close();

            return result;
        } catch (Exception e){}
        return 0;
    }

    private String formatJSONString(String str) {
        StringBuilder r = new StringBuilder();
        formatJSON(JSONValue.parse(str), r, "");
        return r.toString();
    }

    private void formatJSON(Object obj, StringBuilder r,String indent) {
        if (obj == null) {
            r.append("null");
        } else if (obj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map map = (Map) obj;
            Object[] keys = map.keySet().toArray();
            Arrays.sort(keys);
            r.append("{\n");
            String indentTab = indent + TAB;
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
                String indentTab = indent + TAB;
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

    private Object getDestructurin(Json json,Object comparador){
        
        if(json.isJSONObject()){
            for(Object key:json.getKeys()){
                if(key.toString().equals(comparador.toString())){
                    destructurinOunt = json.getString(key);
                    return json.getString(key);
                }

                try{
                   if(json.getJSON(key).isJSONObject()){
                       getDestructurin(json.getJSON(key),comparador);
                    }
                }catch(Exception e){}

                try{
                   if(json.getJSON(key).isJSONArray()){
                       getDestructurin(json.getJSON(key),comparador);
                    }
                }catch(Exception e){}
            }
        }else{
            
            for(Json jsons:json.values()){
                getDestructurin(jsons, comparador);
            }
        }
        
        return null;
    }

    private ArrayList toList(Json json,ArrayList list){

        if (json.isJSONArray()){
            for (Json json1:json.values()){
                toList(json1,list);
            }
        }else {
            for (Object data:json.getKeys()){

                try {
                    Json json1 = new Json(json.getString(data));
                    toList(json1,list);
                }catch (Exception e){
                    list.add(json.getString(data));
                }
            }
        }
        return list;
    }

    private Map<Object,Object> toMap(Json json, Map map){

        if (json.isJSONArray()){
            for (Json json1:json.values()){
                toMap(json1,map);
            }
        }else {
            for (Object data:json.getKeys()){
                try {
                    Json json1 = new Json(json.getString(data));
                    toMap(json1,map);
                }catch (Exception e){
                    if (map.containsKey(data) || map.containsKey(data.toString()+mapCount)){
                        map.put(data.toString()+mapCount,json.getString(data));
                        mapCount++;
                    }else {
                        map.put(data.toString(),json.getString(data));
                    }
                }
            }
        }
        return map;
    }

    private String readFile(String path){
        try {
            String out = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            String sCadena;
            while ((sCadena = in.readLine()) != null) {
                out +=  sCadena;
            }
            return out;
        }catch (Exception e){}
        return null;
    }

    private String readFile(InputStream path){
        try {
            String out = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(path, "utf-8"));
            String sCadena;
            while ((sCadena = in.readLine()) != null) {
                out +=  sCadena;
            }
            return out;
        }catch (Exception e){}
        return null;
    }

    private static boolean isUrl(String s) {
        String regex = "^(https?://)?(([\\w!~*'().&=+$%-]+: )?[\\w!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([\\w!~*'()-]+\\.)*([\\w^-][\\w-]{0,61})?[\\w]\\.[a-z]{2,6})(:[0-9]{1,4})?((/*)|(/+[\\w!~*'().;?:@&=+$,%#-]+)+/*)$";

        try {
            Pattern patt = Pattern.compile(regex);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }
    //==============================================================

    //METODOS TO
    public Map<Object,Object> toMap(){
        return toMap(this,new HashMap());
    }

    public ArrayList toList(){
        ArrayList list = toList(this,new ArrayList());
        return list;
    }

    public String toJSONString(){

        if(obj == null || obj.toString().equals("{}")){
            return formatJSONString(array.toString());
        }

        return formatJSONString(obj.toString());
    }

    public String toString(){

        if(obj == null || obj.toString().equals("{}")){
            try{return array.toJSONString();}catch(Exception e){return "{}";}
        }

        try{return obj.toJSONString();}catch(Exception e){return "{}";}
    }
    //===============================================================

    //METODOS IS

    public boolean isJSONArray(){
        if (obj == null){
            return true;
        }
        return false;
    }

    public boolean isJSONObject(){
        if (array == null){
            return true;
        }
        return false;
    }

    //===============================================================

    //Métodos static
    public static Json parseJson(Map map){
        Json out = new Json();
        for (Object object:map.keySet()){
            out.put(object,map.get(object));
        }
        return out;
    }

    public static Json parseJson(ArrayList list){
        int i = 0;
        Json json = new Json();
        for (Object object:list){
            json.put("item"+i,object);
            i++;
        }
        return json;
    }

    public static Json parseJson(Object object){
        try {
            return new Json(object);
        }catch (Exception e){}
        return null;
    }

    public static boolean isJSONArray(Object obj){
        try {
            JSONParser parser1 = new JSONParser();
            JSONArray array1 = (JSONArray) parser1.parse(obj.toString());
            return true;
        }catch (Exception e){}
        return false;
    }

    public static boolean isJSONObject(Object obj){
        try {
            JSONParser parser1 = new JSONParser();
            JSONObject obj1 = (JSONObject) parser1.parse(obj.toString());
            return true;
        }catch (Exception e){}
        return false;
    }
    //===============================================================

    //METODOS COMPLEJOS "DESTRUCTURIN"
    public Object get(Object key){
        getDestructurin(this,key);
        return destructurinOunt;
    }
    //===============================================================
    
    /*todo Versión 0.0.4
    Versión 0.0.4 ->
    -METODO PARA ORDENAR EL JSON
    -METODO PARA AUTO COMPLETAR json.getMatch(exam)  y que esto se ignifique json.get(example)
    -METODOS PARA CONVERTIR SQL EN JSON Y BISEVERSA*/
}