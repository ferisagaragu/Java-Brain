import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;
import org.javabrain.util.web.service.Petition;

import java.util.HashMap;
import java.util.Map;

public class Run {
    public static void main(String[] args){

        Json json = new Json("{  \n" +
                "  \"array\":[  \n" +
                "    {  \n" +
                "      \"primero\":\"esto aqui\",\n" +
                "      \"segundo\":\"aqui estaba\"\n" +
                "    }\n,{\"oye\":\"aqui mas\"}" +
                "  ]\n" +
                "}");

        Map map = new HashMap<Object,Object>();
        map.put("tres","asi es uno mas");
        map.put("cuatros","asi es uno mas");
        map.put("numeros",123);
        json.putObjectInJSONArray("array",map,1);
        Console.blue(json);
    }
}
