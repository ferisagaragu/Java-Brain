import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;
import org.javabrain.util.resource.Path;
import org.json.simple.parser.ParseException;


public class Run {

    public static void main(String[] args) throws ParseException {

        Json json = new Json(Path.get("org.javabrain.util.data.test","json"));

        /*Json json1 = new Json();
        Json out = new Json();
        json1.put("marrufo","alexa");
        json1.put("alexa","no manches");*/

        //Console.red(json.isJSONArray()); //esta bien por que este no es el elemento al que se debe llamar si no al putObjectInJSONArray
        // mal -> {"item2":"hola amiga","item1":"hola amigo","array":[{"item1array1":"soy el item 1","item2array1":"soy el item2"},{"item1array2":"soy el item 1","item2array2":"soy el item2"},{"item2array3":"soy el item2","item1array3":"soy el item 1"},{"marrufo":"alexa"},{"alexa":"no manches"}],"item4":1,"item3":"10.04.2010 1:12:13 PM"}
        // bien por que no son Jsons separados es uno solo -> {"item2":"hola amiga","item1":"hola amigo","array":[{"item1array1":"soy el item 1","item2array1":"soy el item2"},{"item1array2":"soy el item 1","item2array2":"soy el item2"},{"item2array3":"soy el item2","item1array3":"soy el item 1"},{"marrufo":"alexa","alexa":"no manches"}],"item4":1,"item3":"10.04.2010 1:12:13 PM"}

        /*json.val("other","Esto es lo mejor del mundo");
        /*Json out = *///json.val("nameMy","Fernando");
        /*json.val("nameYou","Frida");*/

        Console.red(json.getJSONArray(0).getString("item1"));
        json.val("other","<3");
       Console.code(json);
        Console.red(json.getJSONArray(0).getString("item1"));
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
