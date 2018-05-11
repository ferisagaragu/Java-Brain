package org.javabrain.util.alert;

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
