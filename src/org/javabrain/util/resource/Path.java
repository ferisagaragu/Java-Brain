package org.javabrain.util.data;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;


/**
 * @author Fernando García
 * @version 0.0.1
 */
public class Path {
    
    
    public static InputStream get(String brainPath){

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        
        try{
            
            if(brainPath.contains("\\")){
                return (InputStream) new FileInputStream(brainPath);
            }
            
            //Validacion de cadenas internas con ->.{} 
            if(brainPath.contains("{")){
               String[] sub = brainPath.split("\\{");
               String firsPath = sub[0].replace(".","/");
               String endPath = sub[1].replace("{","").replace("}","");
               brainPath = firsPath + endPath;
            }
            
            //Validacion de cadenas internas con -> / 
            switch(brainPath.charAt(0)){
                case '/': return classLoader.getResourceAsStream(brainPath.substring(1,brainPath.length()));
                default: return classLoader.getResourceAsStream(brainPath);
            }
            
        }catch(Exception e){}
        
        return null;
    }
    
    public static URL getRes(String brainPath){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        
        try{
            
            if(brainPath.contains("\\")){
                return new java.io.File(brainPath).toURL();
            }
            
            //Validacion de cadenas internas con ->.{} 
            if(brainPath.contains("{")){
               String[] sub = brainPath.split("\\{");
               String firsPath = sub[0].replace(".","/");
               String endPath = sub[1].replace("{","").replace("}","");
               brainPath = firsPath + endPath;
            }
            
            //Validacion de cadenas internas con -> / 
            switch(brainPath.charAt(0)){
                case '/': return classLoader.getResource(brainPath.substring(1,brainPath.length()));
                default: return classLoader.getResource(brainPath);
            }
            
        }catch(Exception e){}
        
        return null;
    }
}
