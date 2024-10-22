import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {
    private String bd = "biblioteca";
    private String url="jdbc:mariadb://localhost/" + bd;
    private String user ="root";
    private String password = "";
    private ArrayList<Usuario> listaUsuarios;
    private Connection conexion;
    public UsuarioDAO(){
        listaUsuarios = new ArrayList<Usuario>();
        try {
            conexion = DriverManager.getConnection( url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

    public void BorrarAlumno(int id){
        String consulta = "Delete from usuario where id = ? ";
        try {
            PreparedStatement ps = conexion.prepareStatement(consulta);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ActualizarAlumno(int id, String nombre){
        String consulta = "Update usuario set nombre = ? where id = ?";
        try {
            PreparedStatement st = conexion.prepareStatement(consulta);
            st.setString(1, nombre);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}