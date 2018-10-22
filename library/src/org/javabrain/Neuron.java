package org.javabrain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javabrain.util.alert.Log;

public class Neuron {

    private static Map<Object,Object> map = new HashMap<>();
        
    public static void init() {
        //Crea el folder conf
        File folder = new File(System.getProperty("user.dir") + "\\src\\conf");
        if (!folder.exists()) {folder.mkdirs();}
        //Crea el archivo de configuracion
        File file = new File(folder.getPath() + "\\neuron.json");
        if (!file.exists() && folder.exists()) {
            String str = Json.parseJson(neuronJson).toJSONString();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()))) {
                writer.write(str);
            } catch (IOException ex) {
                Logger.getLogger(Neuron.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (file.exists()) {
            Json js = new Json(file.getPath());
            File pack = new File(System.getProperty("user.dir") + "\\src\\" + (js.getJSON("package").getString("company") != null ? js.getJSON("package").getString("company") : "").replace(".","\\"));
            pack.mkdirs();
            
            if (pack.exists()) {
                String[] stg = (String[]) js.getJSON("package").getArray("modules") != null ? (String[]) js.getJSON("package").getArray("modules") : new String[] {};

                for (String s:stg) {
                    File module = new File(pack.getPath() + "\\" + s);
                    module.mkdir();
                }
            }
            
            if (pack.exists() && !(js.getJSON("package").getString("company") != null ? js.getJSON("package").getString("company") : "").isEmpty()) {
                File packCheck = new File(System.getProperty("user.dir") + "\\src\\");
                File[] folders = packCheck.listFiles();
                
                for(File fil :folders) {
                    if (!(fil.getPath().contains((js.getJSON("package").getString("company") != null ? js.getJSON("package").getString("company") : "").replace(".","\\")) || fil.getName().equals("conf"))) {
                        Log.alert("Package found " + fil.getName() + " outside of the company packages.");
                    }
                }
                
            }
        }
    }
    
    
    public static Object param(Object key){
        Json json = new Json("conf.{neuron_example.json}");
        return json.getJSON("param").getObject(key.toString());
    }

    public static void setStash(Object key,Object param){
        map.put(key,param);
    }

    public static Object getStash(Object key){
        return map.get(key);
    }

    public static void removeStash(Object key){
        map.remove(key);
    }
   
     public static Connection sqlConnection() {
        try {
            Json json = new Json("conf.{neuron_example.json}");
            Json json1 = json.getJSON("connection").getJSON("postgres");
            String user = json1.getString("user");
            String password = json1.getString("password");
            String host = json1.getString("host");
            String database = json1.getString("database");
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {}
            Connection connection = DriverManager.getConnection("jdbc:postgresql://"+host+"/"+database,user, password);
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return null;
    }
    
    public static Connection connection(String dbName) {
        try {
            Json json = new Json("conf.{neuron_example.json}");
            Json json1 = json.getJSON("connection").getJSON(dbName);
            Console.viewer(json1);
            String user = json1.getString("user");
            String password = json1.getString("password");
            String host = json1.getString("host");
            String database = json1.getString("database");
            String classname = json1.getString("classname");
            
            try {
                Class.forName(classname);
            } catch (ClassNotFoundException ex) {}
            Connection connection = DriverManager.getConnection("jdbc:postgresql://"+host+"/"+database,user, password);
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        return null;
    }
    
    private static String neuronJson = 
    "{"
        + "'param':{},"
        + "'message':{"
            + "'info': true,"
            + "'alert':true,"
            + "'error':true "
        + "},"
        + "'package': {"
            + "'company' : '',"
            + "'modules' : ['model','drawable','controller','run','res']"
        + "},"
        + "'res' : {"
            + "'img':'res',"
            + "'audio' : 'res'"
        + "}"
  + "}";
    
    
    
}
