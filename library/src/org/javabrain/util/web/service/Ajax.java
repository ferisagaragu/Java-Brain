package org.javabrain.util.web.service;

import org.javabrain.util.data.Json;
import org.javabrain.util.data.Type;

import java.util.HashMap;
import java.util.Map;

/***
 * @author Fernando Isaías García Aguirre
 * @version 0.0.2
 * @apiNote Esta clase de Ajax sustituira a Data pesist
 * @implNote esta clase funciona en FX con Platform.runLater(() -> {});
 */
public class Ajax {

    private Map<Object,Object> params = new HashMap<>();
    private Map<Object,Object> objects = new HashMap<>();

    private String url = "";
    private String urlPost = "";
    private String urlPut = "";
    private String urlDelete = "";

    public void ajax(){
        get(url);
        post(urlPost,params);
        put(urlPut,params);
        delete(urlDelete,params);
    }

    public void postconstruct() {}

    public void get(String url) {

        String resp = getURLAjax(url);
        if (resp != null) {
            url = resp;
        }

        if (!url.equals("")) {
            String finalUrl = url;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    postconstruct();
                    Type resp = Petition.doGet(finalUrl);
                    if (resp.STATUS.toString().equals("200")) {
                        success(resp);
                    } else {
                        error(resp, Integer.parseInt(resp.STATUS.toString()));
                    }

                    always();
                }
            });
            thread.start();
        }
    }

    public void post(String url, Map<Object,Object> params) {

        String resp = getURLAjax(url);
        if (resp != null) {
            url = resp;
        }

        urlPost = url;
        if (!url.equals("")) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    postconstruct();
                    Type resp = Petition.doPost(urlPost, params);
                    if (resp.STATUS.toString().equals("200")) {
                        success(resp);
                    } else {
                        error(resp, Integer.parseInt(resp.STATUS.toString()));
                    }
                    always();
                    params.clear();
                }
            });
            thread.start();

        }
    }

    public void put(String url, Map<Object,Object> params) {

        String resp = getURLAjax(url);
        if (resp != null) {
            url = resp;
        }

        urlPut = url;
        if (!url.equals("")) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    postconstruct();
                    Type resp = Petition.doPut(urlPut, params);
                    if (resp.STATUS.toString().equals("200")) {
                        success(resp);
                    } else {
                        error(resp, Integer.parseInt(resp.STATUS.toString()));
                    }
                    always();
                    params.clear();
                }
            });
            thread.start();

        }
    }

    public void delete(String url, Map<Object,Object> params) {

        String resp = getURLAjax(url);
        if (resp != null) {
            url = resp;
        }

        urlDelete = url;
        if (!url.equals("")) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    postconstruct();
                    Type resp = Petition.doDelete(urlDelete, params);
                    if (resp.STATUS.toString().equals("200")) {
                        success(resp);
                    } else {
                        error(resp, Integer.parseInt(resp.STATUS.toString()));
                    }
                    always();
                    params.clear();
                }
            });
            thread.start();

        }
    }


    private String getURLAjax(String param) {
        try {
            Json json = new Json("conf.{neuron.json}");
            return json.getJSON("ajax").getString(param);
        } catch (Exception e) {
            return null;
        }
    }


    public void success(Type resp) {}

    public void error(Type resp, int status) {}

    public void always() {}



    public Object putParam(Object key, Object param){
        return params.put(key,param);
    }



    public Object getObject(Object key) {
        return objects.get(key);
    }

    public Object putObject(Object key, Object param){
        return objects.put(key,param);
    }

    public Object removeObject(Object key){
        return objects.remove(key);
    }

    public Object removeObject(int key){
        return objects.remove(key);
    }

    public Object replaceObject(Object key,Object oldParam,Object newParam){
        return objects.replace(key,oldParam,newParam);
    }

    public void clearObject(){
        objects.clear();
    }
}
