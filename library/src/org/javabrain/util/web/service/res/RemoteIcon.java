package org.javabrain.util.web.service.res;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class RemoteIcon {

    public static void get(String hint){
        try {
            String[] part = hint.split(" ");
            String originPath = "https://raw.githubusercontent.com/ferisagaragu/Java-Brain/master/icon/"+part[0]+"/"+part[1]+"/icons8_"+part[2]+"_100px.png";
            String path = System.getProperty("user.dir") + "\\src\\res\\icon\\";
            File fil = new File(path);
            fil.mkdirs();
            URL url = new URL(originPath);
            String[] data = hint.split("/");
            URLConnection urlCon = url.openConnection();
            InputStream is = urlCon.getInputStream();
            FileOutputStream fos = new FileOutputStream(path + part[2] + ".nco");
            byte[] array = new byte[1000];
            int leido = is.read(array);
            while (leido > 0) {
                fos.write(array, 0, leido);
                leido = is.read(array);
            }

            is.close();
            fos.close();
        } catch (Exception e) {
            System.err.println("Remote resource not found");
        }
    }

}
