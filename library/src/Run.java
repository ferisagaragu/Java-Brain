
import org.javabrain.util.data.Json;

public class Run {

    public static void main(String[] args) throws Exception {
        Json json = new Json("conf.{dataTable.json}");
        json.getJSONArray(0).put("nuevo","dato");
        json.values().forEach( e -> {
            System.out.println(e);
        });

    }

}
