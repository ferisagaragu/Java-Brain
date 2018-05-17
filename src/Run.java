import javax.swing.ImageIcon;
import org.javabrain.util.data.Path;


public class Run {

    public static void main(String[] args) {
        
        /*try {
            BufferedReader in = new BufferedReader(new InputStreamReader(File.get(""), "utf-8"));
            String sCadena;
            while ((sCadena = in.readLine())!=null) {
                Console.black(sCadena); 
            }
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        new ImageIcon(Path.getRes("C:\\GitKraken\\Java-Brain\\src\\res\\component\\json.png"));
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
