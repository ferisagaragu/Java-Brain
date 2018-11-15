package org.javabrain.util.code;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.javabrain.util.resource.Archive;

/**
 *
 * @author Fernando García
 * @version 0.0.1
 */
public class JavaScript {
    
    private ScriptEngineManager script = new ScriptEngineManager();
    private ScriptEngine js = script.getEngineByName("JavaScript");
    private Invocable inv;
    
    public JavaScript(String path) {
        
        try {
            String data = Archive.road(path);

            if (data.charAt(0) != '/') {
                data = "/" + data;
            }

            Reader targetReader = new InputStreamReader(getClass().getResourceAsStream(data));
            js.eval(targetReader);
            inv = (Invocable) js;
            
        } catch (ScriptException ex) {
            System.err.println(ex.getMessage());
        } 
    
    }
    
    public Object invokeFunction(String name,Object... args) {
        try {
            return inv.invokeFunction(name,args);
        } catch (ScriptException | NoSuchMethodException ex) {
            Logger.getLogger(JavaScript.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
