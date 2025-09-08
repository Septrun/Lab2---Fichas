public class JuegoControlador {
    private final Jugador jugador1;
    private final Jugador jugador2;
    private Tablero tablero;

    public JuegoControlador() {
        this.jugador1 = new Jugador("Jugador 1");
        this.jugador2 = new Jugador("Jugador 2");
    }

    public void iniciarNuevaPartida(int tamano) {
        this.tablero = new Tablero(tamano);
        jugador1.setPuntos(0);
        jugador2.setPuntos(0);
    }
    
    public String getTurnoActual(int turno) {
        Jugador jugadorActual = (turno == 1) ? jugador1 : jugador2;
        return jugadorActual.getNombre();
    }

    public String getEstadoJugadores() {
        return "Puntos de " + jugador1.getNombre() + ": " + jugador1.getPuntos() + "\nPuntos de " + jugador2.getNombre() + ": " + jugador2.getPuntos();
    }
    
    public String getTableroMostrado() {
        if (tablero == null) return "Tablero no inicializado.";
        
        int tamano = tablero.getTamano();
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < tamano; i++) {
            sb.append(String.format("%2d", i));
        }
        sb.append("\n");
        for (int i = 0; i < tamano; i++) {
            sb.append(String.format("%2d", i));
            for (int j = 0; j < tamano; j++) {
                Ficha ficha = tablero.getFicha(i, j);
                if (ficha.isCombinado()) {
                    sb.append("  ");
                } else if (ficha.isRevelado()) {
                    sb.append(" ").append(ficha.getSimbolo());
                } else {
                    sb.append(" ■");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void seleccionarFicha(int fila, int columna, int prevFila, int prevCol) {
        if (fila == prevFila && columna == prevCol) {
            throw new IllegalArgumentException("No puedes seleccionar la misma ficha dos veces.");
        }
        validarCoordenadas(fila, columna);
        tablero.getFicha(fila, columna).setRelevado(true);
    }

    public boolean comprobarPar(int fila1, int col1, int fila2, int col2, int turno) {
        Ficha Ficha1 = tablero.getFicha(fila1, col1);
        Ficha Ficha2 = tablero.getFicha(fila2, col2);

        boolean coinciden = Ficha1.getSimbolo().equals(Ficha2.getSimbolo());

        if (coinciden) {
            Jugador jugadorActual = (turno == 1) ? jugador1 : jugador2;
            jugadorActual.sumarPuntos();
            Ficha1.setCombinado(true);
            Ficha2.setCombinado(true);
        }

        Ficha1.setRelevado(false);
        Ficha2.setRelevado(false);

        return coinciden;
    }

    private void validarCoordenadas(int fila, int columna) {
        if (fila < 0 || fila >= tablero.getTamano() || columna < 0 || columna >= tablero.getTamano()) {
            throw new IllegalArgumentException("Las coordenadas están fuera de los límites del tablero.");
        }
        if (tablero.getFicha(fila, columna).isCombinado()) {
            throw new IllegalArgumentException("Esa ficha ya ha sido emparejada. Elige otra.");
        }
    }

    public boolean isJuegoTerminado() {
        return tablero.isJuegoTerminado();
    }

    public String getResultadoPartida() {
        if (jugador1.getPuntos() > jugador2.getPuntos()) {
            jugador1.sumarJuegosGanados();
            return "¡" + jugador1.getNombre() + " ha ganado la partida!";
        } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
            jugador2.sumarJuegosGanados();
            return "¡" + jugador2.getNombre() + " ha ganado la partida!";
        } else {
            return "¡La partida ha terminado en empate!";
        }
    }

    public String getResultadosFinales() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resultados finales:\n");
        sb.append(jugador1.getNombre() + " ha ganado " + jugador1.getJuegosGanados() + " juegos.\n");
        sb.append(jugador2.getNombre() + " ha ganado " + jugador2.getJuegosGanados() + " juegos.\n");

        if (jugador1.getJuegosGanados() > jugador2.getJuegosGanados()) {
            sb.append("¡" + jugador1.getNombre() + " es el ganador!");
        } else if (jugador2.getJuegosGanados() > jugador1.getJuegosGanados()) {
            sb.append("¡" + jugador2.getNombre() + " es el ganador!");
        } else {
            sb.append("¡La partida ha terminado en empate!");
        }
        return sb.toString();
    }
}