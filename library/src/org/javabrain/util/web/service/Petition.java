package org.javabrain.util.web.service;

import org.javabrain.util.data.Type;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Fernando García
 * @version 0.0.3
 */
public class Petition {

    public static Type doGet(String url){
        URL urlIn;
        try {

            urlIn = new URL(url);
            URLConnection con = urlIn.openConnection();
            HttpURLConnection conn = (HttpURLConnection) urlIn.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

            String linea = "";
            String out = "";
            while ((linea = in.readLine()) != null) {
                out += linea;
            }

            return new Type(out,conn.getResponseCode());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return new Type("",500);
    }
    
    public static Type doDelete(String url, Map<Object,Object> params){
        try {
            URL urlIn = new URL(url);
            StringBuilder postData = new StringBuilder();
            
            for (Map.Entry<Object, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey().toString(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                        "UTF-8"));
            }
            
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            
            HttpURLConnection conn = (HttpURLConnection) urlIn.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String result  = "";
            for (int c = in.read(); c != -1; c = in.read()) {
                result += String.valueOf((char) c);
            }

            return new Type(result,conn.getResponseCode());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Type("",500);
    }
    
    public static Type doPut(String url, Map<Object, Object> params) {
        try {
            URL urlIn = new URL(url);
            StringBuilder postData = new StringBuilder();

            for (Map.Entry<Object, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey().toString(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                        "UTF-8"));
            }

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) urlIn.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String result = "";
            for (int c = in.read(); c != -1; c = in.read()) {
                result += String.valueOf((char) c);
            }

            return new Type(result,conn.getResponseCode());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Type("",500);
    }
    
    public static Type doPost(String url, Map<Object,Object> params){
        try {
            URL urlIn = new URL(url);
            StringBuilder postData = new StringBuilder();
            
            for (Map.Entry<Object, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey().toString(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                        "UTF-8"));
            }
            
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            
            HttpURLConnection conn = (HttpURLConnection) urlIn.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String result  = "";
            for (int c = in.read(); c != -1; c = in.read()) {
                result += String.valueOf((char) c);
            }

            return new Type(result,conn.getResponseCode());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Type("",500);
    }

    /**
     * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
     * EJM:
     * 		200 OK			300 Multiple Choices
     * 		301 Moved Permanently	305 Use Proxy
     * 		400 Bad Request		403 Forbidden
     * 		404 Not Found		500 Internal Server Error
     * 		502 Bad Gateway		503 Service Unavailable
     * @param url
     * @return Status Code
     */
    public static int getStatusConnectionCode(String url) {
        Connection.Response response = null;
        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(10000).ignoreHttpErrors(true).execute();
        } catch (Exception ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode() != 404 ? response.statusCode() : 404;
    }

    /**
     * Con este método devuelvo un objeto de la clase Document con el contenido del
     * HTML de la web que me permitirá parsearlo con los métodos de la librelia JSoup
     * @param url
     * @return Documento con el HTML
     */
    public static Document getHtmlDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(1000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }
        return doc;
    }
    
    public static void openURL(String url) {
        String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (osName.startsWith("Mac OS X")) {
                // Runtime.getRuntime().exec("open -a safari " + url);
                // Runtime.getRuntime().exec("open " + url + "/index.html");
                Runtime.getRuntime().exec("open " + url);
            } else {
                System.out.println("Please open a browser and go to "+ url);
            }
        } catch (IOException e) {
            System.out.println("Failed to start a browser to open the url " + url);
            e.printStackTrace();
        }
    }
    
    public static void download(String url,String path) {
        try {
            String name = url;
            String fileName[] = name.split("/");
            name = fileName[fileName.length - 1];
            URL urli = new URL(url);
            URLConnection urlCon = urli.openConnection();
            InputStream is = urlCon.getInputStream();
            FileOutputStream fos = new FileOutputStream(path);

            byte[] array = new byte[1000];
            int leido = is.read(array);
            while (leido > 0) {
                fos.write(array, 0, leido);
                leido = is.read(array);
            }

            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}