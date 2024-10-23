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
    /* Aquí validaremos que el Libro tenga el mismo id, cosa que usaremos a la hora de insertar nuevos Libros */

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
    /* Aquí validaremos que el Libro tenga el mismo nombre e isbn  */

    public boolean validarLibroID(int ID) {
        int contador = 0;
        try (Statement sentencia = conexion.createStatement();
             ResultSet rs = sentencia.executeQuery("SELECT * FROM prestamo")) {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if(rs.getInt(5) == ID)
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

    public void cambiarLibroID(int ID_usuario_nuevo, int ID_usuario_antiguo, int ID_prestamo) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE prestamo SET libroId = ? WHERE id = ? AND libroId = ?")) {
            ps.setInt(1, ID_usuario_nuevo);
            ps.setInt(2, ID_prestamo);
            ps.setInt(3, ID_usuario_antiguo);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /* Aquí cambiaremos el ID de un autor*/

    public void LibroNull(int ID_prestamo, int ID_usuario) {
        try(PreparedStatement ps = conexion.prepareStatement("UPDATE prestamo SET libroId = null WHERE id = ? AND libroId = ?")) {
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
             ResultSet rs = sentencia.executeQuery("SELECT * FROM prestamo WHERE libroId = " + ID)) {
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
