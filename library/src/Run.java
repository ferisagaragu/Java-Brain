
import java.util.List;
import org.javabrain.test.pojo.BudGet;
import org.javabrain.util.data.Json;

public class Run{
    
    public static void main(String[] args) throws Exception {
        
        List<BudGet> list = Json.inject(BudGet.class);
        
        list.forEach(o -> {
            System.out.println(o.getId());
            System.out.println(o.getName());
            System.out.println(o.getMoney());
            System.out.println(o.getDate());
            System.out.println(o.getType());
            System.out.println(o.getData() != null ? o.getData().getString("nuevo") : "NO HAY DATOS");
        });
        
    }

}
