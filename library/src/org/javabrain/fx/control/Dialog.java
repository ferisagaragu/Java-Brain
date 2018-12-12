package org.javabrain.fx.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.awt.*;
import javafx.stage.StageStyle;

/**
 *
 * @author Fernny Rerptar Mcfly
 */
public class Dialog {

    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;
    private Parent parent;

    public Dialog(String fxml, Stage st, String title, Image icon, String css) {
        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            parent = login;
            scene = new Scene(login);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(st);
            stage.setTitle(title);
            stage.getIcons().add(icon);
            scene.getRoot().getStylesheets().add(css);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Dialog(String fxml, Stage st, String title, Image icon) {
        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            parent = login;
            scene = new Scene(login);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(st);
            stage.setTitle(title);
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Dialog(String fxml, Stage st, String title, String css) {
        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            scene = new Scene(login);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(st);
            stage.setTitle(title);
            scene.getRoot().getStylesheets().add(css);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Dialog(String fxml, Stage st, String title) {
        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            scene = new Scene(login);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(st);
            stage.setTitle(title);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Dialog(String fxml, Stage st) {
        try {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            scene = new Scene(login);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(st);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Object getFXML() {
        Object dialog = loader.getController();

        return dialog;
    }

    public void showOnCenter() {
        stage.show();
        //Estas lineas son para centrar las ventanas
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(d.width / 2 - (stage.getWidth() / 2));
        stage.setY(d.height / 2 - (stage.getHeight() / 2));
    }

    public void hide() {
        stage.hide();
    }

    public void close() {
        stage.close();
    }

    public void show() {
        stage.show();
    }

    public void showAndWait() {
        stage.showAndWait();
    }

    public void showAndWaitOnCenter() {
        showOnCenter();
        stage.hide();
        stage.showAndWait();
    }

    public void FullScreen(boolean bool) {
        stage.setFullScreen(bool);
    }

    public Stage getStage() {
        stage.setScene(scene);

        return stage;
    }

    public void OnCenter() {
        stage.show();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(d.width / 2 - (stage.getWidth() / 2));
        stage.setY(d.height / 2 - (stage.getHeight() / 2));
        stage.hide();
    }

    /**
     * *
     * Es el tipo de ventana que solo tiene el botón se salir
     */
    public void windowUtility() {
        stage.initStyle(StageStyle.UTILITY);
    }

    public void setWindowModal(Stage st) {
        stage.initOwner(st);
    }

    public Parent getParent() {
        return parent;
    }   
}
