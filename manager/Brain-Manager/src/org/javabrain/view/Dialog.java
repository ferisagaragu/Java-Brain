package org.javabrain.view;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Fernando García
 */
public class Dialog {
    
    public static String createElementNameWSDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Añadir elemento");
        dialog.setHeaderText("Nombre del elemento");
        dialog.setContentText("Introduce el nombre de la columna:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public static String editElementNameWSDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Editar elemento");
        dialog.setHeaderText("Nombre del elemento");
        dialog.setContentText("Introduce el nombre de la columna:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public static Optional<ButtonType> deleteElementNameWSDialog() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar elemento");
        alert.setHeaderText("El elemento se eliminara");
        alert.setContentText("¿Estas seguro que deseas eliminar el elemento?");

        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }
    
}
