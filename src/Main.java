import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BibliotecaService gestion = new BibliotecaService();
        Scanner teclado = new Scanner(System.in);
        LibroDAO Libro_gestion = new LibroDAO();
        AutorDAO Autor_gestion = new AutorDAO();
        UsuarioDAO Usuario_gestion = new  UsuarioDAO();
        PrestamoDAO Prestamo_gestion = new PrestamoDAO();
        boolean sigo = true;
        while (sigo) {
            gestion.Memoria();
            System.out.println("MENÚ DE INTERACCIÓN" + "\n" +
                    "1 - Leer todo" + "\n" +
                    "2 - Insertar equipos y jugadores" + "\n" +
                    "3 - Actualizar equipos y jugadores" + "\n" +
                    "4 - Borrar equipos o jugadores" + "\n" +
                    "5 - Ordenar equipos por ID o Nombre" + "\n" +
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
                    break;
                case "3":
                    for (Libro libro : gestion.getListaLibro()) {
                        if(!Libro_gestion.validarLibro(libro)) {
                            System.out.println("El equipo tiene los mismos datos");
                        } else {
                            Libro_gestion.actualizarLibros(libro);
                        }
                    }
                    for (Autor autor : gestion.getListaAutores()) {
                        if(!Autor_gestion.validarAutor(autor)) {
                            System.out.println("El jugador tiene los mismos datos");
                        } else {
                            Autor_gestion.actualizarAutores(autor);
                        }
                    }
                    break;
                case "4":
                    System.out.println("¿Qué quieres borrar?" + "\n" +
                            "1 - Equipos" + "\n" +
                            "2 - Jugadores" + "\n" +
                            "* - Salir");
                    switch (teclado.next()) {
                        case "1":
                            System.out.println("Dime los equipos a borrar");
                            int empleados_numero = teclado.nextInt();
                            teclado.nextLine();
                            /* Aquí pondremos los equipos a borrar y la ID y borraremos los que queremos */
                            for (int i = 0; i < empleados_numero; i++) {
                                System.out.println("Dime el ID a borrar");
                                int ID = teclado.nextInt();
                                if (Jugador_gestion.validarJugadorID(ID))
                                    Libro_gestion.borrarLibros(ID);
                                else {
                                    System.out.println("El ID a borrar tiene un jugador" + "\n"
                                            + "\n" + "¿Qué desea hacer?"
                                            + "\n" + "1 - Cambiar el ID del jugador"
                                            + "\n" + "2 - Borrarlo igualmente (pondrá los jugadores del equipo en null)"
                                            + "\n" + "* - Salir");
                                    switch (teclado.next()) {
                                        /* Aquí pondremos las opciones que podremos hacer si el programa encuentra a un empleado con el mismo ID al que hemos puesto */
                                        case "1":
                                            teclado.nextLine();
                                            System.out.println("Dime el nombre del jugador");
                                            String nombre = teclado.nextLine();
                                            System.out.println("Dime el nuevo ID del equipo");
                                            int ID_cambiado = teclado.nextInt();
                                            Jugador_gestion.cambiarJugadorID(nombre, ID_cambiado);
                                        case "2":
                                            Jugador_gestion.jugadoresNull(ID);
                                            Libro_gestion.borrarLibros(ID);
                                            teclado.nextLine();
                                        default:
                                    }
                                }
                            }
                            break;
                        case "2":
                            System.out.println("Dime los jugadores a borrar");
                            int jugadores_numero = teclado.nextInt();
                            teclado.nextLine();
                            /* Aquí pondremos los jugadores a borrar y la ID y borraremos los que queremos */
                            for (int i = 0; i < jugadores_numero; i++) {
                                System.out.println("Dime el ID a borrar");
                                int ID = teclado.nextInt();
                                Autor_gestion.borrarAutores(ID);
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    sigo = false;
            }
        }
    }
}