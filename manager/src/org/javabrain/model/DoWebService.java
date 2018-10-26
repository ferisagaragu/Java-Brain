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
            doGet(fil, in, elements, stage, serverName, userName, password, database, table);
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
    
    private static void doPut(InputStream in, ObservableList<Label> elements, Stage stage, String serverName, String userName, String password, String database, String table) {

        String out = "";

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
        String query = "";
        for (Label lbl : elements) {
            query = query + "," + lbl.getText();
            data = data + "$json -> " + lbl.getText() + " = $row['" + lbl.getText() + "'];\n        ";
        }
        String[] array = query.split(",");
        String query2 = "";

        for (String dat : array) {
            query2 = query2 + ",'\".$" + dat + ".\"'";
        }

        out = out.replace("${query}", "INSERT INTO " + table + " (" + query.substring(1, query.length()) + ") "
                + "VALUES (" + query2.substring(9, query2.length()) + ");");
        out = out.replace("${jsonMap}", data);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(""))) {
            writer.write(out);
        } catch (IOException ex) {
            Logger.getLogger(WebService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
