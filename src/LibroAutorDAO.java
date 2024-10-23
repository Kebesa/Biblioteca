import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LibroAutorDAO {
    static Connection conexion = JDBC.getConexion();


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
    /* Con este método leemos los datos de la tabla Libro_Autor */

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
    /* Con este método insertmos los datos a la tabla libroAutor */


    public void BorrarLibroAutor(int idLibro, int idAutor){
        String consulta = "Delete from prestamo where idLibro = ? AND idAutor = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setInt(1, idLibro);
            ps.setInt(2, idAutor);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /* Con este método borramos los datos de la tabla Libro_Autor */

    public void ActualizarLibroAutor(int idLibroNueva, int idAutorNuevo, LibroAutor libroAutor){
        String consulta = "Update prestamo set idLibro = ?, idAutor = ? where idLibro = ? AND idAutor = ?";
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
    /* Con este método actualizamos los datos de la tabla Libro_Autor */

    public void LibroNull(int ID_autor, int ID_libro) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE libro_autor SET idLibro = null WHERE idLibro = ? AND idAutor = ?")) {
            ps.setInt(1, ID_libro);
            ps.setInt(2, ID_autor);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método ponemos el id del libro a null*/

    public void LibroID(int ID_autor, int ID_libroAntiguo, int idLibroNuevo) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE libro_autor SET idLibro = ? WHERE idLibro = ? AND idAutor = ?")) {
            ps.setInt(1, idLibroNuevo);
            ps.setInt(1, ID_libroAntiguo);
            ps.setInt(2, ID_autor);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método cambiamos el id del libro*/

    public void AutoresNull(int ID_autor, int ID_libro) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE libro_autor SET idAutor = null WHERE idLibro = ? AND idAutor = ?")) {
            ps.setInt(2, ID_autor);
            ps.setInt(1, ID_libro);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Aquí cambiaremos el ID de un autor a null, que es importante a la hora de borrar algún libro para que no se borre el autor, haciendo referencia a que está sin equipo */

    public void cambiarAutorID(int ID_autor_nuevo, int ID_autor_antiguo, int ID_libro) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE libro_autor SET idAutor = ? WHERE idLibro = ? AND idAutor = ?")) {
            ps.setInt(1, ID_autor_nuevo);
            ps.setInt(2, ID_libro);
            ps.setInt(3, ID_autor_antiguo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Aquí cambiaremos el ID de un autor*/

    public boolean validarLibroID(int ID) {
        int contador = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM libro_autor")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if(rs.getInt(1) == ID)
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
    /* Aquí validaremos que el id del autor tenga el mismo ID, cosa que usaremos a la hora de borrar libros */

    public int sacarAutorID(int ID) {
        int resultado = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM libro_autor WHERE idLibro = " + ID)) {
            while (rs.next()) {
                resultado = rs.getInt(2);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    /* Aquí sacamos el id del autor */

    public boolean validarAutorID(int ID) {
        int contador = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM libro_autor")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if(rs.getInt(2) == ID)
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
    /* Aquí validaremos que el jugador tenga el mismo ID, cosa que usaremos a la hora de borrar equipos */

    public int sacarLibroID(int ID) {
        int resultado = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM libro_autor WHERE idAutor = " + ID)) {
            while (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }
    /* Aquí sacaremos el id del libro */

}
