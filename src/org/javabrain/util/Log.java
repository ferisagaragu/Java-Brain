package org.javabrain.util;

import org.javabrain.util.data.Json;

public class Log {

    public static void log(Object obj){
        System.out.println(structur(obj));
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
