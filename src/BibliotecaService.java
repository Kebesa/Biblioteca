import java.util.ArrayList;

public class BibliotecaService {
    private DAOLibro libroDao;
    private UsuarioDAO usuarioDAO;
    private PrestamoDAO prestamoDAO;
    private ArrayList<Usuario> listaUsuario;
    private ArrayList<Libro> listaLibro;
    private ArrayList<Prestamo> listaPrestamo;

    public  BibliotecaService(){
        usuarioDAO = new UsuarioDAO();
    }

    public void actualizarListaLibro(){
        libroDao.leerLibros();

    }

    //Gestion Libro
    public void leerLibro(){

    }


    //Gestion Usuario

    public void actualizarListaUsuario(){
        listaUsuario = usuarioDAO.leerTodo();
    }

    public void leerUsuario(){
        actualizarListaUsuario();
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
    //Gestion Préstamo

    public void actualizarListaPrestamo(){
        listaPrestamo = prestamoDAO.leerTodos();
    }
    public void leerTodos(){
        for (Prestamo prestamo : listaPrestamo){
            System.out.println(prestamo);
        }
    }


}
