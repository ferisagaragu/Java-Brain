package org.javabrain.util.web.service;

import org.javabrain.util.data.Type;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
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
 * @version 0.0.2
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
        return response.statusCode();
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
}