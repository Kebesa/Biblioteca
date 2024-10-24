import java.sql.*;
import java.util.List;

public class AutorDAO {
    static Connection conexion = JDBC.getConexion();


    /* Con esto sincronizaremos la base de datos a nuestra lista */


    public void leerAutores() {
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Autor")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnas_numero = rsmd.getColumnCount();
            for (int i = 1; i <= columnas_numero; i++) {
                if (i > 1)
                    System.out.print(" ");
                String columna_nombre = rsmd.getColumnName(i);
                System.out.print(columna_nombre);
            }
            System.out.println("\n");
            while (rs.next()) {
                for (int b = 1; b <= columnas_numero; b++) {
                    for (int c = 0; c < columnas_numero; c++) {
                        String columnas_datos = rs.getString(b);
                        System.out.print(columnas_datos + " ");
                        b++;
                    }
                    System.out.print("\n");
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método se mostrará en pantalla todos los datos de los autores que hay en los libros de la biblioteca, es decir, en la base de datos */

    public void insertarAutores(Autor autor) {
        try(PreparedStatement ps = conexion.prepareStatement("INSERT INTO Autor VALUES (?, ?);")) {
            ps.setInt(1, autor.getID());
            ps.setString(2, autor.getNombre());
            ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método insertaremos los datos de los autores de los libros de la biblioteca a la base de datos */

    public void actualizarAutores(Autor autor) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE Autor SET nombre = ? WHERE id = ?")) {
            ps.setInt(2, autor.getID());
            ps.setString(1, autor.getNombre());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método actualizaremos todos los datos de los autores de los libros de la biblioteca en la base de datos */

    public void borrarAutores(int ID) {
        try(PreparedStatement ps = conexion.prepareStatement("DELETE FROM Autor WHERE id = ?")){
            ps.setInt(1, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método borraremos todos los autores que queramos especificando el ID del autor en la base de datos */

    public boolean validarID(Autor autor) {
        int contador = 0;
        int columnas_numero = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Autor")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnas_numero = rsmd.getColumnCount();
            while (rs.next()) {
                if (autor.getID() == rs.getInt(1)){
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
    /* Aquí validaremos que el autor tenga el mismo id, cosa que usaremos a la hora de insertar nuevos autores */

    public boolean validarAutor(Autor autor) {
        int contador = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Autor")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if(rs.getString(2).equals(autor.getNombre()))
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
    /* Aquí validaremos que el autor tenga el mismo nombre, cosa que usaremos a la hora de actualizarlo */

}
