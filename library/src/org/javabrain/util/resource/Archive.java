package org.javabrain.util.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.javabrain.util.alert.Log;


/**
 * @author Fernando García
 * @version 0.0.3
 */
public class Archive {

    public static final ClassLoader CLASS_LOADER = ClassLoader.getSystemClassLoader();
    
    public static String road(String path) {

        //Validacion de cadenas internas con ->.{} 
        if (path.contains("{")) {
            String[] sub = path.split("\\{");
            String firsPath = sub[0].replace(".", "/");
            String endPath = sub[1].replace("{", "").replace("}", "");
            path = firsPath + endPath;
        }

        //Validacion de cadenas internas con -> / 
        switch (path.charAt(0)) {
            case '/':
                return path.substring(1, path.length());
            default:
                return path;
        }

    }
    
    public static String read(String path) {

        BufferedReader out;
        String outStg;
        String str;
        outStg = "";
        InputStream inn = CLASS_LOADER.getResourceAsStream(Archive.road(path));

        try (InputStream in = inn == null ? new FileInputStream(new File(path)) : inn) {
            out = new BufferedReader(new InputStreamReader(in, "UTF8"));
            while ((str = out.readLine()) != null) {
                outStg = outStg + str + "\n";
            }
            return outStg;
        } catch (IOException ex) {
            Log.error("Failed to load the file reviews the path that is not being used by another program.", ex);
        }
        return null;
    }


}
