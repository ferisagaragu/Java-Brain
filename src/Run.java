import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;
import org.javabrain.util.resource.Path;
import org.json.simple.parser.ParseException;


public class Run {

    public static void main(String[] args) throws ParseException {

        Json json = new Json(Path.get("org.javabrain.util.data.test","json"));
        Json json1 = new Json();
        Json out = new Json();
        json1.put("marrufo","alexa");
        json1.put("alexa","no manches");



        //json1.putJSONArray("arra2",json1);

        out.putJSONArray("array2", json1);
        Console.red(out.toMap());
        Console.red(json.putJSONInJSONArray("array",out)); //esta bien por que este no es el elemento al que se debe llamar si no al putObjectInJSONArray
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
