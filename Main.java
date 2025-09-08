import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JuegoControlador controlador = new JuegoControlador();

        System.out.println("¡Bienvenido a las Memory Cards!");

        boolean continuarJugando = true;
        do {
            try {
                int tamano = 0;
                while (true) {
                    System.out.print("Introduce el tamaño del tablero (2 o 4): ");
                    tamano = scanner.nextInt();
                    if (tamano % 2 != 0 || tamano <= 0) {
                        System.out.println("El tamaño del tablero debe ser un número par y positivo.");
                    } else {
                        break;
                    }
                }
                controlador.iniciarNuevaPartida(tamano);
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduce un número.");
                scanner.next();
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            int turno = 1;
            while (!controlador.isJuegoTerminado()) {
                limpiarConsola();
                System.out.println(controlador.getTableroMostrado());
                System.out.println("Es el turno de " + controlador.getTurnoActual(turno));
                System.out.println(controlador.getEstadoJugadores());

                boolean turnoValido = false;
                while (!turnoValido) {
                    try {
                        System.out.print("Introduce las coordenadas de la primera ficha (fila columna): ");
                        int fila1 = scanner.nextInt();
                        int col1 = scanner.nextInt();

                        controlador.seleccionarFicha(fila1, col1, -1, -1);
                        limpiarConsola();
                        System.out.println(controlador.getTableroMostrado());

                        System.out.print("Introduce las coordenadas de la segunda ficha (fila columna): ");
                        int fila2 = scanner.nextInt();
                        int col2 = scanner.nextInt();

                        controlador.seleccionarFicha(fila2, col2, fila1, col1);

                        limpiarConsola();
                        System.out.println(controlador.getTableroMostrado());

                        if (controlador.comprobarPar(fila1, col1, fila2, col2, turno)) {
                            System.out.println("\n¡Se formó una pareja!");
                        } else {
                            System.out.println("\nNo no formó ninguna pareja. Las fichas se ocultarán de nuevo.");
                            turno = (turno == 1) ? 2 : 1;
                        }

                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        turnoValido = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, introduce dos números.");
                        scanner.next();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            limpiarConsola();
            System.out.println(controlador.getTableroMostrado());
            System.out.println("Se acabó la partida. Puntuación final:");
            System.out.println(controlador.getEstadoJugadores());
            System.out.println(controlador.getResultadoPartida());

            System.out.println("\n¿Quieres jugar otra partida? (s/n)");
            String respuesta = scanner.next();
            continuarJugando = respuesta.equalsIgnoreCase("s");

        } while (continuarJugando);

        System.out.println(controlador.getResultadosFinales());
        System.out.println("¡Gracias por jugar a las Memory Cards!");
        scanner.close();
    }

    private static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
        }
    }
}