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
    public void BorrarAlumno(int id){
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
    public void ActualizarAlumno(int id, LocalDate fechaInicio,LocalDate fechaFin){
        String consulta = "Update prestamo set fechaInicio = ?, fechaFin = ? where id = ?";
        try {
            PreparedStatement st = conexion.prepareStatement(consulta);
            st.setDate(1, Date.valueOf(fechaInicio));
            st.setDate(2, Date.valueOf(fechaFin));
            st.setInt(3, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
