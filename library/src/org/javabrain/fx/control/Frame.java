package org.javabrain.fx.control;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Fernny Rerptar Mcfly
 */
public class Frame{
    
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;
    private Parent parent;
   
    public Frame(String fxml,String title,Image icon)
    {
        try
        {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            parent = login;
            scene = new Scene(login);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.getIcons().add(icon);
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
        }

    }

    public Frame(String fxml,String title,Image icon,String css)
    {
        try
        {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            parent = login;
            scene = new Scene(login);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.getIcons().add(icon);
            scene.getRoot().getStylesheets().add(css);
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
        }

    }

    public Frame(String fxml,String title,String css)
    {
        try
        {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            scene = new Scene(login);
            stage.setScene(scene);
            stage.setTitle(title);
            scene.getRoot().getStylesheets().add(css);
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
        }

    }

    public Frame(String fxml,String title)
    {
        try
        {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            scene = new Scene(login);
            stage.setScene(scene);
            stage.setTitle(title);
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
        }

    }
    
    public Frame(String fxml)
    {
        try
        {
            loader = new FXMLLoader(getClass().getResource(fxml));
            stage = new Stage();
            Parent login = loader.load();
            scene = new Scene(login);
            stage.setScene(scene);
        }
        catch (Exception e)
        {
            System.out.print(e.getMessage());
        }

    }

    public void setStage(Stage sta)
    {
        
        try
        {
            stage = sta;
            Parent login = loader.load();
            scene.setRoot(login);
            stage.setScene(scene);
        }
        catch (Exception e)
        {
            //System.out.print(e.getMessage());
        }
    }
    
    public Object getFXML()
    {
        Object clase = loader.getController();

        return clase;
    }
    

    public void showOnCenter()
    {
        stage.show();
        //Estas lineas son para centrar las ventanas
        Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(d.width/2-(stage.getWidth()/2));
        stage.setY(d.height/2-(stage.getHeight()/2));
    }
    
    public void hide()
    {
        stage.hide();
    }
    
    public void close()
    {
        stage.close();
    }
    
    public void show()
    {
        stage.show();
    }
    
    public void showAndWait()
    {
        stage.showAndWait();
    }
    
    public void showAndWaitOnCenter()
    {
        showOnCenter();
        stage.hide();
        stage.showAndWait();
    }
    
    public void FullScreen(boolean bool)
    {
        stage.setFullScreen(bool);
    }

    public Stage getStage()
    {
        stage.setScene(scene);

        return stage;
    }

    public void OnCenter()
    {
        Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX(d.width/2-(stage.getWidth()/2));
        stage.setY(d.height/2-(stage.getHeight()/2));
    }

    public Parent getParent() {
        return parent;
    }

}
