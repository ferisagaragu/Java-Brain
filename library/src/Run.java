import org.javabrain.annotation.Json;
import org.javabrain.annotation.JsonType;
import org.javabrain.reflect.JsonEntity;
import org.javabrain.test.JsonMap;

import java.util.List;

public class Run {

    public static void main(String[] args) throws Exception {

        List<JsonMap> maps = JsonEntity.inyect(JsonMap.class);

        maps.forEach( o ->{
            System.out.println(o.getName() + "\n");
            o.getSantuaries().forEach( o2 ->{
                System.out.println(o2.getName());
            });
            System.out.println("\n\n\n");
        });

    }

}
