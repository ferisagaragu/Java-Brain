package org.javabrain.fx.structure;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Form {

    public static void clear(Node... node) {

        for (Node n : node) {

            try {

                TextField field = (TextField) n;
                field.setText("");

            } catch (Exception e) {}

            try {

                CheckBox field = (CheckBox) n;
                field.setSelected(false);

            } catch (Exception e) {}

            try {

                ListView<Object> field = (ListView) n;
                field.getItems().clear();

            } catch (Exception e) {}

        }

    }

}
