package org.javabrain.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.javabrain.controller.WebService;
import org.javabrain.util.resource.Archive;
import org.javabrain.util.resource.Layout;
import org.javabrain.util.resource.R;

/**
 *
 * @author Fernando García
 */
public class DoWebService {
    
    public static boolean saveService(InputStream in,ObservableList<Label> elements,Stage stage,String serverName,String userName,String password,String database,String table) {
        
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Php service(*.php)", "*.php"));
        File fil = chooser.showSaveDialog(stage);
        
        if (fil != null) {
            //doGet(fil, in, elements, stage, serverName, userName, password, database, table);
            add(fil, elements, serverName, userName, password, database, table);
            return true;
        } else {
            return false;
        }
    }
    
    private static void doGet(File fil,InputStream in,ObservableList<Label> elements,Stage stage,String serverName,String userName,String password,String database,String table) {
        String out = "";
        String path = fil.getPath().replace(fil.getName(),"get"+fil.getName());
        try {
            int i;
            char c;

            while ((i = in.read()) != -1) {
                c = (char) i;
                out = out + c;
            }

        } catch (IOException ex) {
            Logger.getLogger(WebService.class.getName()).log(Level.SEVERE, null, ex);
        }

        out = out.replace("${servername}", serverName);
        out = out.replace("${username}", userName);
        out = out.replace("${password}", password);
        out = out.replace("${dbname}", database);

        String data = "";
        for (Label lbl : elements) {
            data = data + "$json -> " + lbl.getText() + " = $row['" + lbl.getText() + "'];\n        ";
        }
        out = out.replace("${query}", "SELECT * FROM " + table);
        out = out.replace("${jsonMap}", data);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(out);
        } catch (IOException ex) {
            Logger.getLogger(WebService.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        Archive.write(fil.getPath(),layout.getLayout());
    }
}