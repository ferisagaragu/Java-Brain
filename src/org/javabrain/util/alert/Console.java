package org.javabrain.util.alert;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import org.javabrain.util.data.Json;

/**
 * @author Fernando García
 * @version 0.0.2
 */
public class Console {

    //Variables publicas
    public static final int JSON = 0;

    //======================================================================

    //Variables privadas
    private static int count = 0;
    //======================================================================

    //Funciones pra imprimir en pantalla

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
        System.out.println("\033[34m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
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
        System.out.print("\033[34m"+message.toString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→")+"\033[30m");
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
        Json json2 = null;
        Json json = null;
        int type = -1;
        try{
            json = new Json(message);
            message = json.toJSONString().replace("<3","❤").replace(":)","☺")
                    .replace(":(","☹").replace("<-","←")
                    .replace("->","→");
            json2 = new Json(message);
            type = 0;
        }catch (Exception e){}
        //Caragar elementos internos en una clase estatica
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Image img = new ImageIcon(classLoader.getResource("res/component/json.png")).getImage();
        switch (type) {
            case 0: new SwingTree(json2,json,img); break;
        }
        breakLine();
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
}

//Clase para cargar todo con respecto a Json
//Falta cambiar los scrolls por unos mas nuevos y el boton
class SwingTree extends JFrame {
  private JScrollPane scrollPane = new JScrollPane();
  private JTree tree;
  private Renderer renderer = new Renderer();

  public SwingTree(Json json,Json real,Image img) {
    DefaultMutableTreeNode root = renderJson("Root",json,"#2196F3");
    tree = new JTree(root);
    getContentPane().setLayout(new BorderLayout());
    tree.setCellRenderer(renderer);
    scrollPane.getViewport().add(tree);
    Button button = new Button("Copy");
    getContentPane().add("Center", scrollPane);
    getContentPane().add(BorderLayout.SOUTH,button);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(500, 500);
    setVisible(true);
    setLocationRelativeTo(null);
    setTitle("JSON Viwer");
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
                int type= 0;
                
                if(result.isEmpty()){
                    type = 1;
                }
                
                try{
                    Integer.parseInt(result);
                    type = 2;
                }catch(Exception ex){}
                
                try{
                    if(result.charAt(0) == '['){
                       type = 3;
                    }
                }catch(Exception ex){}
                
                if(result.contains("http")){
                    type = 4;
                }
                
                if(result.contains(";base64,")){
                    type = 5;
                }
                
                switch(type){
                    case 1: root.add(new DefaultMutableTreeNode(element(key.toString(),"emply","#9E9E9E"))); break;
                    case 2: root.add(new DefaultMutableTreeNode(element(key.toString(),result,"#EC407A"))); break;
                    case 3: root.add(new DefaultMutableTreeNode(element(key.toString(),result,"#26A69A"))); break;
                    case 4: root.add(new DefaultMutableTreeNode(element(key.toString(),result,"#90CAF9"))); break;
                    case 5: root.add(new DefaultMutableTreeNode(element(key.toString(),"base64 ﬦ","#FF9800"))); break;
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
//==============================================================================

//Clase para cargar todo con respecto a clases Java

class JavaViewer extends JFrame{
    
    /*public JavaViewer(String code,Image img) {
    DefaultMutableTreeNode root = renderJson("Root",json,"#2196F3");
    tree = new JTree(root);
    getContentPane().setLayout(new BorderLayout());
    tree.setCellRenderer(renderer);
    tree.addTreeSelectionListener(new TreeHandler());
    scrollPane.getViewport().add(tree);
    Button button = new Button("Copy");
    getContentPane().add("Center", scrollPane);
    getContentPane().add(BorderLayout.SOUTH,button);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(500, 500);
    setVisible(true);
    setLocationRelativeTo(null);
    setTitle("JSON Viwer");
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection ss = new StringSelection(real.toJSONString());
            cb.setContents(ss, ss);
        }
    });
    setIconImage(img);*/
  }
}