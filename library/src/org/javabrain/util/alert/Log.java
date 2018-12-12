package org.javabrain.util.alert;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.javabrain.util.data.Json;
import org.javabrain.util.data.Type;
import org.javabrain.util.web.service.Petition;


public class Log {

    private static Json data = new Json("conf.{neuron.json}");
    private static boolean errorControl = true;

    public static void message(Object obj){
        if (data.getJSON("message").getBoolean("info") && data != null) {
            System.out.println("\033[34m" + structur("Info -> " + obj) + "\033[30m");
        } else {
            System.out.println("\033[34m" + structur("Info -> " + obj) + "\033[30m");
            if (errorControl) {
                Doc.show("Neuron", "The configuration \"neuron.json\" can not be found. Do you want to see how to create it?");
                errorControl = false;
            }
        }
    }

    public static void alert(Object obj) {
        if (data.getJSON("message").getBoolean("alert") && data != null) {
            System.out.println("\033[33m" + structur("Warning -> " + obj) + "\033[30m");
        } else {
            System.out.println("\033[33m" + structur("Warning -> " + obj) + "\033[30m");
//            if (errorControl) {
//                Doc.show("Neuron", "The configuration \"neuron.json\" can not be found. Do you want to see how to create it?");
//                errorControl = false;
//            }
        }
    }

    public static void error(Object obj) {
        if (data.getJSON("message").getBoolean("error") && data != null) {
            System.out.println("\033[31m" + structur("Error -> " + obj) + "\033[30m");
        } else {
            System.out.println("\033[31m" + structur("Error -> " + obj) + "\033[30m");
            if (errorControl) {
                Doc.show("Neuron", "The configuration \"neuron.json\" can not be found. Do you want to see how to create it?");
                errorControl = false;
            }
        }
    }
    
    public static void error(Object obj,Object detail) {
        if (data.getJSON("message").getBoolean("error") && data != null) {
            System.out.println("\033[31m" + structur("Error -> " + obj) + "\033[30m");
            System.out.println("\033[31m" + structur("Detail -> " + detail) + "\033[30m");
        } else {
            System.out.println("\033[31m" + structur("Error -> " + obj) + "\033[30m");
            System.out.println("\033[31m" + structur("Detail -> " + detail) + "\033[30m");
            if (errorControl) {
                Doc.show("Neuron", "The configuration \"neuron.json\" can not be found. Do you want to see how to create it?");
                errorControl = false;
            }
        }
    }

    public static void viewer(Object message) {
        if (Json.isJSON(message)) {
            JsonViewer.show(new Json(message));
            if (errorControl) {
                Doc.show("Neuron", "The configuration \"neuron.json\" can not be found. Do you want to see how to create it?");
                errorControl = false;
            }
        }
    }

    //METODOS PRIVADOS
    private static String structur(Object message) {
        String out = "emply";

        if (Json.isJSON(message)){
            out = Json.parseJson(message).toJSONString().replace("<3", "❤").replace(":)", "☺")
                    .replace(":(", "☹").replace("<-", "←")
                    .replace("->", "→");
        } else {

            try {
                out = message.toString().replace("<3", "❤").replace(":)", "☺")
                        .replace(":(", "☹").replace("<-", "←")
                        .replace("->", "→");
            } catch (Exception e) {}
        }

        return out;
    }
}

// <editor-fold defaultstate="collapsed" desc="JSON VIEWER 0.0.2">
class JsonViewer {
    
    private final Json data;
    private final TreeItem<HBox> rootItem;
    private final String OBJICON = "M22,12V20A2,2 0 0,1 20,22H4A2,2 0 0,1 2,20V12A1,1 0 0,1 1,11V8A2,2 0 0,1 3,6H6.17C6.06,5.69 6,5.35 6,5A3,3 0 0,1 9,2C10,2 10.88,2.5 11.43,3.24V3.23L12,4L12.57,3.23V3.24C13.12,2.5 14,2 15,2A3,3 0 0,1 18,5C18,5.35 17.94,5.69 17.83,6H21A2,2 0 0,1 23,8V11A1,1 0 0,1 22,12M4,20H11V12H4V20M20,20V12H13V20H20M9,4A1,1 0 0,0 8,5A1,1 0 0,0 9,6A1,1 0 0,0 10,5A1,1 0 0,0 9,4M15,4A1,1 0 0,0 14,5A1,1 0 0,0 15,6A1,1 0 0,0 16,5A1,1 0 0,0 15,4M3,8V10H11V8H3M13,8V10H21V8H13Z";
    private final String ARRAYICON = "M3,5A2,2 0 0,1 5,3H19A2,2 0 0,1 21,5V19A2,2 0 0,1 19,21H5C3.89,21 3,20.1 3,19V5M6,6V18H10V16H8V8H10V6H6M16,16H14V18H18V6H14V8H16V16Z";
    private final String INTICON = "M4,17V9H2V7H6V17H4M22,15C22,16.11 21.1,17 20,17H16V15H20V13H18V11H20V9H16V7H20A2,2 0 0,1 22,9V10.5A1.5,1.5 0 0,1 20.5,12A1.5,1.5 0 0,1 22,13.5V15M14,15V17H8V13C8,11.89 8.9,11 10,11H12V9H8V7H12A2,2 0 0,1 14,9V11C14,12.11 13.1,13 12,13H10V15H14Z";
    private final String MONEYICON = "M7,15H9C9,16.08 10.37,17 12,17C13.63,17 15,16.08 15,15C15,13.9 13.96,13.5 11.76,12.97C9.64,12.44 7,11.78 7,9C7,7.21 8.47,5.69 10.5,5.18V3H13.5V5.18C15.53,5.69 17,7.21 17,9H15C15,7.92 13.63,7 12,7C10.37,7 9,7.92 9,9C9,10.1 10.04,10.5 12.24,11.03C14.36,11.56 17,12.22 17,15C17,16.79 15.53,18.31 13.5,18.82V21H10.5V18.82C8.47,18.31 7,16.79 7,15Z";
    private final String DATEICON = "M9,10H7V12H9V10M13,10H11V12H13V10M17,10H15V12H17V10M19,3H18V1H16V3H8V1H6V3H5C3.89,3 3,3.9 3,5V19A2,2 0 0,0 5,21H19A2,2 0 0,0 21,19V5A2,2 0 0,0 19,3M19,19H5V8H19V19Z";
    private final String STRINGICON = "M3,5A2,2 0 0,1 5,3H19A2,2 0 0,1 21,5V19A2,2 0 0,1 19,21H5C3.89,21 3,20.1 3,19V5M12.5,11H11.5A1.5,1.5 0 0,1 10,9.5A1.5,1.5 0 0,1 11.5,8H12.5A1.5,1.5 0 0,1 14,9.5H16A3.5,3.5 0 0,0 12.5,6H11.5A3.5,3.5 0 0,0 8,9.5A3.5,3.5 0 0,0 11.5,13H12.5A1.5,1.5 0 0,1 14,14.5A1.5,1.5 0 0,1 12.5,16H11.5A1.5,1.5 0 0,1 10,14.5H8A3.5,3.5 0 0,0 11.5,18H12.5A3.5,3.5 0 0,0 16,14.5A3.5,3.5 0 0,0 12.5,11Z";
    private final String DOUBLEICON = "M19,11H17V9H15V11H13V13H15V15H17V13H19V19H5V5H19M19,3H5A2,2 0 0,0 3,5V19A2,2 0 0,0 5,21H19A2,2 0 0,0 21,19V5A2,2 0 0,0 19,3M9,11V10H10V11M12,14V10C12,8.89 11.1,8 10,8H9A2,2 0 0,0 7,10V11C7,12.11 7.9,13 9,13H10V14H7V16H10A2,2 0 0,0 12,14Z";
    private final String CHARICON = "M7.2,11.2C8.97,11.2 10.4,12.63 10.4,14.4C10.4,16.17 8.97,17.6 7.2,17.6C5.43,17.6 4,16.17 4,14.4C4,12.63 5.43,11.2 7.2,11.2M14.8,16A2,2 0 0,1 16.8,18A2,2 0 0,1 14.8,20A2,2 0 0,1 12.8,18A2,2 0 0,1 14.8,16M15.2,4A4.8,4.8 0 0,1 20,8.8C20,11.45 17.85,13.6 15.2,13.6A4.8,4.8 0 0,1 10.4,8.8C10.4,6.15 12.55,4 15.2,4Z";
    private final String JSONICON = "M5,3H7V5H5V10A2,2 0 0,1 3,12A2,2 0 0,1 5,14V19H7V21H5C3.93,20.73 3,20.1 3,19V15A2,2 0 0,0 1,13H0V11H1A2,2 0 0,0 3,9V5A2,2 0 0,1 5,3M19,3A2,2 0 0,1 21,5V9A2,2 0 0,0 23,11H24V13H23A2,2 0 0,0 21,15V19A2,2 0 0,1 19,21H17V19H19V14A2,2 0 0,1 21,12A2,2 0 0,1 19,10V5H17V3H19M12,15A1,1 0 0,1 13,16A1,1 0 0,1 12,17A1,1 0 0,1 11,16A1,1 0 0,1 12,15M8,15A1,1 0 0,1 9,16A1,1 0 0,1 8,17A1,1 0 0,1 7,16A1,1 0 0,1 8,15M16,15A1,1 0 0,1 17,16A1,1 0 0,1 16,17A1,1 0 0,1 15,16A1,1 0 0,1 16,15Z";
    private final String HYPERINCON = "M10.59,13.41C11,13.8 11,14.44 10.59,14.83C10.2,15.22 9.56,15.22 9.17,14.83C7.22,12.88 7.22,9.71 9.17,7.76V7.76L12.71,4.22C14.66,2.27 17.83,2.27 19.78,4.22C21.73,6.17 21.73,9.34 19.78,11.29L18.29,12.78C18.3,11.96 18.17,11.14 17.89,10.36L18.36,9.88C19.54,8.71 19.54,6.81 18.36,5.64C17.19,4.46 15.29,4.46 14.12,5.64L10.59,9.17C9.41,10.34 9.41,12.24 10.59,13.41M13.41,9.17C13.8,8.78 14.44,8.78 14.83,9.17C16.78,11.12 16.78,14.29 14.83,16.24V16.24L11.29,19.78C9.34,21.73 6.17,21.73 4.22,19.78C2.27,17.83 2.27,14.66 4.22,12.71L5.71,11.22C5.7,12.04 5.83,12.86 6.11,13.65L5.64,14.12C4.46,15.29 4.46,17.19 5.64,18.36C6.81,19.54 8.71,19.54 9.88,18.36L13.41,14.83C14.59,13.66 14.59,11.76 13.41,10.59C13,10.2 13,9.56 13.41,9.17Z";
    
    public JsonViewer(Json data) {
        this.data = data;
        this.rootItem = new TreeItem<>();
    }

    private void initAndShowGUI() {
        JFrame frame = new JFrame("JSON Viewer");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(getClass().getResource("/res/component/json.png")).getImage());
        Platform.runLater(() -> {
            initFX(fxPanel);
        });
    }

    private void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene() {
        StackPane root = new StackPane();
        root.getStylesheets().add("/res/style/viewer.css");
        Scene scene = new Scene(root, Color.ALICEBLUE);
        root.getChildren().add(rootTree());
        return scene;
    }

    private TreeView rootTree() {
        
        if (data.isJSONObject()) {
            Label lab = new Label("Root: ");
            lab.getStyleClass().add("root-lbl");

            Label lab2 = new Label("JSON Object");
            lab2.getStyleClass().add("object-lbl");

            SVGPath objIcon = new SVGPath();
            objIcon.setContent(OBJICON);
            objIcon.getStyleClass().add("object-lbl");
            objIcon.setScaleX(0.5);
            objIcon.setScaleY(0.5);
            
            rootItem.setValue(new HBox(lab,lab2,objIcon));
        } else {
            Label lab = new Label("Root: ");
            lab.getStyleClass().add("root-lbl");

            Label lab2 = new Label("JSON Array");
            lab2.getStyleClass().add("array-lbl");

            SVGPath objIcon = new SVGPath();
            objIcon.setContent(ARRAYICON);
            objIcon.getStyleClass().add("array-lbl");
            objIcon.setScaleX(0.5);
            objIcon.setScaleY(0.5);
            
            rootItem.setValue(new HBox(lab,lab2,objIcon));
        }   
        readData(data,rootItem);
       return new TreeView(rootItem);
    }

    public void readData(Json data, TreeItem<HBox> root) {

        if (data.isJSONObject()) {
            for (Object o : data.getKeys()) {
                if (Json.isJSONArray(data.getObject(o))) {
                    TreeItem obj = createItem(o.toString(), "[{\"end\":\"to\"}]");
                    root.getChildren().add(obj);
                    readData(data.getJSON(o), obj);
                } else {
                    root.getChildren().add(createItem(o.toString(), data.getString(o)));
                }
            }
        } else {

            for (Json js : data.values()) {
                TreeItem obj = createItem("Object", "{}");
                root.getChildren().add(obj);
                readData(js, obj);
            }
        }

    }
    
    //Aquí se le da todo el estilo errorControl los items
    private TreeItem<HBox> createItem(String itemName,String item) {

        Label itemNameLbl = new Label(itemName + ": ");
        itemNameLbl.getStyleClass().add("root-lbl");
        
        Label itemLbl = new Label(item);
        
        Label typeLbl = new Label();
        
        Hyperlink h = null;
        
        boolean especial = true;
        
        String icon = ""; 
        String type = "";
        String length = "";
        String typ = Type.convertType(item);

        switch (typ){
            
            case "Integer":  
                type = "Integer"; 
                icon =  INTICON;
                itemLbl.getStyleClass().add("int-lbl");
            break;
            
            case "Money":
                type = "Money"; 
                icon =  MONEYICON;
                itemLbl.getStyleClass().add("money-lbl");
            break;
            
            case "Date":
                type = "Date"; 
                icon =  DATEICON;
                itemLbl.getStyleClass().add("date-lbl");
            break;
            
            case "String":
                type = "String"; 
                icon =  STRINGICON;
                itemLbl.getStyleClass().add("string-lbl");
                itemLbl.setText("\""+item+"\"");
            break;
            
            case "Double":
                type = "Double"; 
                icon =  DOUBLEICON;
                itemLbl.getStyleClass().add("double-lbl");
            break;
            
            case "Character":
                type = "Character"; 
                icon =  CHARICON;
                itemLbl.getStyleClass().add("char-lbl");
                itemLbl.setText("'"+item+"'");
            break;
            
            case "JSON Object":
                type = "JSON Object"; 
                icon =  JSONICON;
                itemLbl.getStyleClass().add("object-lbl");
                typeLbl.getStyleClass().add("object-lbl");
                itemLbl.setText("");
                especial = false;
            break;
            
            case "JSON Array":
                type = "JSON Array"; 
                icon =  ARRAYICON;
                itemLbl.getStyleClass().add("object-lbl");
                typeLbl.getStyleClass().add("array-lbl");
                itemLbl.setText("");
                especial = false;
            break;
            
            case "Array":
                String[] len = item.split(",");
                length = len.length+"";
                type = "Array"; 
                icon =  ARRAYICON;
                itemLbl.getStyleClass().add("array-lbl");
            break;
            
            case "Url":
                type = "URL"; 
                icon =  HYPERINCON;
                h = new Hyperlink(item);
                h.getStyleClass().add("link-lbl");
                h.setOnAction( e -> {
                    Petition.openURL(item);
                });
            break;
            
            default: 
                type = "Object"; 
                icon = OBJICON; 
                itemLbl.getStyleClass().add("object-lbl");
        }

        typeLbl.setText("("+type+") "+length);
        if (especial) {
            typeLbl.getStyleClass().add("type-lbl");
        }
        
        SVGPath iconType = new SVGPath();
        iconType.setContent(icon);
        iconType.setScaleX(0.5);
        iconType.setScaleY(0.5);
        iconType.getStyleClass().add("type-lbl");

        return new TreeItem<>(new HBox(itemNameLbl,typeLbl,iconType,h != null ? h : itemLbl));
    }
    
    public static void show(Json js) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JsonViewer jv = new JsonViewer(js);
                jv.initAndShowGUI();
            }
        });
    }

}// </editor-fold>


