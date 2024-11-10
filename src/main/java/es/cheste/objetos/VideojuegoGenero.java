package es.cheste.objetos;

import java.util.Objects;

public class VideojuegoGenero {
    private int idProducto;
    private int idGenero;
    private int idNuevoProducto;
    private int idNuevoGenero;
    private String nombreProducto;
    private String nombreGenero;

    public VideojuegoGenero() {
        super();
    }

    public VideojuegoGenero(int idProducto, int idGenero, String nombreProducto, String nombreGenero) {
        this.idProducto = idProducto;
        this.idGenero = idGenero;
        this.nombreProducto = nombreProducto;
        this.nombreGenero = nombreGenero;
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

    public int getIdNuevoProducto() {
        return idNuevoProducto;
    }

    public void setIdNuevoProducto(int idNuevoProducto) {
        this.idNuevoProducto = idNuevoProducto;
    }

    public int getIdNuevoGenero() {
        return idNuevoGenero;
    }

    public void setIdNuevoGenero(int idNuevoGenero) {
        this.idNuevoGenero = idNuevoGenero;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideojuegoGenero that = (VideojuegoGenero) o;
        return idProducto == that.idProducto && idGenero == that.idGenero && idNuevoProducto == that.idNuevoProducto && idNuevoGenero == that.idNuevoGenero && Objects.equals(nombreProducto, that.nombreProducto) && Objects.equals(nombreGenero, that.nombreGenero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idGenero, idNuevoProducto, idNuevoGenero, nombreProducto, nombreGenero);
    }

    @Override
    public String toString() {
        return "VideojuegoGenero{" +
                "idProducto=" + idProducto +
                ", idGenero=" + idGenero +
                ", idNuevoProducto=" + idNuevoProducto +
                ", idNuevoGenero=" + idNuevoGenero +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", nombreGenero='" + nombreGenero + '\'' +
                '}';
    }
}
