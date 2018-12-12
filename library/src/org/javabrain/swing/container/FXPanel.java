package org.javabrain.swing.container;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Fernando García
 */
public class FXPanel extends JFXPanel{
    
    private StackPane root;
    
    public FXPanel() {
        root = new StackPane();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGUI();
            }
        });
    }

    private void initAndShowGUI() {
        Platform.runLater(() -> {
            initFX(this);
        });
    }

    private void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene() {
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color:#FFF;");
        return scene;
    }
    
    public void add(Node node){
        root.getChildren().add(node);
    }
    
    //GET AND SET
    public StackPane getRoot() {
        return root;
    }

    public void setRoot(StackPane root) {
        this.root = root;
    }

}
