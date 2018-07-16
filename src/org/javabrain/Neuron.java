package org.javabrain;

import org.javabrain.util.data.Json;

public class Neuron {

    public static String param(Object key){
        Json json = new Json("conf.{neuron.json}");
        return json.getJSON("param").getString(key.toString());
    }

}
