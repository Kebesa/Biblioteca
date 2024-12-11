import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PrestamoDAO {
    private  ArrayList<Prestamo> listaPrestamo;
    static Connection conexion = JDBC.getConexion();

    /* Con este método leemos los datos de la tabla prestamo */
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

    /* Con este método insertmos los datos a la tabla prestamo */
    public void InsertarPrestamo(Prestamo p){
        String consulta = "Insert into prestamo Values(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)){
            ps.setInt(1, p.getId());
            ps.setDate(2, Date.valueOf(p.getFechaInicio()));
            ps.setDate(3, Date.valueOf(p.getFechaFin()));
            ps.setInt(4, p.getUsuarioId() );
            ps.setInt(5, p.getLibroId() );
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Con este método borramos los datos de la tabla prestamo */
    public void BorrarPrestamo(int id){
        String consulta = "Delete from prestamo where id = ? ";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Con este método actualizamos los datos de la tabla prestamo */
    public void ActualizarPrestamo(Prestamo prestamo){
        String consulta = "Update prestamo set fechaInicio = ?, fechaFin = ? where id = ?";
        try {
            PreparedStatement st = conexion.prepareStatement(consulta);
            st.setDate(1, Date.valueOf(prestamo.getFechaInicio()));
            st.setDate(2, Date.valueOf(prestamo.getFechaFin()));
            st.setInt(3, prestamo.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validarID(Prestamo prestamo) {
        int contador = 0;
        int columnas_numero = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Prestamo")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnas_numero = rsmd.getColumnCount();
            while (rs.next()) {
                if (prestamo.getId() == rs.getInt(1)){
                    contador++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (contador > 0) {
            return false;
        } else
            return true;
    }
    /* Aquí validaremos que el Prestamo tenga el mismo id, cosa que usaremos a la hora de prestamos nuevos */

    public boolean validarPrestamo(Prestamo prestamo) {
        int contador = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Prestamo")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if(rs.getDate(2).equals(Date.valueOf(prestamo.getFechaInicio())) && rs.getDate(3).equals(Date.valueOf(prestamo.getFechaFin())) && rs.getInt(4) == prestamo.getUsuarioId() && rs.getInt(5) == prestamo.getLibroId())
                    contador++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (contador > 0) {
            return false;
        } else
            return true;
    }
    /* Aquí validaremos que el prestamo tenga la misma fecha, id del usuario y del libro, cosa que usaremos a la hora de actualizarlo */

}
