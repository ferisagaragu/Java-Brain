import org.javabrain.util.alert.Console;
import org.javabrain.util.data.Json;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Run {

    public static void main(String[] args) {

        Json json = null;
        try{
            String query = "select * from reportfailures;";
            Statement st = connectDatabase().createStatement();
            ResultSet rs = st.executeQuery(query);
            json = new Json(rs);
        }catch (Exception e){System.err.println(e.getMessage());}
        Console.viewer(json);

    }

    public static Connection connectDatabase() {
        try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/atencion",
                    "postgres", "root");

            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            return connection;
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
        return null;
    }

    /*todo Classes por añadir
     -Clase Date (Añadir a esta la suma de fechas y un .format)
     */

}
