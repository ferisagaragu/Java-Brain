import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;


public class Run {

    public static void main(String[] args) {

        Console.viewer("package org.javabrain.util.resource;\n" +
"\n" +
"import org.javabrain.util.data.Json;\n" +
"\n" +
"import javax.swing.*;\n" +
"import java.io.*;\n" +
"import java.net.URL;\n" +
"\n" +
"/***\n" +
" * @author Fernando García\n" +
" * @version 0.0.1\n" +
" */\n" +
"public class Path {\n" +
"\n" +
"    private static Pth pth = new Pth();\n" +
"\n" +
"    public static String PROYECT = System.getProperty(\"user.dir\")+\"\\\\\";\n" +
"\n" +
"    public static ImageIcon getRes(String resName){\n" +
"\n" +
"        String out = \"\";\n" +
"\n" +
"        try {\n" +
"            BufferedReader in = new BufferedReader(new InputStreamReader(pth.getPath(\"/config/neuron.json\"), \"utf-8\"));\n" +
"            String sCadena = \"\";\n" +
"\n" +
"            while ((sCadena = in.readLine())!=null) {\n" +
"                out += sCadena;\n" +
"            }\n" +
"\n" +
"            Json json = new Json(out);\n" +
"            json.setJSON(json.getObject(\"path\"));\n" +
"            out = json.getString(\"res\").replace(\".\",\"/\");\n" +
"\n" +
"            if(out.charAt(0) != '/'){\n" +
"                out = \"/\"+out;\n" +
"            }\n" +
"            if (out.charAt(out.length()-1) != '/'){\n" +
"                out = out+\"/\";\n" +
"            }\n" +
"\n" +
"        }catch (Exception e){}\n" +
"\n" +
"        return new ImageIcon(pth.getPathRes(out+resName));\n" +
"    }\n" +
"\n" +
"    public static InputStream getFile(String fileName){\n" +
"\n" +
"        String out = \"\";\n" +
"\n" +
"        try {\n" +
"            BufferedReader in = new BufferedReader(new InputStreamReader(pth.getPath(\"/config/neuron.json\"), \"utf-8\"));\n" +
"            String sCadena = \"\";\n" +
"\n" +
"            while ((sCadena = in.readLine())!=null) {\n" +
"                out += sCadena;\n" +
"            }\n" +
"\n" +
"            Json json = new Json(out);\n" +
"            json.setJSON(json.getObject(\"path\"));\n" +
"            out = json.getString(\"file\").replace(\".\",\"/\");\n" +
"\n" +
"            if(out.charAt(0) != '/'){\n" +
"                out = \"/\"+out;\n" +
"            }\n" +
"            if (out.charAt(out.length()-1) != '/'){\n" +
"                out = out+\"/\";\n" +
"            }\n" +
"\n" +
"        }catch (Exception e){}\n" +
"\n" +
"        return pth.getPath(out+fileName);\n" +
"    }\n" +
"\n" +
"    public static InputStream getJson(String fileName){\n" +
"\n" +
"        String out = \"\";\n" +
"\n" +
"        try {\n" +
"            BufferedReader in = new BufferedReader(new InputStreamReader(pth.getPath(\"/config/neuron.json\"), \"utf-8\"));\n" +
"            String sCadena = \"\";\n" +
"\n" +
"            while ((sCadena = in.readLine())!=null) {\n" +
"                out += sCadena;\n" +
"            }\n" +
"\n" +
"            Json json = new Json(out);\n" +
"            json.setJSON(json.getObject(\"path\"));\n" +
"            out = json.getString(\"json\").replace(\".\",\"/\");\n" +
"\n" +
"            if(out.charAt(0) != '/'){\n" +
"                out = \"/\"+out;\n" +
"            }\n" +
"            if (out.charAt(out.length()-1) != '/'){\n" +
"                out = out+\"/\";\n" +
"            }\n" +
"\n" +
"        }catch (Exception e){}\n" +
"\n" +
"        return pth.getPath(out+fileName);\n" +
"    }\n" +
"\n" +
"    public static InputStream get(String path,String extention){\n" +
"        path = path.replace(\".\",\"/\");\n" +
"\n" +
"        if(path.charAt(0) != '/'){\n" +
"            path = \"/\"+path;\n" +
"        }\n" +
"        return pth.getPath(path+\".\"+extention);\n" +
"    }\n" +
"\n" +
"    public static File getExternal(String path, String extention,String prefix){\n" +
"\n" +
"        if(!prefix.isEmpty()){\n" +
"            path = path.replace(\".\",\"\\\\\");\n" +
"\n" +
"            if((path.charAt(0) != '/')){\n" +
"                path = prefix+\"\\\\\"+path;\n" +
"            }\n" +
"        }else{\n" +
"            path = path.replace(\".\",\"/\");\n" +
"        }\n" +
"\n" +
"        return new File(path+\".\"+extention);\n" +
"    }\n" +
"}\n" +
"\n" +
"class Pth{\n" +
"\n" +
"    public InputStream getPath(String path){\n" +
"        return getClass().getResourceAsStream(path);\n" +
"    }\n" +
"\n" +
"    public URL getPathRes(String path){\n" +
"        return getClass().getResource(path);\n" +
"    }\n" +
"}");
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
