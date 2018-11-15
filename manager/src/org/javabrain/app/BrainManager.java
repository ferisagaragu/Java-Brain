package org.javabrain.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.javabrain.Neuron;
import org.javabrain.controller.Index;
import org.javabrain.controller.WebService;
import org.javabrain.fx.control.Drawable;
import org.javabrain.fx.control.Frame;
import org.javabrain.util.resource.R;
import sun.launcher.resources.launcher;

/**
 *
 * @author Fernando García
 */
public class BrainManager extends Application{

    private final Frame index = new Frame(R.getDrawable("index.fxml"));
    private final Drawable webService = new Drawable(R.getDrawable("web_service.fxml"));
    
    public void start(Stage primaryStage) throws Exception {
        Neuron.init();
        WebService webServiceC = (WebService) webService.getFXML();

        Index indexC = (Index) index.getFXML();
        indexC.setStage(index.getStage());
        indexC.init(webServiceC);

        index.showOnCenter();
    }
    
    public static void main(String[] args){launch(args);}

}