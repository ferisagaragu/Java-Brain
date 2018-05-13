package org.javabrain.util.data;

import org.javabrain.util.resource.Path;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    
    /*
    FUNCIONAN - EXTERNOS
    Json json = new Json(new File("C:\\Users\\QualtopGroup\\Desktop\\test.json"));
    Json json = new Json("C:\\Users\\QualtopGroup\\Desktop\\test.json");
    
    Json json = new Json("[DB.nueva]");
    Console.black(json.use("acciones"));
    Falta el Json json = new Json("[C:\\Users\\QualtopGroup\\Desktop\\]");
    
    FUNCIONAN - INTERNOS
    Json json = new Json("org.javabrain.test.{test.json}");
    Json json = new Json("org/javabrain/test/test.json");
    Json json = new Json("/org/javabrain/test/test.json");
    */
    public Json(Object json) {
        
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
            if (new File(json.toString()).isFile()) {
                File fil = new File(json.toString());
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
            File fil = new File(path);
            for (Object files : fil.list()) {
                jsons.put(files.toString().replace(".json",""),path+"/"+files);
            }
        }  
    }
    //================================================================

    //-METODOS GET

    //MJSONG-0001
    public String getString(Object key){
        return obj.get(key).toString();
    }

    //MJSONG-0002
    public int getInteger(Object key){
        return Integer.parseInt(obj.get(key).toString());
    }

    //MJSONG-0003
    public float getFloat(Object key){
        return Float.parseFloat(obj.get(key).toString());
    }

    //MJSONG-0004
    public boolean getBoolean(Object key){
        return Boolean.parseBoolean(obj.get(key).toString());
    }

    //MJSONG-0005
    public char getCharacter(Object key){
        return obj.get(key).toString().charAt(0);
    }

    //MJSONG-0006
    public double getDouble(Object key){
        return Double.parseDouble(obj.get(key).toString());
    }

    //MJSONG-0007
    public Object getObject(Object key){
        return obj.get(key);
    }

    //MJSONG-0008
    public Json getJSON(Object key){
        return new Json(obj.get(key));
    }

    //MJSONG-0009
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

    //MJSONG-0010
    public ArrayList<Object> getList(Object key){
        ArrayList<Object> list = new ArrayList<>();

        for (Object o:getArray(key)) {
            list.add(o);
        }
        return list;
    }

    //MJSONG-0011
    public Timestamp getTimestamp(Object key){
        return Timestamp.valueOf(obj.get(key).toString());
    }

    //MJSONG-0012
    public Collection getKeys(){
        Collection collection = new ArrayList();
        for (Object sets:obj.keySet()) {
            collection.add(sets);
        }
        return collection;
    }

    //MJSONG-0013
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

    //MJSONG-0014
    public Json getJSONArray(Object key, int index){
        JSONArray array = null;

        try{array = (JSONArray) parser.parse(obj.get(key).toString());}catch (Exception e){}

        return new Json(array.get(index));
    }

    //MJSONG-0015
    public Json getJSONArray(int index){
        return new Json(array.get(index));
    }

    //MJSONG-0016
    public Json getJSONArray(Object key){
        JSONArray array = null;
        try{array = (JSONArray) parser.parse(obj.get(key).toString());}catch (Exception e){}
        return new Json(array);
    }

    public BigDecimal getBigDecimal(Object key){
        return BigDecimal.valueOf(Double.parseDouble(obj.get(key).toString()));
    }

    public Byte getByte(Object key){
        return Byte.parseByte(obj.get(key).toString());
    }

    public Long getLong(Object key){
        return Long.parseLong(obj.get(key).toString());
    }

    public String getDate(Object key, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(obj.get(key).toString());
            String sdate = formatter.format(date);
            return sdate;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDecimal(Object key, String format){
        DecimalFormat format1 = new DecimalFormat(format);
        return format1.format(obj.get(key));
    }
    //===============================================================

    //METODOS SET 1

    //MJSONS-0001
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
    //METODOS DE ACCION 19

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

    //Falta Json

    public boolean existKey(Object key) {
        for (Object sets:getKeys()) {
            if (sets.toString().equals(key.toString())){
                return true;
            }
        }
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

    //Todo falta hacer el metodo to hasmap para que se pueda usar este
    public Json putJSON(Json json){
        //obj.putAll();
        return  new Json(obj);
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
        try {
            array = (JSONArray) parser.parse("["+json.toJSONString()+"]");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Json(array);
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

    //todo este es para excluir de un JSONArray pero falta para excluir un elemento
    public Json exclude(int index){
        int i = 0;
        String out = "";
        for (Json json : values()) {
            if(i != index){
                out += json.toString();
            }
            i++;
        }
        return new Json("["+out+"]");
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

    //todo este metodo es inperfecto
    public Json join(Json arrayJoin,String idJSON,String idJSON2,String match){

        JSONArray  nArray = new JSONArray();

        try {
            for (Object obj1 : array) {

                JSONObject js1 = (JSONObject) parser.parse(obj1.toString());

                for (Object obj2 : arrayJoin.values()) {

                    JSONObject js2 = (JSONObject) parser.parse(obj2.toString());

                    if (js1.get(idJSON).toString().toLowerCase().equals(match.toLowerCase()) &&
                            js2.get(idJSON2).toString().toLowerCase().equals(match.toLowerCase())) {
                        nArray.add((JSONObject) parser.parse(js1.toString().replace("}", js2.toString().replace("{", ",").replace("\"id\"", "\"idjoin\""))));
                    }

                }

            }
        }catch (Exception e){}

        return  new Json(nArray);
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
        return new Json(jsons.get(jsonFile));
    }
    //===============================================================

    //METODOS PRIVADOS

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
    //==============================================================

    //Todo metodos TO en versión 0.0.3
    //METODOS TO
    public Map<Object,Object> toMap(){
        Map<Object,Object> map = new LinkedHashMap<>();
        for (Object key:getKeys()){
            map.put(key,obj.get(key));
        }

        return map;
    }

    public ArrayList toList(){
        return null;
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

    //METODOS COMPLEJOS "DESTRUCTURIN"
    public Object get(Object key){
        getDestructurin(this,key);
        return destructurinOunt;
    }
    //===============================================================
    
    /*todo Versión 0.0.2
    Versión 0.0.2 ->
    -AGREGAR JSONJOIN
    -METODO PARA ORDENAR EL JSON
    -TIPEAR EL BSON "HACER EN OTRA CLACE"
    -METODOS PARA CONVERTIR SQL EN JSON Y BISEVERSA
    -HasMap a JSON y biseversa*/
}
