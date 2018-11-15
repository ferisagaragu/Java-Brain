package org.javabrain.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import org.javabrain.fx.structure.Controller;

/**
 *
 * @author Fernando García
 */
public class Index extends Controller{
    
    @FXML private StackPane root;
    @FXML private JFXButton phpServiceBtn;
    
    private WebService webService;
    private PopOver over;
    private JFXButton getBtn;
    private JFXButton putBtn;
    private JFXButton postBtn;
    private JFXButton deleteBtn;
    private VBox phpServiceConten;
    
    @Override
    public void init(Object... params) {
        webService = (WebService) params[0];
        webService.init(stage);
        
        over = new PopOver();
        getBtn = new JFXButton("Get petition");
        putBtn = new JFXButton("Put petition");
        postBtn = new JFXButton("Post petition");
        deleteBtn = new JFXButton("Delete petition");
        phpServiceConten = new VBox(getBtn,putBtn,postBtn,deleteBtn);
        
        super.init(params); 
    }

    @Override
    public void custom() {
        
        phpServiceConten.setPrefSize(100, 150);
        phpServiceConten.setSpacing(10);
        phpServiceConten.setPadding(new Insets(10, 0, 10, 0));
        phpServiceConten.setAlignment(Pos.CENTER);
        
        over.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
        over.setContentNode(phpServiceConten);
        
    }

    @Override
    public void onAction() {
        
        phpServiceBtn.setOnAction( evt ->{
            over.show(phpServiceBtn);
        });
        
        getBtn.setOnAction( evt ->{
            root.getChildren().clear();
            root.getChildren().add(webService.getRoot());
            over.hide();
        });
        
        
        

    }
       
}