package org.javabrain.fx.container;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

/**
 *
 * @author Fernando García
 */
public class Modal {
    
    private static JFXDialog dialog = new JFXDialog();
    private static JFXDialogLayout layout = new JFXDialogLayout();
    
    private static String infoIcon = "M13,9H11V7H13M13,17H11V11H13M12,2A10,10 0 0,0 2,12A10,10 0 0,0 12,22A10,10 0 0,0 22,12A10,10 0 0,0 12,2Z"; 
    private static String confIcon = "M2.2,16.06L3.88,12L2.2,7.94L6.26,6.26L7.94,2.2L12,3.88L16.06,2.2L17.74,6.26L21.8,7.94L20.12,12L21.8,16.06L17.74,17.74L16.06,21.8L12,20.12L7.94,21.8L6.26,17.74L2.2,16.06M13,17V15H11V17H13M13,13V7H11V13H13Z";
    
    public static void showCustom(StackPane root,Region region) {
        clearDialog();
        dialog.setContent(region);
        dialog.setDialogContainer(root);
        dialog.show();
    }
    
    public static void showInfo(StackPane root,String title,String content) {
        clearDialog();
        
        JFXButton button = new JFXButton("Ok");
        SVGPath icon = new SVGPath();
        Label titleI = new Label("  " + title);
        Label contentI = new Label(content);
        HBox box = new HBox(icon,titleI);
        
        icon.setContent(infoIcon);
        layout.setHeading(box);
        layout.setBody(contentI);
        layout.setActions(button);
        button.setOnAction( evt -> {
            dialog.close();
        });
        dialog.setContent(layout);
        dialog.show(root);
      
    }
    
    public static void showInfo(StackPane root,String title,String content,String css) {
        clearDialog();
        
        JFXButton button = new JFXButton("Ok");
        SVGPath icon = new SVGPath();
        Label titleI = new Label("  " + title);
        Label contentI = new Label(content);
        HBox box = new HBox(icon,titleI);
        
        icon.setContent(infoIcon);
        layout.setHeading(box);
        layout.setBody(contentI);
        layout.setActions(button);
        button.setOnAction( evt -> {
            dialog.close();
        });
        dialog.setContent(layout);
        dialog.show(root);
        
        button.getStyleClass().add("btn");
        icon.getStyleClass().add("icon");
        titleI.getStyleClass().add("title");
        contentI.getStyleClass().add("content");
        
        layout.getStylesheets().add(css);
        
    }
    
    public static JFXButton showConfirm(StackPane root,String title,String content) {
        clearDialog();
        
        JFXButton button = new JFXButton("Ok");
        JFXButton button2 = new JFXButton("Cancel");
        SVGPath icon = new SVGPath();
        Label titleI = new Label("  " + title);
        Label contentI = new Label(content);
        HBox box = new HBox(icon,titleI);
        
        icon.setContent(confIcon);
        layout.setHeading(box);
        layout.setBody(contentI);
        layout.setActions(button,button2);
     
        dialog.setContent(layout);
        dialog.show(root);
        
        button2.setOnAction( evt -> {
            dialog.close();
        });
        
        button.setOnMouseClicked(evt -> {
            dialog.close();
        });
                
        return button;
    }
    
    public static JFXButton showConfirm(StackPane root,String title,String content,String css) {
        clearDialog();
        
        JFXButton button = new JFXButton("Ok");
        JFXButton button2 = new JFXButton("Cancel");
        SVGPath icon = new SVGPath();
        Label titleI = new Label("  " + title);
        Label contentI = new Label(content);
        HBox box = new HBox(icon,titleI);
        
        icon.setContent(confIcon);
        layout.setHeading(box);
        layout.setBody(contentI);
        layout.setActions(button,button2);
      
        dialog.setContent(layout);
        dialog.show(root);
        
        button.getStyleClass().add("btn");
        button.getStyleClass().add("btn-ok");
        
        button2.getStyleClass().add("btn");
        button2.getStyleClass().add("btn-cancel");
        
        icon.getStyleClass().add("icon");
        titleI.getStyleClass().add("title");
        contentI.getStyleClass().add("content");
        
        layout.getStylesheets().add(css);
        
        button2.setOnAction( evt -> {
            dialog.close();
        });
        
        button.setOnMouseClicked(evt -> {
            dialog.close();
        });
        
        return button;
    }
    
    public static JFXButton showInput(StackPane root,String title,String content,Node input) {
        clearDialog();
        
        JFXButton button = new JFXButton("Ok");
        JFXButton button2 = new JFXButton("Cancel");
        SVGPath icon = new SVGPath();
        Label titleI = new Label("  " + title);
        Label contentI = new Label(content);
        HBox box = new HBox(icon,titleI);
        
        icon.setContent(confIcon);
        layout.setHeading(box);
        layout.setBody(new VBox(contentI,input));
        layout.setActions(button,button2);
      
        dialog.setContent(layout);
        dialog.show(root);
        
        button2.setOnAction( evt -> {
            dialog.close();
        });
        
        button.setOnMouseClicked(evt -> {
            dialog.close();
        });
        
        return button;
    }
    
    public static JFXButton showInput(StackPane root,String title,String content,Node input,String css) {
        clearDialog();
        
        JFXButton button = new JFXButton("Ok");
        JFXButton button2 = new JFXButton("Cancel");
        SVGPath icon = new SVGPath();
        Label titleI = new Label("  " + title);
        Label contentI = new Label(content);
        HBox box = new HBox(icon,titleI);
        
        icon.setContent(confIcon);
        layout.setHeading(box);
        layout.setBody(new VBox(contentI,input));
        layout.setActions(button,button2);
      
        dialog.setContent(layout);
        dialog.show(root);
        
        button.getStyleClass().add("btn");
        button.getStyleClass().add("btn-ok");
        
        button2.getStyleClass().add("btn");
        button2.getStyleClass().add("btn-cancel");
        
        icon.getStyleClass().add("icon");
        titleI.getStyleClass().add("title");
        contentI.getStyleClass().add("content");
        
        layout.getStylesheets().add(css);
        
        button2.setOnAction( evt -> {
            dialog.close();
        });
        
        button.setOnMouseClicked(evt -> {
            dialog.close();
        });
        
        return button;
    }
    
    public static JFXDialog getDialog() {
        return dialog;
    }
    
    private static void clearDialog(){
        dialog.setContent(null);
        dialog.setDialogContainer(null);
        layout.getStylesheets().clear();
        layout.getActions().clear();
        layout.getHeading().clear();
        layout.getBody().clear();
    }

}
