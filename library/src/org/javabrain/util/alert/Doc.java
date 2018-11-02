package org.javabrain.util.alert;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.util.Scanner;

public class Doc {

    private void initAndShowGUI(String clas) {
        JFrame frame = new JFrame("Doc - "+clas);
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon(getClass().getResource("/res/component/notebook.png")).getImage());
        Platform.runLater(() -> {
            initFX(fxPanel,clas);
        });
    }

    private void initFX(JFXPanel fxPanel,String clas) {
        Scene scene = createScene(clas);
        fxPanel.setScene(scene);
    }

    private Scene createScene(String clas) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://javabrain.webcindario.com/index.html?"+clas);

        root.getChildren().add(browser);
        return scene;
    }


    public static void show(String clas, String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("\033[33m" + message + " (Yes/No)" + "\033[30m");
                        Scanner scanner = new Scanner(System.in);

                        if (scanner.nextLine().toLowerCase().equals("y") || scanner.nextLine().toLowerCase().equals("yes")) {
                            Doc doc = new Doc();
                            doc.initAndShowGUI(clas);
                        } else {
                            System.exit(0);
                        }
                    }
                });
                thread.start();
            }
        });

    }

}
