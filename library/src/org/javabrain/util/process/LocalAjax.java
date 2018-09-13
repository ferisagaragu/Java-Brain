package org.javabrain.util.process;

import java.util.HashMap;
import java.util.Map;

public class LocalAjax {

    private Map<Object,Object> objects = new HashMap<>();

    public void ajax(){

        Thread ajax = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });
        ajax.start();
    }

    public void process() {}

    public void postconstruct() {}

    public void success() {}

    public void error() {}

    public void always() {}

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
