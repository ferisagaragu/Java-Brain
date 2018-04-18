package org.javabrain.util.web.service;

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
 * @version 0.0.1
 */
public class Petition {
    
    public static String doGet(String url){
        URL urlIn;
        try {

            urlIn = new URL(url);
            URLConnection con = urlIn.openConnection();
 
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            String linea = "";
            String out = "";
            while ((linea = in.readLine()) != null) {
                out += linea;
            }
            
            return out;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        return "";
    }
    
    public static String doDelete(String url,Map<Object,Object> params){
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
            
            return result;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static String doPut(String url, Map<Object, Object> params) {
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

            return result;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static String doPost(String url,Map<Object,Object> params){
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
            
            return result;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Petition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
