package org.javabrain.fx.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fernando Isaías García Aguirre
 * @version 0.0.1
 */
public class Drawable {

    private FXMLLoader loader;

    public Drawable(String fxml)
    {
        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            loader.load();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

    public Object getFXML() {
        return loader.getController();
    }

}
