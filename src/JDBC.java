import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    static String URL = "jdbc:mariadb://localhost:3306/biblioteca";
    static String USR = "root";
    static String PASS = "";

    private static Connection conexion = null;

    private JDBC(){
        try {
            conexion = DriverManager.getConnection(URL, USR, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /* Aquí ponemos los comandos necesarios para que la clase realice la conexión hacia la base de datos
    especificada en las variables más arriba
     */

    public static Connection getConexion() {
        if (conexion == null)
            new JDBC();
        return conexion;
    }
    /* Este método sirve para que si la variable conexión es nula y no tiene conexión a base de datos,
    darle conexión a nuestra variable.
     */

}
