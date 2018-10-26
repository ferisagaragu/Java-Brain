package org.javabrain.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.javabrain.controller.Index;
import org.javabrain.controller.WebService;
import org.javabrain.fx.control.Frame;
import org.javabrain.fx.control.Layout;

/**
 *
 * @author Fernando García
 */
public class BrainManager extends Application{

    private final Frame index = new Frame("/org/javabrain/drawable/index.fxml");
    private final Layout webService = new Layout("/org/javabrain/drawable/web_service.fxml");
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        WebService webServiceC = (WebService) webService.getFXML();
        
        Index indexC = (Index) index.getFXML();
        indexC.setStage(index.getStage());
        indexC.init(webServiceC);
        
        index.showOnCenter();
    }
    
    public static void main(String[] args) { launch(args); }

}
