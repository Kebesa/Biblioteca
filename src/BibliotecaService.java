import java.util.ArrayList;

public class BibliotecaService {
    private UsuarioDAO usuarioDAO;
    private PrestamoDAO prestamoDAO;
    private ArrayList<Usuario> listaUsuario;

    public  BibliotecaService(){
        usuarioDAO = new UsuarioDAO();
    }
    //Gestion Usuario
    public void leerUsuario(){
        listaUsuario = usuarioDAO.leerTodo();
        for (Usuario u : listaUsuario){
            System.out.println(u);
        }
    }

    public void CrearUsuario(Usuario usuario){
        usuarioDAO.InsertarUsuario(usuario);
        listaUsuario.add(usuario);
    }

    public void BorrarUsuario(int id ){
        usuarioDAO.BorrarAlumno(id);
        listaUsuario.remove(id);
    }
    //Gestion
}
