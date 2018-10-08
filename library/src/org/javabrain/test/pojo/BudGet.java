package org.javabrain.test.pojo;

import org.javabrain.annotation.json.*;

/**
 *
 * @author Fernando García
 */
@Json
@Get( url = "https://budget.webcindario.com/getbudgets.php", type = PetitionType.GET)
@Create( url = "https://budget.webcindario.com/addbudgets.php", type = PetitionType.POST)
@Replace( url = "https://budget.webcindario.com/putbudgets.php", type = PetitionType.POST)
@Delete( url = "https://budget.webcindario.com/deletebudgets.php", type = PetitionType.POST)
public class BudGet {
    
    @Identify
    @Key( name = "id" )
    private int id;
    
    @Key( name = "name" )
    private String name;
    
    @Key( name = "date" )
    private String date;
    
    @Key( name = "money" )
    private String money;
    
    @Key( name = "type" )
    private int type;

    public BudGet() {}
    
    public BudGet(int id, String name, String date, String money, int type) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.money = money;
        this.type = type;
    }

    public BudGet(String name, String date, String money, int type) {
        this.name = name;
        this.date = date;
        this.money = money;
        this.type = type;
    }

    public BudGet(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money == null ? "0 MNX" : money;
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
