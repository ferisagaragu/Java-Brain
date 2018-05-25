
import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;

public class Run {

    public static void main(String[] args) {

        Json json1 = new Json("org.javabrain.test.{new.json}");
        Json json2 = new Json("org.javabrain.test.{new2.json}");
        json1.join(json2,"id");
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
