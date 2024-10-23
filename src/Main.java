import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BibliotecaService gestion = new BibliotecaService();
        Scanner teclado = new Scanner(System.in);
        LibroDAO Libro_gestion = new LibroDAO();
        AutorDAO Autor_gestion = new AutorDAO();
        UsuarioDAO Usuario_gestion = new  UsuarioDAO();
        PrestamoDAO Prestamo_gestion = new PrestamoDAO();
        LibroAutorDAO LibroAutor = new LibroAutorDAO();
        boolean sigo = true;
        while (sigo) {
            gestion.Memoria();
            System.out.println("MENÚ DE INTERACCIÓN" + "\n" +
                    "1 - Leer todo" + "\n" +
                    "2 - Insertar todo" + "\n" +
                    "3 - Actualizar todo" + "\n" +
                    "4 - Borrar libros, autores, préstamos o usuarios" + "\n" +
                    "* - Salir");
            switch (teclado.next()) {
                case "1":
                    System.out.println("Libros" + "\n");
                    Libro_gestion.leerLibros();
                    System.out.println("Autores" + "\n");
                    Autor_gestion.leerAutores();
                    System.out.println("Usuarios" + "\n");
                    Usuario_gestion.leerTodo();
                    System.out.println("Préstamos" + "\n");
                    Prestamo_gestion.leerTodos();
                    break;
                case "2":
                    for (Libro libro : gestion.getListaLibro()) {
                        if (!Libro_gestion.validarID(libro)) {
                            System.out.println("Ya hay un libro con ese ID");
                        } else {
                            Libro_gestion.insertarLibros(libro);
                        }
                    }
                    for (Autor autor : gestion.getListaAutores()) {
                        if (!Autor_gestion.validarID(autor)) {
                            System.out.println("Ya hay un autor con ese ID");
                        } else {
                            Autor_gestion.insertarAutores(autor);
                        }
                    }
                    for (Prestamo prestamo : gestion.getListaPrestamo()) {
                        if (!Prestamo_gestion.validarID(prestamo)) {
                            System.out.println("Ya hay un préstamo con ese ID");
                        } else {
                            Prestamo_gestion.InsertarPrestamo(prestamo);
                        }
                    }
                    for (Usuario usuario : gestion.getListaUsuario()) {
                        if (!Usuario_gestion.validarID(usuario)) {
                            System.out.println("Ya hay un usuario con ese ID");
                        } else {
                            Usuario_gestion.InsertarUsuario(usuario);
                        }
                    }
                    break;
                case "3":
                    for (Libro libro : gestion.getListaLibro()) {
                        if(!Libro_gestion.validarLibro(libro)) {
                            System.out.println("El libro tiene los mismos datos");
                        } else {
                            Libro_gestion.actualizarLibros(libro);
                        }
                    }
                    for (Autor autor : gestion.getListaAutores()) {
                        if(!Autor_gestion.validarAutor(autor)) {
                            System.out.println("El autor tiene los mismos datos");
                        } else {
                            Autor_gestion.actualizarAutores(autor);
                        }
                    }
                    for (Prestamo prestamo : gestion.getListaPrestamo()) {
                        if(!Prestamo_gestion.validarPrestamo(prestamo)) {
                            System.out.println("El préstamo tiene los mismos datos");
                        } else {
                            Prestamo_gestion.ActualizarPrestamo(prestamo);
                        }
                    }
                    for (Usuario usuario : gestion.getListaUsuario()) {
                        if(!Usuario_gestion.validarUsuario(usuario)) {
                            System.out.println("El usuario tiene los mismos datos");
                        } else {
                            Usuario_gestion.ActualizarUsuario(usuario);
                        }
                    }
                    break;
                case "4":
                    System.out.println("¿Qué quieres borrar?" + "\n" +
                            "1 - Libros" + "\n" +
                            "2 - Autores" + "\n" +
                            "3 - Préstamos" + "\n" +
                            "4 - Usuarios" + "\n" +
                            "* - Salir");
                    switch (teclado.next()) {
                        case "1":
                            System.out.println("Dime los libros a borrar");
                            int libros_numero = teclado.nextInt();
                            teclado.nextLine();
                            /* Aquí pondremos los equipos a borrar y la ID y borraremos los que queremos */
                            for (int i = 0; i < libros_numero; i++) {
                                System.out.println("Dime el ID a borrar");
                                int ID = teclado.nextInt();
                                if (LibroAutor.validarLibroID(ID))
                                    Libro_gestion.borrarLibros(ID);
                                else {
                                    System.out.println("El ID a borrar tiene un autor" + "\n"
                                            + "\n" + "¿Qué desea hacer?"
                                            + "\n" + "1 - Cambiar el ID del autor"
                                            + "\n" + "2 - Borrarlo igualmente (pondrá el autor del libro en null)"
                                            + "\n" + "* - Salir");
                                    switch (teclado.next()) {
                                        /* Aquí pondremos las opciones que podremos hacer si el programa encuentra a un empleado con el mismo ID al que hemos puesto */
                                        case "1":
                                            teclado.nextLine();
                                            System.out.println("Dime el nuevo ID del autor");
                                            int ID_cambiado = teclado.nextInt();
                                            LibroAutor.LibroID(ID, LibroAutor.sacarAutorID(ID), ID_cambiado);
                                        case "2":
                                            LibroAutor.LibroNull(ID,LibroAutor.sacarAutorID(ID));
                                            Libro_gestion.borrarLibros(ID);
                                            teclado.nextLine();
                                        default:
                                            break;
                                    }
                                }
                            }
                            break;
                        case "2":
                            System.out.println("Dime los autores a borrar");
                            int autores_numero = teclado.nextInt();
                            teclado.nextLine();
                            /* Aquí pondremos los equipos a borrar y la ID y borraremos los que queremos */
                            for (int i = 0; i < autores_numero; i++) {
                                System.out.println("Dime el ID a borrar");
                                int ID = teclado.nextInt();
                                if (LibroAutor.validarAutorID(ID))
                                    Autor_gestion.borrarAutores(ID);
                                else {
                                    System.out.println("El ID a borrar tiene un libro" + "\n"
                                            + "\n" + "¿Qué desea hacer?"
                                            + "\n" + "1 - Cambiar el ID del libro"
                                            + "\n" + "2 - Borrarlo igualmente (pondrá el libro del autor en null)"
                                            + "\n" + "* - Salir");
                                    switch (teclado.next()) {
                                        /* Aquí pondremos las opciones que podremos hacer si el programa encuentra a un empleado con el mismo ID al que hemos puesto */
                                        case "1":
                                            teclado.nextLine();
                                            System.out.println("Dime el nuevo ID del libro");
                                            int ID_cambiado = teclado.nextInt();
                                            LibroAutor.cambiarAutorID(ID, LibroAutor.sacarLibroID(ID), ID_cambiado);
                                        case "2":
                                            LibroAutor.AutoresNull(ID,LibroAutor.sacarLibroID(ID));
                                            Autor_gestion.borrarAutores(ID);
                                            teclado.nextLine();
                                        default:
                                            break;
                                    }
                                }
                            }
                            break;
                        case "3":
                            System.out.println("Dime los préstamos a borrar");
                            int prestamo_numero = teclado.nextInt();
                            teclado.nextLine();
                            /* Aquí pondremos los equipos a borrar y la ID y borraremos los que queremos */
                            for (int i = 0; i < prestamo_numero; i++) {
                                System.out.println("Dime el ID a borrar");
                                int ID = teclado.nextInt();
                                Prestamo_gestion.BorrarPrestamo(ID);
                            }
                            break;
                        case "4":
                            System.out.println("Dime los usuarios a borrar");
                            int usuarios_numero = teclado.nextInt();
                            teclado.nextLine();
                            /* Aquí pondremos los equipos a borrar y la ID y borraremos los que queremos */
                            for (int i = 0; i < usuarios_numero; i++) {
                                System.out.println("Dime el ID a borrar");
                                int ID = teclado.nextInt();
                                if (LibroAutor.validarAutorID(ID))
                                    Usuario_gestion.BorrarUsuario(ID);
                                else {
                                    System.out.println("El ID a borrar tiene un préstamo" + "\n"
                                            + "\n" + "¿Qué desea hacer?"
                                            + "\n" + "1 - Cambiar el ID del préstamo"
                                            + "\n" + "2 - Borrarlo igualmente (pondrá el préstamo del usuario en null)"
                                            + "\n" + "* - Salir");
                                    switch (teclado.next()) {
                                        /* Aquí pondremos las opciones que podremos hacer si el programa encuentra a un empleado con el mismo ID al que hemos puesto */
                                        case "1":
                                            teclado.nextLine();
                                            System.out.println("Dime el nuevo ID del préstamo");
                                            int ID_cambiado = teclado.nextInt();
                                            LibroAutor.cambiarAutorID(ID, LibroAutor.sacarLibroID(ID), ID_cambiado);
                                        case "2":
                                            LibroAutor.AutoresNull(ID,LibroAutor.sacarLibroID(ID));
                                            Autor_gestion.borrarAutores(ID);
                                            teclado.nextLine();
                                        default:
                                            break;
                                    }
                                }
                            }
                    }
                    break;
                default:
                    sigo = false;
            }
        }
    }
}