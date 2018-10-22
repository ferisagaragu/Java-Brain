package org.javabrain.util.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.javabrain.util.alert.Log;

/**
 *
 * @author Fernando García
 */
public class Archive {
    
    public static String read(String path) {
        
        BufferedReader out;
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        String outStg;
        String str;
        outStg = "";
        
        try (InputStream in = loader.getResourceAsStream(path.charAt(0) == '/' ? path.substring(1, path.length()) : path)) {
            
            out = new BufferedReader(new InputStreamReader(in, "UTF8"));
            out.lines().forEach(o -> {
                System.out.println(o);
            });
            
            while ((str = out.readLine()) != null) {
                outStg = outStg + str;
            }

            return outStg;
            
        } catch (IOException ex) {
            Log.error("Failed to load the file reviews the path that is not being used by another program.",ex);
        }
        
        return null;
    }
    
    
    
}
