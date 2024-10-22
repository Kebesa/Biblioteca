import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LibroAutorDAO {
    private int idLibro;
    private int idAutor;
    static Connection conexion = JDBC.getConexion();

    public LibroAutorDAO(int idLibro, int idAutor){
        this.idLibro = idLibro;
        this.idAutor = idAutor;
    }

    /* Con este método leemos los datos de la tabla Libro_Autor */
    public void leerTodos(){
        String consulta = "Select * from libro_autor";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()){
                int idLibro = rs.getInt(1);
                int idAutor = rs.getInt(2);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Con este método insertmos los datos a la tabla libroAutor */
    public void InsertarPrestamo(LibroAutor libroAutor){
        String consulta = "Insert into libro_autor Values(?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)){
            ps.setInt(1, libroAutor.getIdLibro());
            ps.setInt(2, libroAutor.getIdAutor());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Con este método borramos los datos de la tabla Libro_Autor */
    public void BorrarLibroAutor(int idLibro, int idAutor){
        String consulta = "Delete from prestamo where idLibro = ?, idAutor = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setInt(1, idLibro);
            ps.setInt(2, idAutor);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Con este método actualizamos los datos de la tabla Libro_Autor */
    public void ActualizarLibroAutor(int idLibroNueva, int idAutorNuevo, LibroAutor libroAutor){
        String consulta = "Update prestamo set idLibro = ?, idAutor = ? where idLibro = ?, idAutor = ?";
        try {
            PreparedStatement st = conexion.prepareStatement(consulta);
            st.setInt(1, idLibroNueva);
            st.setInt(2, idAutorNuevo);
            st.setInt(3, libroAutor.getIdLibro());
            st.setInt(4, libroAutor.getIdAutor());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
