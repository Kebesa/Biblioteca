import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        LibroDAO Libro_gestion = new LibroDAO();
        AutorDAO Autor_gestion = new AutorDAO();
        UsuarioDAO Usuario_gestion = new  UsuarioDAO();
        PrestamoDAO Prestamo_gestion = new PrestamoDAO();
        boolean sigo = true;
        while (sigo) {
            Equipo_gestion.memoriaEquipos(equipos);
            Jugador_gestion.memoriaJugadores(jugadores);
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
                    for (Equipo equipo : equipos) {
                        if (!Equipo_gestion.validarID(equipo)) {
                            System.out.println("Ya hay un equipo con ese ID");
                        } else {
                            Equipo_gestion.insertarEquipo(equipo);
                        }
                    }
                    for (Jugador jugador : jugadores) {
                        if (!Jugador_gestion.validarID(jugador)) {
                            System.out.println("Ya hay un jugador con ese ID");
                        } else {
                            Jugador_gestion.insertarJugador(jugador);
                        }
                    }
                    break;
                case "3":
                    for (Equipo equipo : equipos) {
                        if(!Equipo_gestion.validarEquipo(equipo)) {
                            System.out.println("El equipo tiene los mismos datos");
                        } else {
                            Equipo_gestion.actualizarEquipos(equipo);
                        }
                    }
                    for (Jugador jugador : jugadores) {
                        if(!Jugador_gestion.validarJugador(jugador)) {
                            System.out.println("El jugador tiene los mismos datos");
                        } else {
                            Jugador_gestion.actualizarJugadores(jugador);
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
                                    Equipo_gestion.borrarEquipos(ID);
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
                                            Equipo_gestion.borrarEquipos(ID);
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
                                Jugador_gestion.borrarJugadores(ID);
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case "5":
                    System.out.println("¿Qué quieres borrar?" + "\n" +
                            "1 - Equipos" + "\n" +
                            "2 - Jugadores" + "\n" +
                            "* - Salir");
                    teclado.nextLine();
                    switch (teclado.next()) {
                        case "1":
                            System.out.println("1 - Ordenar por ID" + "\n" +
                                    "2 - Ordenar por Nombre" + "\n" +
                                    "* - Salir");
                            /* Aquí pondremos cómo queremos que nos ordene los equipos y nos lo muestre por pantalla */
                            switch (teclado.next()) {
                                case "1":
                                    Equipo_gestion.ordenarEquipoID();
                                    break;
                                case "2":
                                    Equipo_gestion.ordenarEquipoNombre();
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "2":
                            System.out.println("1 - Ordenar por ID" + "\n" +
                                    "2 - Ordenar por Nombre" + "\n" +
                                    "* - Salir");
                            /* Aquí pondremos cómo queremos que nos ordene los jugadores y nos lo muestre por pantalla */
                            switch (teclado.next()) {
                                case "1":
                                    Jugador_gestion.ordenarJugadorID();
                                    break;
                                case "2":
                                    Jugador_gestion.ordenarJugadorNombre();
                                    break;
                                default:
                                    break;
                            }
                        default:
                    }
                    break;
                default:
                    sigo = false;
            }
        }
    }
}