package org.javabrain.util.alert;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import org.javabrain.util.data.Json;

/**
 * @author Fernando García
 * @version 0.0.2
 */
public class Console {

    //Variables publicas
    //======================================================================

    //Variables privadas
    private static int count = 0;
    //======================================================================

    //Funciones para imprimir en pantalla



    public static void red(Object message){
        System.out.println("\033[31m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }
    
    public static void green(Object message){
        System.out.println("\033[32m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }
    
    public static void yellow(Object message){
        System.out.println("\033[33m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }
    
    public static void blue(Object message){
        try{
            System.out.println("\033[34m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
        }catch (Exception e){}
    }

    public static void magenta(Object message){
        System.out.println("\033[35m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }
    
    public static void cyan(Object message){
        System.out.println("\033[36m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }

    public static void white(Object message){
        System.out.println("\033[37m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }

    public static void black(Object messge){System.out.println(messge.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→"));}



    public static void redOnLine(Object message){
        System.out.print("\033[31m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }

    public static void greenOnLine(Object message){
        System.out.print("\033[32m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }

    public static void yellowOnLine(Object message){
        System.out.print("\033[33m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }

    public static void blueOnLine(Object message){
        System.out.print("\033[34m"+structur(message)+"\033[30m");
    }

    public static void magentaOnLine(Object message){
        System.out.print("\033[35m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }

    public static void cyanOnLine(Object message){
        System.out.print("\033[36m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }

    public static void whiteOnLine(Object message){
        System.out.print("\033[37m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
    }

    public static void blackOnLine(Object messge){System.out.print(messge.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→"));}

    public static void colorOnLine(Object message){
        if(count == 8){count = 0;}
        switch (count)
        {
            case 0: redOnLine(message); count++; return;
            case 1: greenOnLine(message); count++; return;
            case 2: yellowOnLine(message); count++; return;
            case 3: blueOnLine(message); count++; return;
            case 4: magentaOnLine(message); count++; return;
            case 5: cyanOnLine(message); count++; return;
            case 6: whiteOnLine(message); count++; return;
            case 7: blackOnLine(message); count++; return;
        }
    }

    //todo metodo incompleto
    /*Este metodo soporta lenguaje JSON
     * falta hacer que soporte Java
     * aun no esta terminado*/
    public static void code(Object message){

        int type = -1;
        try{
            Json json = new Json(message);
            message = json.toJSONString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→");
            type = 0;
        }catch (Exception e){}

        switch (type) {

            case 0:
                String mess = message.toString();
                boolean text = false;

                for (int i = 0; i < mess.length(); i++) {
                    boolean number = true;
                    switch (mess.charAt(i)) {
                        case '[': magentaOnLine(mess.charAt(i)); break;
                        case ']': magentaOnLine(mess.charAt(i)); break;

                        case '{': cyanOnLine(mess.charAt(i)); break;
                        case '}': cyanOnLine(mess.charAt(i)); break;

                        case ':': greenOnLine(mess.charAt(i)); break;

                        case '/': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '*': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '-': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '+': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '=': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '?': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '¿': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '!': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '¡': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '<': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '>': yellowOnLine(mess.charAt(i));  number = false; break;
                        case ';': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '&': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '%': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '$': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '@': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '|': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '❤': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '☺': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '☹': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '←': yellowOnLine(mess.charAt(i));  number = false; break;
                        case '→': yellowOnLine(mess.charAt(i));  number = false; break;

                        case '"':
                            blueOnLine(mess.charAt(i));
                            if(text){
                                text = false;
                            }else {
                                text = true;
                            }
                        break;

                        default:

                            if(text){
                                blueOnLine(mess.charAt(i));
                            }else{

                                if (!text){
                                    switch (mess.charAt(i))  {
                                        case '0': redOnLine(mess.charAt(i));  number = false; break;
                                        case '1': redOnLine(mess.charAt(i));  number = false; break;
                                        case '2': redOnLine(mess.charAt(i));  number = false; break;
                                        case '3': redOnLine(mess.charAt(i));  number = false; break;
                                        case '4': redOnLine(mess.charAt(i));  number = false; break;
                                        case '5': redOnLine(mess.charAt(i));  number = false; break;
                                        case '6': redOnLine(mess.charAt(i));  number = false; break;
                                        case '7': redOnLine(mess.charAt(i));  number = false; break;
                                        case '8': redOnLine(mess.charAt(i));  number = false; break;
                                    }
                                }

                                if (number){
                                    blackOnLine(mess.charAt(i));
                                }
                            }
                    }
                }

                break;
        }
        breakLine();
    }
    
    public static void viewer(Object message){
        if (message != null) {
            Json json2 = null;
            Json json = null;
            int type = -1;
            boolean isJson = true;
            try {
                json = new Json(message);
                message = json.toJSONString().replace("<3", "❤").replace(":)", "☺")
                        .replace(":(", "☹").replace("<-", "←")
                        .replace("->", "→");
                json2 = new Json(message);
                type = 0;
                isJson = false;
            } catch (Exception e) {
            }

            if (message.toString().contains("http") && isJson) {
                type = 1;
            }

            try {
                ImageIcon icon = (ImageIcon) message;
                type = 2;
            } catch (Exception e){}

            //Caragar elementos internos en una clase estatica
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Image img = new ImageIcon(classLoader.getResource("res/component/json.png")).getImage();
            switch (type) {
                case 0:
                    new SwingTree(json2, json, img);
                    break;
                case 1: {
                    try {
                        new ImageViewer(new URL(message.toString()), img);
                    } catch (Exception ex) {
                        Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case 2:{
                    try {
                        new ImageViewer(message, img);
                    } catch (Exception ex) {
                        Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                default:
                    new JavaViewer(message.toString(), img);
            }
            breakLine();
        }else {
            System.err.println("Untraceable content");
        }
    }
    
    //=======================================================================

    //Funciones para imprecion sin entrada
    public static void tabulation(){
        System.out.print("\t");
    }

    public static void breakLine(){
        System.out.print("\n");
    }
    //=======================================================================

    private static String structur(Object message) {
        String out = "";
        try {
            out = message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→");
        } catch (Exception e) {
            return "Emply";
        }
        return out;
    }

}

//Clase para cargar todo con respecto a Json
//Falta cambiar los scrolls por unos mas nuevos y el boton
class SwingTree extends JFrame {
  private org.javabrain.swing.container.ScrollPanel scrollPane = new org.javabrain.swing.container.ScrollPanel();
  private JTree tree;
  private Renderer renderer = new Renderer();

  public SwingTree(Json json,Json real,Image img) {
    DefaultMutableTreeNode root = renderJson("Root",json,"#2196F3");
    tree = new JTree(root);
    getContentPane().setLayout(new BorderLayout());
    tree.setCellRenderer(renderer);
    scrollPane.getViewport().add(tree);
    org.javabrain.swing.control.Button button = new org.javabrain.swing.control.Button("Copy");
    getContentPane().add("Center", scrollPane);
    getContentPane().add(BorderLayout.SOUTH,button);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(500, 500);
    setVisible(true);
    setLocationRelativeTo(null);
    setTitle("JSON Viewer");
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection ss = new StringSelection(real.toJSONString());
            cb.setContents(ss, ss);
        }
    });
    setIconImage(img);
  }
  
  private DefaultMutableTreeNode renderJson(String pre,Json json,String color){

    DefaultMutableTreeNode root = null;

    int a = 1;
    if(json.isJSONArray()){
        root = new DefaultMutableTreeNode(html(pre,"Array","#2196F3"));

        for(Json js:json.values()){
            if(js.isJSONObject()){
                root.add(renderJson("item "+a,js,color));
            }
            a++;
        }
        
    }else{
        root = new DefaultMutableTreeNode(html(pre,"Object",color));
        for(Object key:json.getKeys()){
            try{
                Json obj = json.getJSON(key);
                if(obj.isJSONArray()){
                    root.add(renderJson(key.toString(),obj,color));
                }else{
                    root.add(renderJson(key.toString(),obj,"#2196F3"));
                }
                
            }catch(Exception e){
                String result = json.getString(key);
                int typo= 0;

                try {
                    if(result.isEmpty()){
                        typo = 1;
                    }
                }catch (Exception ex){}
                
                try{
                    Integer.parseInt(result);
                    typo = 2;
                }catch(Exception ex){}
                
                try{
                    if(result.charAt(0) == '['){
                       typo = 3;
                    }
                }catch(Exception ex){}

                try {
                    if(result.contains("http")){
                        typo = 4;
                    }
                }catch (Exception ex){}

                try{
                    if(result.contains(";base64,")){
                        typo = 5;
                    }
                }catch (Exception ex){}

                try {
                    if (result == null){
                        typo = 6;
                    }
                }catch (Exception ex){}

                switch(typo){
                    case 1: root.add(new DefaultMutableTreeNode(element(key.toString(),"emply","#9E9E9E"))); break;
                    case 2: root.add(new DefaultMutableTreeNode(element(key.toString(),result,"#EC407A"))); break;
                    case 3: root.add(new DefaultMutableTreeNode(element(key.toString(),result,"#26A69A"))); break;
                    case 4: root.add(new DefaultMutableTreeNode(element(key.toString(),"<a href=\""+result+"\">"+result+"</a>","#90CAF9"))); break;
                    case 5: root.add(new DefaultMutableTreeNode(element(key.toString(),"base64 ﬦ","#FF9800"))); break;
                    case 6: root.add(new DefaultMutableTreeNode(element(key.toString(),"null","#3F51B5"))); break;
                    default:
                       root.add(new DefaultMutableTreeNode(element(key.toString(),"\""+result+"\"","#EF5350"))); 
                }
            }
        }
    }

    return root;
  }
  
  private String html(String pre,String text,String color){
      return "<html>" +
                "<font color=\"#BA68C8\">&nbsp;&nbsp;"+
                    pre+":"+
                "</font>"+
                "<font color=\""+color+"\">&nbsp;&nbsp;"+
                    text+
                "</font></html>";
  }
  
  private String element(String key,String obj,String css){
      return "<html>" +
                "<font color=\"#BA68C8\">&nbsp;&nbsp;"+
                    key+":"+
                "</font>"+
                "<font color=\""+css+"\">&nbsp;"+
                    obj+
                "</font>"
              + "</html>";
  }
  
class Renderer extends JLabel implements TreeCellRenderer {
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
      boolean expanded, boolean leaf, int row, boolean hasFocus) {
    setText(value.toString() + "                   ");
    return this;
  }
}
}
//==============================================================================

//Clase para cargar todo con respecto a clases Java
class JavaViewer extends JFrame{
    
    private org.javabrain.swing.container.ScrollPanel scrollPane = new org.javabrain.swing.container.ScrollPanel();
    
    public JavaViewer(String code,Image img) {
        JLabel label = new JLabel(renderCode(code));
        label.setVerticalAlignment(JLabel.TOP);
        scrollPane.getViewport().add(label);
        scrollPane.getViewport().setBackground(Color.white);
        getContentPane().add("Center", scrollPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Java Viewer");
        setIconImage(img);
    }
    
    private String renderCode(String code){
        
        code = code.replace("0","°!0")
                   .replace("1","°!1")
                   .replace("2","°!2")
                   .replace("3","°!3")
                   .replace("4","°!4")
                   .replace("5","°!5")
                   .replace("6","°!6")
                   .replace("7","°!7")
                   .replace("8","°!8")
                   .replace("9","°!9")
                   .replace("/","°!/");
        
        
        code = code.replace("°!/","<font color=\"#9C27B0\">/</font>")
                   .replace("=","<font color=\"#2196F3\">=</font>")
                   .replace("public","<font color=\"#16abc6\">public</font>")
                   .replace("import","<font color=\"#16abc6\">import</font>")
                   .replace("package","<font color=\"#16abc6\">package</font>")
                   .replace("static","<font color=\"#16abc6\">static</font>")
                   .replace("class","<font color=\"#16abc6\">class</font>")
                   .replace("void","<font color=\"#16abc6\">void</font>")
                   .replace("super","<font color=\"#16abc6\">super</font>")
                   .replace("private","<font color=\"#16abc6\">private</font>")
                   .replace("extends","<font color=\"#16abc6\">extends</font>")
                   .replace("implements","<font color=\"#16abc6\">implements</font>")
                   .replace("new","<font color=\"#16abc6\">new</font>")
                   .replace("try","<font color=\"#16abc6\">try</font>")
                   .replace("catch","<font color=\"#16abc6\">catch</font>")
                   .replace("throw","<font color=\"#16abc6\">throw</font>")
                   .replace("while","<font color=\"#16abc6\">while</font>")
                   .replace("for","<font color=\"#16abc6\">for</font>")
                   .replace("if","<font color=\"#16abc6\">if</font>")
                   .replace("else","<font color=\"#16abc6\">else</font>")
                   .replace("case","<font color=\"#16abc6\">case</font>")
                   .replace("continue","<font color=\"#16abc6\">continue</font>")
                   .replace("default","<font color=\"#16abc6\">default</font>")
                   .replace("this","<font color=\"#16abc6\">this</font>")
                   .replace("return","<font color=\"#16abc6\">return</font>")
                   .replace("@Override","<font color=\"#fdb657\">@Override</font>")
                   .replace("°!0","<font color=\"#fdb657\">0</font>")
                   .replace("°!1","<font color=\"#fdb657\">1</font>")
                   .replace("°!2","<font color=\"#fdb657\">2</font>")
                   .replace("°!3","<font color=\"#fdb657\">3</font>")
                   .replace("°!4","<font color=\"#fdb657\">4</font>")
                   .replace("°!5","<font color=\"#fdb657\">5</font>")
                   .replace("°!6","<font color=\"#fdb657\">6</font>")
                   .replace("°!7","<font color=\"#fdb657\">7</font>")
                   .replace("°!8","<font color=\"#fdb657\">8</font>")
                   .replace("°!9","<font color=\"#fdb657\">9</font>")
                   .replace("@author","<font color=\"#fdb657\">@author</font>")
                   .replace("@version","<font color=\"#fdb657\">@version</font>")
                   .replace("{","<font color=\"#EC407A\">{</font>")
                   .replace("}","<font color=\"#EC407A\">}</font>")
                   .replace("Json","<font color=\"#E53935\">Json</font>")
                   .replace("!","<font color=\"#2196F3\">!</font>")
                   .replace("|","<font color=\"#2196F3\">|</font>")
                   .replace("&","<font color=\"#2196F3\">&</font>")
                   .replace("+","<font color=\"#9C27B0\">+</font>")
                   .replace("-","<font color=\"#9C27B0\">-</font>")
                   .replace("*","<font color=\"#9C27B0\">*</font>")
                   .replace("%","<font color=\"#9C27B0\">%</font>")
                   .replace("\n","<br>")
                   .replace("    ","&nbsp;&nbsp&nbsp;&nbsp;");
        
        
        
        return "<html>"+code+"</html>";
    }
}

//Clase para cargar todo con respecto a clases Java
class ImageViewer extends JFrame{
    
    private org.javabrain.swing.container.ScrollPanel scrollPane = new org.javabrain.swing.container.ScrollPanel();
    
    public ImageViewer(Object image,Image img) throws URISyntaxException {
        try {
            URL url = (URL) image;
            if (url.toURI().toString().endsWith(".png") ||
                    url.toURI().toString().endsWith(".jpg") ||
                    url.toURI().toString().endsWith(".gif")) {

                JLabel label = new JLabel(new ImageIcon(url));
                scrollPane.getViewport().add(label);
                scrollPane.getViewport().setBackground(Color.white);
                getContentPane().add("Center", scrollPane);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setSize(500, 500);
                setVisible(true);
                setLocationRelativeTo(null);
                setTitle("Image Viewer");
                setIconImage(img);

            } else {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();

                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        try {
                            desktop.browse(new URI(url.toString()));
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }catch (Exception e){
            JLabel label = new JLabel((ImageIcon) image);
            scrollPane.getViewport().add(label);
            scrollPane.getViewport().setBackground(Color.white);
            getContentPane().add("Center", scrollPane);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(500, 500);
            setVisible(true);
            setLocationRelativeTo(null);
            setTitle("Image Viewer");
            setIconImage(img);
        }
    }
}