import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;
import org.javabrain.util.resource.Path;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class Run {
    public static void main(String[] args) throws ParseException {

        Json json = new Json(Path.getExternal("doc.json","json",""));
        ArrayList<Json> jsons = json.getJSON("method").getJSON("get").values();

        for(Json json1:jsons){
            Console.green(json1.getString("name")+" -> "+json1.getJSONArray("parameter",0).getString("type"));
        }

        json.put("accion",123);
        Console.blue(json);

    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
