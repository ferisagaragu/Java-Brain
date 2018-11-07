package org.javabrain.util.resource;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import org.javabrain.util.data.Json;

/**
 *
 * @author Fernando García
 */
public class R {
    
    private static R r;
    private static Map<Object,File> img = new LinkedHashMap();
    private static Map<Object,File> raw = new LinkedHashMap();
    private static Map<Object,File> layout = new LinkedHashMap();
    private static Json js = new Json(System.getProperty("user.dir") + "\\src\\conf\\neuron.json");

    public R() {
        if (js != null) {
            if((!js.getString("res").equals("{}")) && (!js.getString("res").isEmpty()) && (js.getString("res") != null)) {
                File data = new File(System.getProperty("user.dir") + "\\src\\res\\");

                if (data.exists()) {
                    File[] files = data.listFiles();
                    //Falta validar si existe alguna de las siguientes opciones y si hay alguna nueva se añada    
                    for (File fil : files) {
                        File[] fi = fil.listFiles();
                        for (File f : fi) {
                            switch (fil.getName()) {
                                case "img": img.put(f.getName(), f); break;
                                case "raw": raw.put(f.getName(), f); break;
                                case "layout": layout.put(f.getName(), f); break;
                            }

                        }
                    }
                }
            }
        }
    }
    
    private static void init() {
        if ( r == null ) {
            r = new R();
        }
    }

    public static Map<Object, File> getImg() {
        init();
        return img;
    }

    public static void setImg(Map<Object, File> img) {
        init();
        R.img = img;
    }

    public static Map<Object, File> getRaw() {
        init();
        return raw;
    }

    public static void setRaw(Map<Object, File> raw) {
        init();
        R.raw = raw;
    }

    public static Map<Object, File> getLayout() {
        init();
        return layout;
    }

    public static void setLayout(Map<Object, File> layout) {
        init();
        R.layout = layout;
    }

}
