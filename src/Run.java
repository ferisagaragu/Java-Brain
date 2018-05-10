import java.io.File;
import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;


public class Run {

    public static void main(String[] args) {

        Json json = new Json("[{\n" +
"  \"item1\":\"hola amigo :)\",\n" +
"  \"item2\":\"hola amiga\",\n" +
"  \"item3\":\"el perro tiene el nombre {other}\",\n" +
"  \"item4\":1,\n" +
"  \"item5\":[{\"nuevo\": \"mas valor = {nameMy} + {nameYou} :) -> :) <-\",\"otroitem\":[{\"este mas\":123}]}]\n" +
"}]");
            
        Console.black(json);
       
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
