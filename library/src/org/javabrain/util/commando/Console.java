package org.javabrain.util.commando;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Fernando García
 * @version 0.0.1
 */
public class Console {

    public static String command(String[] coms) {
        try {

            String commant = "";
            for (int i = 0; i < coms.length; i++) {
                commant = commant + coms[i] + " & ";
            }
            commant = commant.substring(0,commant.length() - 2);

            Process p = Runtime.getRuntime().exec("cmd /C " + commant);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
            String line = null;
            String out = "";
            while ((line = in.readLine()) != null) {
                out = out + line + "\n";
            }
            return out;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
