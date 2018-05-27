
import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;

import java.util.HashMap;
import java.util.Map;

public class Run {

    public static void main(String[] args) {

        Map<Object,Object> map = new HashMap<>();
        map.put("nuevo 1","dato2");
        map.put("nuevo 2","dato3");


        Json json1 = new Json("org.javabrain.test.{new.json}");
        Json json2 = new Json("org.javabrain.test.{new2.json}");
        Json json = new Json(map);
        Console.viewer(json);
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
