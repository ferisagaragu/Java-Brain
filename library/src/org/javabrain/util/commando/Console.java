package org.javabrain.util.commando;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Fernando García
 * @version 0.0.1
 */
public class Console {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String OSArchitecture = System.getProperty("os.arch").toLowerCase();
    private static String OSVersion = System.getProperty("os.version").toLowerCase();
    
    public static String command(String[] coms) {
        try {
            String out = "";
            if (isWindows()) {
                String commant = "";
                for (int i = 0; i < coms.length; i++) {
                    commant = commant + coms[i] + " & ";
                }
                commant = commant.substring(0,commant.length() - 2);

                Process p = Runtime.getRuntime().exec("cmd /C " + commant);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
                String line = null;
                while ((line = in.readLine()) != null) {
                    out = out + line + "\n";
                }
            } else {
                
                Process p = Runtime.getRuntime().exec(coms);
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
                String line = null;
                while ((line = in.readLine()) != null) {
                    out = out + line + "\n";
                }
            }
            return out;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static void execute(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }
 
    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }
 
    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }
 
    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

    public static String getOS() {
        return OS;
    }

    public static String getOSVersion() {
        return OSVersion;
    }

    public static String getOSArchitecture() {
        return OSArchitecture;
    }
}
