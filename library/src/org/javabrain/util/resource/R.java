package org.javabrain.util.resource;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import org.javabrain.util.code.JavaScript;
import org.javabrain.util.data.Json;

/**
 *
 * @author Fernando García
 * @version 0.0.3
 */
public class R {
    
    private static R r;
    private static Map<Object,String> img = new LinkedHashMap();
    private static Map<Object,InputStream> raw = new LinkedHashMap();
    private static Map<Object,Layout> layout = new LinkedHashMap();
    private static Map<Object,String> style = new LinkedHashMap();
    private static Map<Object,String> drawable = new LinkedHashMap();
    private static Map<Object,JavaScript> script = new LinkedHashMap();
    private static final Json js = new Json("conf.{neuron.json}");
    private static final Json resmap = new Json("conf.{resmap.json}");
    public static final ClassLoader CLASS_LOADER = ClassLoader.getSystemClassLoader();
    public static boolean isUse = false;
    
    public R() {
        if (!isUse) {
            try {
                if (js != null) {
                    Json res = new Json();
                    if ((!js.getString("res").equals("{}")) && (!js.getString("res").isEmpty()) && (js.getString("res") != null)) {
                        File data = new File(Archive.SOURCE_PATH + "res");
                        File drawa = new File(Archive.SOURCE_PATH + js.getString("drawable").replace(".", "\\"));
                        File scri = new File(Archive.SOURCE_PATH + js.getString("script").replace(".", "\\"));
                        
                        Json drawableJson = new Json();
                        if (drawa.exists()) {
                            for (File fil:drawa.listFiles()) {
                                drawableJson.put(fil.getName(), Archive.convertToRelative(fil.getPath()));
                            }
                        }
                        
                        Json scriptJson = new Json();
                        if (!js.getString("script").isEmpty()) {
                            if (scri.exists()) {
                                for (File fil:scri.listFiles()) {
                                    scriptJson.put(fil.getName(), Archive.convertToRelative(fil.getPath()));
                                }
                            }
                        }
                        
                        if (data.exists()) {
                            File[] files = data.listFiles();
                            Json layoutJson = new Json();
                            Json imgJson = new Json();
                            Json rawJson = new Json();
                            Json styleJson = new Json();

                            //Aquí se cargan las rutas y se convierten en rutas relativas    
                            for (File fil : files) {
                                File[] fi = fil.listFiles();
                                for (File f : fi) {
                                    switch (fil.getName()) {
                                        case "img": imgJson.put(f.getName(), Archive.convertToRelative(f.getPath())); break;
                                        case "raw": rawJson.put(f.getName(), Archive.convertToRelative(f.getPath())); break;
                                        case "layout": layoutJson.put(f.getName(), Archive.convertToRelative(f.getPath())); break;
                                        case "style": styleJson.put(f.getName(), Archive.convertToRelative(f.getPath())); break;
                                    }
                                }
                            }
                            //===================================================================
                            
                            //Aquí se crea el archivo resmap.json y como es la primera vez se cargan virtualmente
                            try {
                                
                                res.put("img", imgJson);
                                res.put("raw", rawJson);
                                res.put("layout", layoutJson);
                                res.put("style", styleJson);
                                res.put("drawable", drawableJson);
                                res.put("script", scriptJson);
                                res.write("./conf/resmap.json");
                                
                                for (Object key1 : res.getJSON("layout").getKeys()) {
                                    layout.put(key1, new Layout(res.getJSON("layout").getString(key1)));
                                }

                                for (Object key1 : res.getJSON("img").getKeys()) {
                                    img.put(key1, res.getJSON("img").getString(key1));
                                }

                                for (Object key1 : res.getJSON("raw").getKeys()) {
                                    raw.put(key1, CLASS_LOADER.getResourceAsStream(res.getJSON("raw").getString(key1)));
                                }
                                
                                for (Object key1 : res.getJSON("style").getKeys()) {
                                    style.put(key1, res.getJSON("style").getString(key1));
                                }
                                
                                for (Object key1 : res.getJSON("drawable").getKeys()) {
                                    drawable.put(key1, res.getJSON("drawable").getString(key1));
                                }
                                
                                for (Object key1 : res.getJSON("script").getKeys()) {
                                    script.put(key1, new JavaScript(res.getJSON("script").getString(key1)));
                                }
                                
                            } catch (Exception e) {}
                            //====================================
                            
                        }
                    }

                    //En esta seccion se cargan los datos en los mapas
                    for (Object key1 : resmap.getJSON("layout").getKeys()) {
                        layout.put(key1, new Layout(resmap.getJSON("layout").getString(key1)));
                    }

                    for (Object key1 : resmap.getJSON("img").getKeys()) {
                        img.put(key1,"/" + resmap.getJSON("img").getString(key1));
                    }

                    for (Object key1 : resmap.getJSON("raw").getKeys()) {
                        raw.put(key1, CLASS_LOADER.getResourceAsStream(resmap.getJSON("raw").getString(key1)));
                    }
                    
                    for (Object key1 : resmap.getJSON("style").getKeys()) {
                        style.put(key1, resmap.getJSON("style").getString(key1));
                    }
                    
                    for (Object key1 : resmap.getJSON("drawable").getKeys()) {
                        drawable.put(key1, resmap.getJSON("drawable").getString(key1));
                    }
                    
                    for (Object key1 : res.getJSON("script").getKeys()) {
                        script.put(key1, new JavaScript(res.getJSON("script").getString(key1)));
                    }
                    //=========================================================================================
                }
            } catch (Exception e) {
                System.out.println("No existe el archivo de configuración");
            }
            isUse = true;
        }
    }
    

    private static void init() {
        if ( r == null ) {
            r = new R();
        }
    }

    public static URL getImg(Object clazz,Object key) {
        init();
        return clazz.getClass().getResource(img.get(key));
    }

    public static String getImg(Object key) {
        init();
        return img.get(key);
    }

    public static InputStream getRaw(Object key) {
        init();
        return raw.get(key);
    }


    public static Layout getLayout(Object key) {
        init();
        return layout.get(key);
    }

    public static String getStyle(Object key) {
        init();
        return "/" + style.get(key);
    }
    
    public static String getDrawable(Object key){
        init();
        return "/" + drawable.get(key);
    }
    
    public static JavaScript getScript(Object key){
        init();
        return script.get(key);
    }
}
