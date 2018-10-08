package org.javabrain.test.ajax;

import org.javabrain.annotation.ajax.*;
import org.javabrain.annotation.ajax.Error;
import org.javabrain.annotation.ajax.Process;
import org.javabrain.util.data.Json;

@Ajax
public class DeleteBudget {

    private boolean resp;

    @Postconstruct
    private void postconstructor() {

    }

    @Process
    private void process() {
        resp = Json.delete(null);
    }

    @Success
    private void success() {
        System.out.println(resp ? "Todo bien!" : "Nada salio bien");
    }

    @Error
    private void error() {

    }

}
