import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PrestamoDAO {
    private  ArrayList<Prestamo> listaPrestamo;
    static Connection conexion = JDBC.getConexion();

    public ArrayList<Prestamo> leerTodos(){
        String consulta = "Select * from prestamo";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()){
                int id = rs.getInt(1);
                LocalDate fechaIn = rs.getDate(2).toLocalDate();
                LocalDate fechaFIn = rs.getDate(3).toLocalDate();
                int usuarioId = rs.getInt(4);
                int libroId = rs.getInt(5);
                Prestamo prestamo = new Prestamo(id, fechaIn, fechaFIn, usuarioId, libroId);
                listaPrestamo.add(prestamo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPrestamo;
    }

}
