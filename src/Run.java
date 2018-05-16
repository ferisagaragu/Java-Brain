import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;


public class Run {

    public static void main(String[] args) {

        Json json = new Json("{viwer.json}");

        Console.viewer(json);
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
