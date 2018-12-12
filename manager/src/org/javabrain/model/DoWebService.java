package org.javabrain.model;

import com.jfoenix.controls.JFXSnackbar;
import java.io.File;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.javabrain.util.resource.Archive;
import org.javabrain.util.resource.Layout;
import org.javabrain.util.resource.R;

/**
 *
 * @author Fernando García
 */
public class DoWebService {
    
    public static boolean saveService(ObservableList<Label> elements,Stage stage,String serverName,String userName,String password,String database,String table,JFXSnackbar snackbar) {
        
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Php service(*.php)", "*.php"));
        File fil = chooser.showSaveDialog(stage);
        
        if (fil != null) {
            doGet(fil,elements,serverName, userName, password, database, table);
            add(fil, elements, serverName, userName, password, database, table);
            snackbar.show("Los Web-Services se han creado correctamente.", 3000);
            return true;
        } else {
            return false;
        }
    }
    
    private static void doGet(File fil,ObservableList<Label> elements,String serverName,String userName,String password,String database,String table) {
        
        Layout layout = R.getLayout("get.layout");
        
        layout.put("servername", serverName);
        layout.put("username",userName);
        layout.put("password", password);
        layout.put("dbname", database);
        
        String data = "";
        for (Label lbl : elements) {
            data = data + "$json -> " + lbl.getText() + " = $row['" + lbl.getText() + "'];\n        ";
        }
        layout.put("query","SELECT * FROM " + table);
        layout.put("jsonMap", data);

        String fileName = fil.getName();
        Archive.write(fil.getPath().replace(fileName,"get"+fileName),layout.getLayout());
    }
    
    private static void add(File fil,ObservableList<Label> elements,String serverName, String userName, String password, String database, String table) {

        Layout layout = R.getLayout("add.layout");
        
        layout.put("servername",serverName);
        layout.put("username",userName);
        layout.put("password",password);
        layout.put("dbname",database);
        
        String data = "";
        String values = "";
        String params = "";
        String json = "";
        
        for(Label o : elements) {
            if (!o.getText().equals("id")) {
                data = data + "$"+o.getText()+"=$_POST['"+o.getText()+"']; \n";
                values = values +","+ o.getText();
                params = params +",'\".$"+ o.getText() + ".\"'";
                json = json + "\""+o.getText()+"\":"+"\"' .$"+o.getText()+". '\",";
            }
        }

        layout.put("params",data);
        layout.put("query","insert into "+table+"("+values.substring(1, values.length())+") values ("+params.substring(1, params.length())+")");
        layout.put("jsonOut","{"+json.substring(0,json.length() - 1)+"}");
        
        String fileName = fil.getName();
        Archive.write(fil.getPath().replace(fileName,"add"+fileName),layout.getLayout());
    }
}