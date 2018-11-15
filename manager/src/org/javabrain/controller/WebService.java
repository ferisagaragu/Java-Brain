package org.javabrain.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.javabrain.fx.structure.Controller;
import org.javabrain.model.DoWebService;
import org.javabrain.view.Dialog;

/**
 *
 * @author Fernando García
 */
public class WebService extends Controller{

    @FXML private VBox root;
    @FXML private JFXTextField serverNameFld;
    @FXML private JFXTextField userNameFld;
    @FXML private JFXPasswordField password;
    @FXML private JFXTextField databaseFld;
    @FXML private JFXButton addBtn;
    @FXML private JFXButton editBtn;
    @FXML private JFXButton dropBtn;
    @FXML private JFXButton cancelBtn;
    @FXML private JFXButton doneBtn;
    @FXML private JFXListView<Label> elementsLst;
    @FXML private JFXTextField tableFld;
    
    @Override
    public void init(Object... params) {
        setStage((Stage) params[0]);
        super.init(params);
    }

    @Override
    public void custom() {
        elementsLst.getItems().add(new Label("id"));
    }

    @Override
    public void onAction() {
        
        //Acciones de la lista
        addBtn.setOnAction( evt -> {
            String addStg = Dialog.createElementNameWSDialog();
            if (addStg != null) {
                elementsLst.getItems().add(new Label(addStg));
            }
        });
        
        editBtn.setOnAction( evt -> { 
            if (elementsLst.getSelectionModel().getSelectedIndex() != -1) {
                String editStg = Dialog.editElementNameWSDialog();
                if (editStg != null) {
                    elementsLst.getItems().set( elementsLst.getSelectionModel().getSelectedIndex() ,new Label(editStg));
                }
            }
        });
        
        dropBtn.setOnAction( evt -> {
            if (elementsLst.getSelectionModel().getSelectedIndex() != -1) {
                Optional<ButtonType> result = Dialog.deleteElementNameWSDialog();
                if (result.get() == ButtonType.OK) {
                    elementsLst.getItems().remove(elementsLst.getSelectionModel().getSelectedItem());
                }
            }
        });
        //====================
        
        doneBtn.setOnAction( evt -> {
            DoWebService.saveService(getClass().getResourceAsStream("/res/get.layout"),elementsLst.getItems(), stage, 
                serverNameFld.getText(), userNameFld.getText(), password.getText(),
                databaseFld.getText(),tableFld.getText());
        });
        
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }
    
}