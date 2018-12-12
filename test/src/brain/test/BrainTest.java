package brain.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.javabrain.Neuron;
import org.javabrain.util.data.Text;


/**
 *
 * @author Fernando García
 */
public class BrainTest {
    
    public static void main(String[] args) throws SQLException {
        Neuron.init();
        Text t = new Text(":smile:Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",15);
        System.out.println(t);
    }
}









































































