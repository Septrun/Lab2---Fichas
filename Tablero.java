import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tablero {
    private Ficha[][] tablero;
    private int tamano;

  private static final String[] simbolo = new String[] {
        "+", // Cruz
        "&", // Ampersand
        "$", // Dólar
        "@", // Arroba
        "#", // Almohadilla
        "%", // Porcentaje
        "*", // Asterisco
        "!", // Exclamación
        "=", // Igual
        "~"  // Tilde
};

    public Tablero(int tamano) {
        if (tamano % 2 != 0) {
            throw new IllegalArgumentException("El tamaño del tablero debe ser un número par.");
        }
        this.tamano = tamano;
        this.tablero = new Ficha[tamano][tamano];
        inicializarTablero();
    }

    private void inicializarTablero() {
        List<Ficha> fichas = new ArrayList<>();
        int numPares = (tamano * tamano) / 2;
        if (numPares > simbolo.length) {
            throw new IllegalArgumentException("El tamaño del tablero es demasiado grande.");
        }
        for (int i = 0; i < numPares; i++) {
            fichas.add(new Ficha(simbolo[i]));
            fichas.add(new Ficha(simbolo[i]));
        }
        Collections.shuffle(fichas, new Random());

        int k = 0;
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                this.tablero[i][j] = fichas.get(k++);
            }
        }
    }

    public Ficha getFicha(int fila, int columna) {
        if (fila < 0 || fila >= tamano || columna < 0 || columna >= tamano) {
            throw new IndexOutOfBoundsException("Las coordenadas están fuera de los límites del tablero.");
        }
        return tablero[fila][columna];
    }

    public int getTamano() {
        return tamano;
    }

    public boolean isJuegoTerminado() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (!tablero[i][j].isCombinado()) {
                    return false;
                }
            }
        }
        return true;
    }
}