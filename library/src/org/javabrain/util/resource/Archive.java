package org.javabrain.util.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.javabrain.util.alert.Log;


/**
 * @author Fernando García
 * @version 0.0.3
 * @note [] se usara para hacer una ruta relativa fuera de la carpeta src pero dentro de la del proyecto
 */
public class Archive {

    private static final ClassLoader CLASS_LOADER = ClassLoader.getSystemClassLoader();
    
    public static final String PROYECT_PATH = System.getProperty("user.dir");
    public static final String SOURCE_PATH = System.getProperty("user.dir") + "\\src\\";
    
    public static String road(String path) {
        
        String firsPath = "";
        
        //Validacion de cadenas internas con ->.{} 
        if (path.contains("{")) {
            String[] sub = path.split("\\{");
            firsPath = sub[0].replace(".", "/");
            String endPath = sub[1].replace("{", "").replace("}", "");
            path = firsPath + endPath;
        }

        //Valida cadenas internas con [] para crear una ruta en la carpeta del proyecto
        if (path.contains("[")) {
            File f = new File(firsPath.replace("[", ""));
            f.mkdirs();
            path = PROYECT_PATH +"\\"+ path.replace("[", "").replace("]", "").replace("/","\\");
        }
        
        //Validacion de cadenas internas con -> / 
        switch (path.charAt(0)) {
            case '/':
                return path.substring(1, path.length());
            default:
                return path;
        }

    }
    
    public static String read(Object path) {

        if (path.getClass().getName().equals("java.io.File")) {
            path = ((File) path).getPath();
        }
        
        BufferedReader out;
        String outStg;
        String str;
        outStg = "";
        InputStream inn = CLASS_LOADER.getResourceAsStream(road(path.toString()));
        
        try (InputStream in = inn == null ? new FileInputStream(new File(road(path.toString()))) : inn) {
            out = new BufferedReader(new InputStreamReader(in, "UTF8"));
            while ((str = out.readLine()) != null) {
                outStg = outStg + str + "\n";
            }
            return outStg.substring(0, outStg.length() - 1);
        } catch (IOException ex) {
            Log.error("Failed to load the file reviews the path that is not being used by another program.", ex);
        }
        return null;
    }

    public static boolean write(Object path, String text) {
        
        if (path.getClass().getName().equals("java.io.File")) {
            path = ((File) path).getPath();
        }
        
        if (road(path.toString()).contains("/")) {
            path = SOURCE_PATH + road(path.toString()).replace("/","\\");
        }

        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(road(path.toString())), "UTF8"))) {
            out.append(text);
            out.flush();
            out.close();
            return true;
        } catch (IOException ioe) {
            Log.error("Could not create or write the file, check that the route is correct or exists.",ioe);
        }

        return false;
    }
    
    public static String convertToRelative(String path) {
        path = path.replace(Archive.SOURCE_PATH,"");
        path = path.replace("\\","/");
        return path;
    }
    
}
