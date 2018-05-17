package org.javabrain.util.web.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Fernando García
 * @version 0.0.1
 */
public class Translator {

    public static String SPANISH =  "es";
    public static String ENGLISH = "en";
    public static String FRENCH = "fr";
    public static String ITALIAN = "it";
    public static String PORTUGUESE = "pt";
    public static String GERMAN = "de";
    public static String RUSSIAN = "ru";
    public static String ARAB = "ar";
    public static String TURKISH = "tr";
    
    public static String traslate(String origin,String pastto, String text) {
        URL url;
        String linea = "";
        String newline = "";
        BufferedReader in = null;
        try {

            url = new URL("https://statickidz.com/scripts/traductor/?&source=" + origin + "&target=" + pastto + "&q=" + URLEncoder.encode(text, "UTF-8"));
            URLConnection con = url.openConnection();
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((linea = in.readLine()) != null) {
                newline += linea;
            }

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(newline);
            newline = obj.get("translation").toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try{in.close();}catch (Exception e){}
        }

        return newline;
    }

    public static String autoTraslate(String pastto, String text) {
        URL url;
        String linea = "";
        String newline = "";
        BufferedReader in = null;
        try {

            url = new URL("https://statickidz.com/scripts/traductor/?&source=auto&target=" + pastto + "&q=" + URLEncoder.encode(text, "UTF-8"));
            URLConnection con = url.openConnection();
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            while ((linea = in.readLine()) != null) {
                newline += linea;
            }

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(newline);
            newline = obj.get("translation").toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try{in.close();}catch (Exception e){}
        }

        return newline;
    }

}

