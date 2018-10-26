package org.javabrain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import org.javabrain.util.alert.Log;

/***
 * @author Fernando García
 * @version 0.0.2
 */
public class Neuron {
    
    private static Map<Object,Object> map = new HashMap<>();
        
    public static void init() {
        
        //Crea el folder conf
        File folder = new File(System.getProperty("user.dir") + "\\src\\conf");
        if (!folder.exists()) {folder.mkdirs();}
        String str = "";
        //Crea el archivo de configuracion
        File file = new File(folder.getPath() + "\\neuron.json");
        if (!file.exists() && folder.exists()) {
            NeuronConfing neuronJs =  new NeuronConfing(null, true);
            neuronJs.setVisible(true);
            str = Json.parseJson(neuronJs.getResp()).toJSONString();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()))) {
                writer.write(str);
            } catch (IOException ex) {
                Log.error(ex.getMessage());
            }
        }

        Json js = new Json(str.isEmpty() ? "/conf/neuron.json" : str);
        makeModules(js);
        makeRes(js);
        clearMain(new File(System.getProperty("user.dir") + "\\src\\"));
        System.exit(0);
    }
    
    // CREACION DE MODULOS
    private static  void makeModules(Json js) {
        String[] modules = (String[]) js.getJSON("package").getArray("modules");
        File moduleMk = new File(System.getProperty("user.dir") + "\\src\\" + (js.getJSON("package").getString("company").replace(".","\\")));
        if (!moduleMk.exists()) {
            moduleMk.mkdirs();
        }
        for (String mod : modules) {
            mod = mod.replace(".","\\");
            File file = new File(moduleMk.getPath() + "\\" + mod);
            
            if ((!file.exists()) && getmodulePath(new File(System.getProperty("user.dir") + "\\src\\"),file.getName()).equals("emply")) {
                file.mkdirs();
            } else {
                String originalPath = getmodulePath(new File(System.getProperty("user.dir") + "\\src\\"),file.getName());
                File[] fils = new File(originalPath).listFiles();
                
                for (File fil : fils) {
                    
                    String pack = fil.getPath().replace(System.getProperty("user.dir") + "\\src\\","").replace(fil.getName(),"").replace("\\",".");
                    pack = pack.substring(0, pack.length() - 1);
                    
                    String finalPack = file.getPath().replace(System.getProperty("user.dir") + "\\src\\","").replace("\\",".");
                    changePack(fil, pack, finalPack);
                    changeImport(new File(System.getProperty("user.dir") + "\\src\\"),pack,finalPack);
                    
                }
                moveCmd(originalPath,file.getPath());
            }
        }
    }
        
    private static String out = "";
    private static String getmodulePath(File dir,String module) {
        
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    if (module.equals(listFile[i].getName())) {
                        out = listFile[i].getPath();
                    } else {
                       getmodulePath(listFile[i],module); 
                    }
                }
            }
        }
        
        return out.isEmpty() ? "emply" : out;
    }
     
    private static void changePack(File dir, String pack, String finalPack) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(dir.getPath()));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();

                FileWriter fichero = null;
                PrintWriter pw = null;
                try {
                    fichero = new FileWriter(dir.getPath());
                    pw = new PrintWriter(fichero);
                    pw.println(everything.replace("package " + pack, "package " + finalPack));
                    pw.close();
                } catch (IOException e) {
                } finally {
                    try {
                        if (null != fichero) {
                            fichero.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return;

            } finally {
                br.close();
            }
        } catch (Exception e) {
        }
    }
    
    private static void changeImport(File dir, String pack, String finalPack) {
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    changeImport(listFile[i], pack, finalPack);
                } else {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(listFile[i].getPath()));
                        try {
                            StringBuilder sb = new StringBuilder();
                            String line = br.readLine();

                            while (line != null) {
                                sb.append(line);
                                sb.append(System.lineSeparator());
                                line = br.readLine();
                            }
                            String everything = sb.toString();

                            FileWriter fichero = null;
                            PrintWriter pw = null;
                            try {
                                fichero = new FileWriter(listFile[i].getPath());
                                pw = new PrintWriter(fichero);
                                pw.println(everything.replace("import " + pack,"import " + finalPack));
                            } catch (IOException e) {
                            } finally {
                                try {
                                    if (null != fichero) {
                                        fichero.close();
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                            return;

                        } finally {
                            br.close();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
    //==========================================================================
     
    //MANEJO DE RECURSOS
    private static void makeRes(Json js){
        if (js.getString("res") != null) {
            Json jso = js.getJSON("res");
            File resFolder = new File(System.getProperty("user.dir") + "\\src\\" + "res");
            if (!resFolder.exists()) {
                resFolder.mkdirs();
            }
            
            if (jso.getArray("img") != null) {
               File f = new File(resFolder.getPath() + "\\img");
               if (!f.exists()) {
                   f.mkdirs();
               }
            }
            if (jso.getArray("raw") != null) {
                File f = new File(resFolder.getPath() + "\\raw");
                if (!f.exists()) {
                   f.mkdirs();
                }
            }
            if (jso.getArray("layout") != null) {
                File f = new File(resFolder.getPath() + "\\layout");
                if (!f.exists()) {
                   f.mkdirs();
                }
            }

            moveRes(new File(System.getProperty("user.dir") + "\\src\\"),jso);
        }
    }
    
    private static void moveRes(File dir,Json js) {
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    moveRes(listFile[i],js);
                } else {
                    for (Object o : js.getArray("raw")){
                        String ext = getFileExtension(listFile[i]);
                        if (o.toString().equals(ext)) {
                            moveCmd(listFile[i].getPath(),System.getProperty("user.dir") + "\\src\\res\\raw\\"+listFile[i].getName());
                        }
                    }
                    
                    for (Object o : js.getArray("img")){
                        String ext = getFileExtension(listFile[i]);
                        if (o.toString().equals(ext)) {
                            moveCmd(listFile[i].getPath(),System.getProperty("user.dir") + "\\src\\res\\img\\"+listFile[i].getName());
                        }
                    }
                    
                    for (Object o : js.getArray("layout")){
                        String ext = getFileExtension(listFile[i]);
                        if (o.toString().equals(ext)) {
                            moveCmd(listFile[i].getPath(),System.getProperty("user.dir") + "\\src\\res\\layout\\"+listFile[i].getName());
                        }
                    }
                    
                }
            }
        }
    }
    
    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
    
    //==========================================================================
    
    private static boolean moveCmd(String inf ,String outf) {
        try {  
            Process p = Runtime.getRuntime().exec("cmd /C MOVE "+ inf +" "+outf);  
            BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));  
            String line = null;
            String out = "";
            while ((line = in.readLine()) != null) {  
                out = out + line;  
            }  
            return !out.isEmpty();
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return false;
    }
    
    
    
    
    
    
    
    private static void clearMain(File dir){
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    clearMain(listFile[i]);
                } else {
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(listFile[i].getPath()));
                        try {
                            StringBuilder sb = new StringBuilder();
                            String line = br.readLine();

                            while (line != null) {
                                sb.append(line);
                                sb.append(System.lineSeparator());
                                line = br.readLine();
                            }
                            String everything = sb.toString();
                            if (everything.contains("public static void main")) {
                                FileWriter fichero = null;
                                PrintWriter pw = null;
                                try {
                                    fichero = new FileWriter(listFile[i].getPath());
                                    pw = new PrintWriter(fichero);
                                    pw.println(everything.replace("Neuron.init();", "// Neuron.init();").replace("import org.javabrain.Neuron;","import org.javabrain.Neuron; //If you don't use erase this."));
                                } catch (IOException e) {
                                } finally {
                                    try {
                                        if (null != fichero) {
                                            fichero.close();
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                return;
                            }
                        } finally {
                            br.close();
                        }
                    } catch (Exception e) {
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
     
}
