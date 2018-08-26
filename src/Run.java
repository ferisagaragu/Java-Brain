
import org.javabrain.fx.structure.Controller;
import org.javabrain.util.data.Type;
import org.javabrain.util.web.service.Petition;
import org.javabrain.util.web.service.Valuta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Run {

    public static void main(String[] args) throws Exception {
        System.out.println(Valuta.usdToMnx(0.080f));
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
