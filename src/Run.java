import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;
import org.javabrain.util.resource.Path;
import org.json.simple.parser.ParseException;

import java.util.*;

public class Run {
    public static void main(String[] args) throws ParseException {

        Json json = new Json(Path.get("org.javabrain.util.data.test","json"));
        Console.blue(json.getJSONArray("array").exclude(0));
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
