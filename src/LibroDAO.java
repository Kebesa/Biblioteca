import java.sql.*;

public class LibroDAO {
    static Connection conexion = JDBC.getConexion();

    public void leerLibros() {
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Libro")) {
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
    /* Con este método se mostrará en pantalla todos los datos de los libros que hay en la biblioteca, es decir, en la base de datos */

    public void insertarLibros(Libro libro) {
        try(PreparedStatement ps = conexion.prepareStatement("INSERT INTO Libro VALUES (?, ?, ?);")) {
            ps.setInt(1, libro.getID());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getIsbn());
            ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método insertaremos los datos de los libros de la biblioteca a la base de datos */

    public void actualizarLibros(Libro libro) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE Libro SET titulo = ?, isbn = ? WHERE id = ?")) {
            ps.setInt(3, libro.getID());
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método actualizaremos todos los datos de los libros de la biblioteca en la base de datos */

    public void borrarLibros(int ID) {
        try(PreparedStatement ps = conexion.prepareStatement("DELETE FROM Libro WHERE id = ?")){
            ps.setInt(1, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Con este método borraremos todos los libros que queramos especificando el ID del libro en la base de datos */

    public boolean validarID(Libro libro) {
        int contador = 0;
        int columnas_numero = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Libro")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            columnas_numero = rsmd.getColumnCount();
            while (rs.next()) {
                if (libro.getID() == rs.getInt(1)){
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
    /* Aquí validaremos que el departamento tenga el mismo nombre, cosa que usaremos a la hora de insertar nuevos departamentos */

    public boolean validarLibro(Libro libro) {
        int contador = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM Libro")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if(rs.getString(2).equals(libro.getTitulo()) && rs.getString(3).equals(libro.getIsbn()))
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
}
