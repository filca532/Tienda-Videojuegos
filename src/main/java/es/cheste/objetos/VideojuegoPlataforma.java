package es.cheste.clases;

import java.util.Objects;

public class VideojuegoPlataforma {
    private int idProducto;
    private int idPlataforma;

    public VideojuegoPlataforma() {
        super();
    }

    public VideojuegoPlataforma(int idProducto, int idPlataforma) {
        this.idProducto = idProducto;
        this.idPlataforma = idPlataforma;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideojuegoPlataforma that = (VideojuegoPlataforma) o;
        return idProducto == that.idProducto && idPlataforma == that.idPlataforma;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idPlataforma);
    }

    @Override
    public String toString() {
        return "VideojuegoPlataforma{" +
                "idProducto=" + idProducto +
                ", idPlataforma=" + idPlataforma +
                '}';
    }
}
