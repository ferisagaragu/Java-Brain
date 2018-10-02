
import org.javabrain.annotation.json.Identify;
import org.javabrain.annotation.json.Json;
import org.javabrain.annotation.json.Key;

/**
 *
 * @author Fernando García
 */
@Json(delete = "https://budget.webcindario.com/deletebudgets.php")
public class Budget {
    
    @Identify
    @Key(name = "id")
    private int id;
    
    @Key(name = "name")
    private String name;
    
    @Key(name = "date")
    private String date;

    @Key(name = "money")
    private String money;
    
    @Key(name = "type")
    private int type;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
