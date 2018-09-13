package org.javabrain.fx.structure;

import javafx.stage.Stage;

/**
 * @author Fernando García
 * @version 0.0.1
 */
public class Controller {

    public Stage stage;

    public void init(Object... params){
        custom();
        onAction();
    };

    public void custom(){};
    public void onAction(){};

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
