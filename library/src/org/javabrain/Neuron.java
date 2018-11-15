package org.javabrain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.javabrain.fx.control.Frame;
import org.javabrain.util.data.Json;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import org.javabrain.util.alert.Log;

import javax.swing.*;
import org.javabrain.util.resource.Archive;

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
            NeuronConf.show();
        } else {
            try {
                Json js = new Json(str.isEmpty() ? "/conf/neuron.json" : str);
                makeModules(js);
                makeRes(js);
                coutBuilds(js);
                //Esta parte no funcional totalmente bien
                //clearMain(new File(System.getProperty("user.dir") + "\\src\\"));
            } catch (Exception e) {}
        }
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
        Archive.write(dir, Archive.read(dir).replace("package " + pack, "package " + finalPack));
    }

    private static void changeImport(File dir, String pack, String finalPack) {
        File listFile[] = dir.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    changeImport(listFile[i], pack, finalPack);
                } else {
                    Archive.write(listFile[i],Archive.read(listFile[i]).replace("import " + pack,"import " + finalPack));
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
            if (jso.getArray("style") != null) {
                File f = new File(resFolder.getPath() + "\\style");
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
                    
                    for (Object o : js.getArray("style")){
                        String ext = getFileExtension(listFile[i]);
                        if (o.toString().equals(ext)) {
                            moveCmd(listFile[i].getPath(),System.getProperty("user.dir") + "\\src\\res\\style\\"+listFile[i].getName());
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
                                    pw.println(everything.replace("// Neuron.init();", "// // Neuron.init();").replace("import org.javabrain.Neuron; //If you don't use erase this.","//If you don't use erase this."));
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

    public static void coutBuilds(Json js){
        try {
            int count = js.getInteger("build") == -1 ? 1 : js.getInteger("build") + 1;
            js.put("build", count);
            js.write("/conf/neuron.json");
        } catch (Exception e) {}
    }
    
    public static Object param(Object key){
        Json json = new Json("conf.{neuron.json}");
        System.out.println(json.convertParams());
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

class NeuronConf {

    private void initAndShowGUI() {
        JFrame frame = new JFrame("Neuron config");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(600, 425);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(getClass().getResource("/res/component/json.png")).getImage());
        Platform.runLater(() -> {
            initFX(fxPanel);
        });
    }

    private void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene() {
        Frame frame = new Frame("/org/javabrain/neuron.fxml");
        StackPane root = (StackPane) frame.getStage().getScene().getRoot();
        VBox principal = (VBox) root.getChildren().get(0);
        JFXButton okBtn = (JFXButton) principal.getChildren().get(2);

        JFXCheckBox alertChk = null;
        JFXCheckBox errorChk = null;
        JFXCheckBox infoChk = null;

        JFXTextField packFld = null;
        JFXTextField comFld = null;

        for (Node com :((HBox)((VBox)principal.getChildren().get(0)).getChildren().get(1)).getChildren()) {

            switch (((JFXCheckBox)com).getText()) {
                case "Alert" : alertChk = (JFXCheckBox) com; break;
                case "Error" : errorChk = (JFXCheckBox) com; break;
                case "Info"  : infoChk = (JFXCheckBox) com; break;
            }

        }

        for (Node com : ((VBox)principal.getChildren().get(1)).getChildren()) {

            if (!com.getClass().getName().equals("javafx.scene.control.Label")) {
                switch (((JFXTextField) com).getPromptText()) {
                    case "Package name": packFld = (JFXTextField) com; break;
                    case "Modules": comFld = (JFXTextField) com; break;
                }
            }

        }

        JFXTextField finalComFld = comFld;
        JFXCheckBox finalAlertChk = alertChk;
        JFXTextField finalPackFld = packFld;
        JFXCheckBox finalErrorChk = errorChk;
        JFXCheckBox finalInfoChk = infoChk;

        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String[] mod = finalComFld.getText().toLowerCase().split(",");
                String out = "";
                for (String val : mod) {
                    out = out + "'" + val + "',";
                }
                out = out.substring(0, out.length() - 1);

                String data = "{'message':{'alert':" + finalAlertChk.isSelected() + ",'error':" + finalErrorChk.isSelected() + ",'info':" + finalInfoChk.isSelected() + "},"
                        + "'package':{'company':'" + finalPackFld.getText() + "','modules':[" + out + "]},'param':'$(conf.{param.json})','res':{'img':['.jpg','.png','.gif'],'raw':['.mp3','.mp4'],'layout':['.xml','.html','.txt'],'style':['.css']},"
                        + "'build': 0,'drawable':''}";

                File folder = new File(System.getProperty("user.dir") + "\\src\\conf");
                File file = new File(folder.getPath() + "\\neuron.json");
                String str = Json.parseJson(data).toJSONString();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()))) {
                    writer.write(str);
                } catch (IOException ex) {
                    Log.error(ex.getMessage());
                }

                Json js = new Json(str.isEmpty() ? "/conf/neuron.json" : str);
                makeModules(js);
                makeRes(js);
                clearMain(new File(System.getProperty("user.dir") + "\\src\\"));
                Archive.write("./conf/param.json","{}");
                System.exit(0);
            }
        });

        return frame.getStage().getScene();
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
            Process p = Runtime.getRuntime().exec("cmd /C MOVE " + inf + " " + outf);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
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
                                    pw.println(everything.replace("// Neuron.init();", "// // Neuron.init();").replace("import org.javabrain.Neuron; //If you don't use erase this.","import org.javabrain.Neuron; //If you don't use erase this. //If you don't use erase this."));
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

  
    public static void show() {
        SwingUtilities.invokeLater(() -> {
            NeuronConf jv = new NeuronConf();
            jv.initAndShowGUI();
        });
    }

}

