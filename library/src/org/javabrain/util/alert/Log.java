package org.javabrain.util.alert;

import org.javabrain.util.data.Json;

public class Log {

    private static Json data = new Json("conf.{neuron.json}");

    public static void message(Object obj){
        if (data.getJSON("message").getBoolean("info")) {
            System.out.println("\033[34m" + structur("Info -> " + obj) + "\033[30m");
        }
    }

    public static void alert(Object obj) {
        if (data.getJSON("message").getBoolean("alert")) {
            System.out.println("\033[33m" + structur("Warning -> " + obj) + "\033[30m");
        }
    }

    public static void error(Object obj) {
        if (data.getJSON("message").getBoolean("error")) {
            System.out.println("\033[31m" + structur("Error -> " + obj) + "\033[30m");
        }
    }
    
    public static void error(Object obj,Object detail) {
        if (data.getJSON("message").getBoolean("error")) {
            System.out.println("\033[31m" + structur("Error -> " + obj) + "\033[30m");
            System.out.println("\033[31m" + structur("Detail -> " + detail) + "\033[30m");
        }
    }

    public static void viewer(Object obj) {

    }


    //METODOS PRIVADOS
    private static String structur(Object message) {
        String out = "emply";

        if (Json.isJSON(message)){
            out = Json.parseJson(message).toJSONString().replace("<3", "❤").replace(":)", "☺")
                    .replace(":(", "☹").replace("<-", "←")
                    .replace("->", "→");
        } else {

            try {
                out = message.toString().replace("<3", "❤").replace(":)", "☺")
                        .replace(":(", "☹").replace("<-", "←")
                        .replace("->", "→");
            } catch (Exception e) {}
        }

        return out;
    }
}
