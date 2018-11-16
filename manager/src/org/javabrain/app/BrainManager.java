package org.javabrain.app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.javabrain.controller.Index;
import org.javabrain.controller.WebService;
import org.javabrain.fx.control.Drawable;
import org.javabrain.fx.control.Frame;
import org.javabrain.util.resource.R;

/**
 *
 * @author Fernando García
 */
public class BrainManager extends Application{

    private final Frame index = new Frame(R.getDrawable("index.fxml"),"Brain - Manager",new Image("res/img/brain.png"));
    private final Drawable webService = new Drawable(R.getDrawable("web_service.fxml"));
    
    public void start(Stage primaryStage) throws Exception {
        //Neuron.init();
        WebService webServiceC = (WebService) webService.getFXML();

        Index indexC = (Index) index.getFXML();
        indexC.setStage(index.getStage());
        indexC.init(webServiceC);

        index.showOnCenter();
    }
    
    public static void main(String[] args){launch(args);}

}