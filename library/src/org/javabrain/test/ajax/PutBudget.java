package org.javabrain.test.ajax;

import org.javabrain.annotation.ajax.Ajax;
import org.javabrain.annotation.ajax.Error;
import org.javabrain.annotation.ajax.Postconstruct;
import org.javabrain.annotation.ajax.Process;
import org.javabrain.annotation.ajax.Success;
import org.javabrain.test.pojo.BudGet;
import org.javabrain.util.data.Json;

@Ajax
public class PutBudget {

    private boolean resp;
    private BudGet budGet;

    public PutBudget(BudGet budGet) {
        this.budGet = budGet;
    }

    @Postconstruct
    private void postconstructor() {}

    @Process
    private void process() {
        resp = Json.save(budGet);
    }

    @Success
    private void success() {}

    @Error
    private void error() {}

}
