
import java.util.List;
import org.javabrain.util.data.Json;

public class Run{
    
    public static void main(String[] args) throws Exception {
        
        // List<Budget> lista = Json.inject(Budget.class);
        Budget budget = new Budget();
        budget.setId(39);
        budget.setName("UPDATE");
        budget.setDate("02/10/2018");
        budget.setMoney("300 MNX");
        budget.setType(1);
        
        System.out.println(/*Json.save(budget)*/ Json.delete(budget));
    }

}
