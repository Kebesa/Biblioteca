import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BibliotecaService {
    static Connection conexion = JDBC.getConexion();
    private ArrayList<Usuario> listaUsuario;
    private ArrayList<Autor> listaAutores;
    private ArrayList<Libro> listaLibro;
    private ArrayList<Prestamo> listaPrestamo;

    public  BibliotecaService(){

    }

    public void Memoria(){
        memoriaLibro();
        memoriaPrestamo();
        memoriaUsuario();
        memoriaAutores();
    }

    public void memoriaLibro() {
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Libro")) {
            int contador = 0;
            while (rs.next()) {
                Libro libro = new Libro(rs.getInt(1),rs.getString(2), rs.getString(3));
                for (Libro libro2 : listaLibro) {
                    if (libro.getID() == libro2.getID())
                        contador++;
                }
                if (contador == 0)
                    listaLibro.add(libro);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void memoriaUsuario() {
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Usuario")) {
            int contador = 0;
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(1),rs.getString(2));
                for (Usuario usuario2 : listaUsuario) {
                    if (usuario.getId() == usuario2.getId())
                        contador++;
                }
                if (contador == 0)
                    listaUsuario.add(usuario);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void memoriaAutores() {
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Autor")) {
            int contador = 0;
            while (rs.next()) {
                Autor autor = new Autor(rs.getInt(1),rs.getString(2));
                for (Autor autor2 : listaAutores) {
                    if (autor.getID() == autor2.getID())
                        contador++;
                }
                if (contador == 0)
                    listaAutores.add(autor);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void memoriaPrestamo(){
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Prestamo")) {
            int contador = 0;
            while (rs.next()) {
                Prestamo prestamo = new Prestamo(rs.getInt(1),rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate(), rs.getInt(4), rs.getInt(5));
                for (Prestamo prestamo2 : listaPrestamo) {
                    if (prestamo.getId() == prestamo2.getId())
                        contador++;
                }
                if (contador == 0)
                    listaPrestamo.add(prestamo);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
