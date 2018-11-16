package brain.test;

import org.javabrain.Neuron;
import org.javabrain.util.resource.R;


/**
 *
 * @author Fernando García
 */
public class BrainTest{

    public static void main(String[] args) {
        Neuron.init();
        //System.out.println(R.getLayout("deletelocation.php"));
        //System.out.println(R.getLayout("fracment_1.txt"));
        System.out.println(R.getDrawable("index.fxml"));
        System.out.println(Neuron.param("name"));
        Object o = R.getScript("script.js").invokeFunction("hola"," Fernando");
        System.out.println(o);
    }
    
}









































































