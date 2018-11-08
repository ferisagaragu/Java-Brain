
import org.javabrain.util.data.Json;
import org.javabrain.util.resource.Layout;
import org.javabrain.util.resource.Archive;


public class Run {
    
    public static void main(String[] args) {
        
        Layout layout = new Layout("C:\\Users\\Fernando García\\Desktop\\Legend of Zelda, The (U) (PRG1) [!].nes");
        layout.put("servername","localhost:3306");
        layout.put("username","fernny27");
        System.out.println(layout);
        
    }

}



