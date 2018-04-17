package org.javabrain.util.resource;

import org.javabrain.util.data.Json;

import javax.swing.*;
import java.io.*;
import java.net.URL;

/***
 * @author Fernando García
 * @version 0.0.1
 */
public class Path {

    private static Pth pth = new Pth();

    public static String PROYECT = System.getProperty("user.dir")+"\\";

    public static ImageIcon getRes(String resName){

        String out = "";

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(pth.getPath("/config/neuron.json"), "utf-8"));
            String sCadena = "";

            while ((sCadena = in.readLine())!=null) {
                out += sCadena;
            }

            Json json = new Json(out);
            json.setJSON(json.getObject("path"));
            out = json.getString("res").replace(".","/");

            if(out.charAt(0) != '/'){
                out = "/"+out;
            }
            if (out.charAt(out.length()-1) != '/'){
                out = out+"/";
            }

        }catch (Exception e){}

        return new ImageIcon(pth.getPathRes(out+resName));
    }

    public static InputStream getFile(String fileName){

        String out = "";

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(pth.getPath("/config/neuron.json"), "utf-8"));
            String sCadena = "";

            while ((sCadena = in.readLine())!=null) {
                out += sCadena;
            }

            Json json = new Json(out);
            json.setJSON(json.getObject("path"));
            out = json.getString("file").replace(".","/");

            if(out.charAt(0) != '/'){
                out = "/"+out;
            }
            if (out.charAt(out.length()-1) != '/'){
                out = out+"/";
            }

        }catch (Exception e){}

        return pth.getPath(out+fileName);
    }

    public static InputStream getJson(String fileName){

        String out = "";

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(pth.getPath("/config/neuron.json"), "utf-8"));
            String sCadena = "";

            while ((sCadena = in.readLine())!=null) {
                out += sCadena;
            }

            Json json = new Json(out);
            json.setJSON(json.getObject("path"));
            out = json.getString("json").replace(".","/");

            if(out.charAt(0) != '/'){
                out = "/"+out;
            }
            if (out.charAt(out.length()-1) != '/'){
                out = out+"/";
            }

        }catch (Exception e){}

        return pth.getPath(out+fileName);
    }

    public static InputStream get(String path,String extention){
        path = path.replace(".","/");

        if(path.charAt(0) != '/'){
            path = "/"+path;
        }
        return pth.getPath(path+"."+extention);
    }

}

class Pth{

    public InputStream getPath(String path){
        return getClass().getResourceAsStream(path);
    }

    public URL getPathRes(String path){
        return getClass().getResource(path);
    }
}
