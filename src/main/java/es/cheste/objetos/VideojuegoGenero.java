package es.cheste.clases;

import java.util.Objects;

public class VideojuegoGenero {
    private int idProducto;
    private int idGenero;

    public VideojuegoGenero() {
        super();
    }

    public VideojuegoGenero(int idProducto, int idGenero) {
        this.idProducto = idProducto;
        this.idGenero = idGenero;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideojuegoGenero that = (VideojuegoGenero) o;
        return idProducto == that.idProducto && idGenero == that.idGenero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idGenero);
    }

    @Override
    public String toString() {
        return "VideojuegoGenero{" +
                "idProducto=" + idProducto +
                ", idGenero=" + idGenero +
                '}';
    }
}
