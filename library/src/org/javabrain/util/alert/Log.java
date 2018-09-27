package org.javabrain.util.alert;

import org.javabrain.util.data.Json;

public class Log {

    private static Json data = new Json("conf.{neuron_example.json}");

    public static void message(Object obj){
        if (data.getJSON("message").getBoolean("info")) {
            System.out.println("\033[34m" + structur(obj) + "\033[30m");
        }
    }

    public static void alert(Object obj) {
        if (data.getJSON("message").getBoolean("info")) {
            System.out.println("\033[33m" + structur(obj) + "\033[30m");
        }
    }

    public static void error(Object obj) {
        if (data.getJSON("message").getBoolean("error")) {
            System.out.println("\033[31m" + structur(obj) + "\033[30m");
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
                    .replace("->", "→");;
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
