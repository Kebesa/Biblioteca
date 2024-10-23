import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
    private ArrayList<Usuario> listaUsuarios;
    static Connection conexion = JDBC.getConexion();


    public ArrayList<Usuario> leerTodo(){
        String consulta="Select * from usuario";
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while(rs.next()){
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                Usuario u = new Usuario(id , nombre);
                listaUsuarios.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }
    /* Con este método leemos los datos de la tabla Usuario */


    public void InsertarUsuario(Usuario u){
        String consulta = "Insert into usuario Values(?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(consulta)){
            ps.setInt(1, u.getId());
            ps.setString(2, u.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /* Con este método insertamos los datos en la tabla Usuario */


    public void BorrarUsuario(int id){
        String consulta = "Delete from usuario where id = ? ";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /* Con este método borramos los datos de la tabla Usuario con el id*/


    public void ActualizarUsuario(Usuario usuario){
        String consulta = "Update usuario set nombre = ? where id = ?";
        try {
            PreparedStatement st = conexion.prepareStatement(consulta);
            st.setString(1, usuario.getNombre());
            st.setInt(2, usuario.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /* Con este método actualizamos los datos de la tabla Usuario */

    public boolean validarID(Usuario usuario) {
        int contador = 0;
        int columnas_numero = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Usuario")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnas_numero = rsmd.getColumnCount();
            while (rs.next()) {
                if (usuario.getId() == rs.getInt(1)){
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
    /* Aquí validaremos que el Usuario tenga el mismo id , cosa que usaremos a la hora de insertar nuevos Usuarios */

    public boolean validarUsuario(Usuario usuario) {
        int contador = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Usuario")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if(rs.getString(2).equals(usuario.getNombre()))
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
    /* Aquí validaremos que el jugador tenga el mismo nombre, cosa que usaremos a la hora de actualizarlo */

    public boolean validarUsuarioID(int ID) {
        int contador = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM prestamo")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if(rs.getInt(4) == ID)
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

    public void cambiarUsuarioID(int ID_usuario_nuevo, int ID_usuario_antiguo, int ID_prestamo) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE prestamo SET usuarioId = ? WHERE id = ? AND usuarioId = ?")) {
            ps.setInt(1, ID_usuario_nuevo);
            ps.setInt(2, ID_prestamo);
            ps.setInt(3, ID_usuario_antiguo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Aquí cambiaremos el ID de un autor*/

    public void UsuarioNull(int ID_prestamo, int ID_usuario) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE prestamo SET usuarioId = null WHERE id = ? AND usuarioId = ?")) {
            ps.setInt(2, ID_prestamo);
            ps.setInt(1, ID_usuario);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Aquí cambiaremos el ID de un autor a null, que es importante a la hora de borrar algún libro para que no se borre el autor, haciendo referencia a que está sin equipo */

    public int sacarPrestamoID(int ID) {
        int resultado = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM prestamo WHERE usuarioId = " + ID)) {
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
