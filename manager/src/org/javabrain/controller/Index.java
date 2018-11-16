package org.javabrain.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.javabrain.fx.structure.Controller;

/**
 *
 * @author Fernando García
 */
public class Index extends Controller{
    
    @FXML private StackPane root;
    @FXML private JFXButton phpServiceBtn;
    @FXML private GridPane indexPane;
    
    private WebService webService;
    
    @Override
    public void init(Object... params) {
        webService = (WebService) params[0];
        webService.init(stage,indexPane);
        
        super.init(params); 
    }

    @Override
    public void onAction() {
        
        phpServiceBtn.setOnAction( evt ->{
            root.getChildren().clear();
            root.getChildren().add(webService.getRoot());
        });
        
    }
       
}