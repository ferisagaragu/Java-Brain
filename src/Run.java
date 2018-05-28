
import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Run {

    public static void main(String[] args) {

        ArrayList list = new ArrayList();
        list.add("nuevo 1");
        list.add("nuevo 2");


        Json json1 = new Json("org.javabrain.test.{new.json}");
        Json json2 = new Json("org.javabrain.test.{new2.json}");
        Json json = new Json(list);
        Console.viewer(json);
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
