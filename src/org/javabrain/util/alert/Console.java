package org.javabrain.util.alert;

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
        System.out.println("\033[31m"+message+"\033[30m");
    }
    
    public static void green(Object message){
        System.out.println("\033[32m"+message+"\033[30m");
    }
    
    public static void yellow(Object message){
        System.out.println("\033[33m"+message+"\033[30m");
    }
    
    public static void blue(Object message){
        System.out.println("\033[34m"+message+"\033[30m");
    }

    public static void magenta(Object message){
        System.out.println("\033[35m"+message+"\033[30m");
    }
    
    public static void cyan(Object message){
        System.out.println("\033[36m"+message+"\033[30m");
    }

    public static void white(Object message){
        System.out.println("\033[37m"+message+"\033[30m");
    }

    public static void black(Object messge){System.out.println(messge);}



    public static void redOnLine(Object message){
        System.out.print("\033[31m"+message+"\033[30m");
    }

    public static void greenOnLine(Object message){
        System.out.print("\033[32m"+message+"\033[30m");
    }

    public static void yellowOnLine(Object message){
        System.out.print("\033[33m"+message+"\033[30m");
    }

    public static void blueOnLine(Object message){
        System.out.print("\033[34m"+message+"\033[30m");
    }

    public static void magentaOnLine(Object message){
        System.out.print("\033[35m"+message+"\033[30m");
    }

    public static void cyanOnLine(Object message){
        System.out.print("\033[36m"+message+"\033[30m");
    }

    public static void whiteOnLine(Object message){
        System.out.print("\033[37m"+message+"\033[30m");
    }

    public static void blackOnLine(Object messge){System.out.print(messge);}

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
    public static void code(Object message,int type){

        switch (type) {

            case 0:
                String mess = message.toString();
                boolean text = false;
                boolean curly = false;

                for (int i = 0; i < mess.length(); i++) {
                    switch (mess.charAt(i)) {
                        case '{':
                            red(mess.charAt(i));
                            curly = true;
                            if (curly) {
                                tabulation();
                            }
                            break;
                        case '}':
                            redOnLine("\n" + mess.charAt(i));
                            curly = false;
                            break;
                        case '"':
                            blueOnLine(mess.charAt(i));
                            if (text) {
                                text = false;
                            } else {
                                text = true;
                            }
                            break;
                        case ',':
                            black(",");
                            if (curly) {
                                tabulation();
                            }
                            break;
                        case ':':
                            magentaOnLine(mess.charAt(i));
                            break;
                        default:
                            if (text) {
                                if (curly) {

                                }
                                blueOnLine(mess.charAt(i));
                            } else {
                                blackOnLine(mess.charAt(i));
                            }
                    }
                }

                break;


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
}
