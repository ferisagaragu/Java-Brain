import org.javabrain.test.ajax.GetBudget;
import org.javabrain.test.ajax.PutBudget;
import org.javabrain.test.pojo.BudGet;
import org.javabrain.util.web.service.Ajax;

public class Run{
    
    public static void main(String[] args) throws Exception {
        GetBudget getBudget = new GetBudget();

        BudGet budGet = new BudGet();
        budGet.setName("Peticion multiple 2018");
        budGet.setDate("07/10/2018");
        PutBudget putBudget = new PutBudget(budGet);


        Ajax.petitions(getBudget,getBudget);
        System.out.println("Nuevo proceso");
    }

}
