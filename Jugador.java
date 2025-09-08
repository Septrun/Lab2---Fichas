public class Jugador {
    private String nombre;
    private int puntos;
    private int juegosGanados;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.juegosGanados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public void sumarJuegosGanados() {
        this.juegosGanados++;
    }

    public void sumarPuntos() {
        this.puntos++;
    }
}