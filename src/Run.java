import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;


public class Run {

    public static void main(String[] args) {

        System.out.println("test");
        Json json = new Json("[DB.nueva]");

        Console.black(json.use("acciones"));

    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
