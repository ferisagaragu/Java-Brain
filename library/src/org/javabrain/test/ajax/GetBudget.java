package org.javabrain.test.ajax;

import org.javabrain.annotation.ajax.*;
import org.javabrain.annotation.ajax.Error;
import org.javabrain.annotation.ajax.Process;
import org.javabrain.util.data.Json;
import org.javabrain.util.web.service.Petition;

/**
 * @author Fernando García
 */
@Ajax
public class GetBudget {

    @Response
    private boolean pass = false;

    private Json resp;

    @Postconstruct
    private void postconstructor() {

    }
    
    @Process
    private void process() {
        resp = Petition.doGet("https://budget.webcindario.com/getbudgets.php").JSON;
        pass = true;
    }
    
    @Success
    private void success(){
        System.out.println("Termino la pimer peticion con respuesta: -->");
        System.out.println(resp.toString());
    }
    
    @Error
    private void error(){
        System.out.println("Error");
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
