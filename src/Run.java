import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;
import org.javabrain.util.resource.Path;
import org.javabrain.util.web.service.Petition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
