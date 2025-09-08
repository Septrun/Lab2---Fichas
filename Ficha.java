import java.util.Objects;

public class Ficha {
    private final String simbolo;
    private boolean isRevelado;
    private boolean isCombinado;

    public Ficha(String simbolo) {
        this.simbolo = simbolo;
        this.isRevelado = false;
        this.isCombinado = false;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public boolean isRevelado() {
        return isRevelado;
    }

    public void setRelevado(boolean revelado) {
        isRevelado = revelado;
    }

    public boolean isCombinado() {
        return isCombinado;
    }

    public void setCombinado(boolean combinado) {
        isCombinado = combinado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ficha ficha = (Ficha) o;
        return Objects.equals(simbolo, ficha.simbolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simbolo);
    }
}