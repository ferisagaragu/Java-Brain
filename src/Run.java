
import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;
import org.javabrain.util.web.service.Valuta;
import org.javabrain.util.web.service.res.Dottydots;

public class Run {

    public static void main(String[] args) {

        Json json = new Json("org.javabrain.test.{test.json}");
        Console.blue(Json.parseJson(json.toList()));
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
