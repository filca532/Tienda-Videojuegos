package es.cheste.objetos;

import java.util.Objects;

public class VideojuegoPlataforma {
    private int idProducto;
    private int idPlataforma;
    private int idNuevoProducto;
    private int idNuevaPlataforma;
    private String nombreProducto;
    private String nombrePlataforma;

    public VideojuegoPlataforma() {
        super();
    }

    public VideojuegoPlataforma(int idProducto, int idPlataforma, String nombreProducto, String nombrePlataforma) {
        this.idProducto = idProducto;
        this.idPlataforma = idPlataforma;
        this.nombreProducto = nombreProducto;
        this.nombrePlataforma = nombrePlataforma;
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

    public int getIdNuevoProducto() {
        return idNuevoProducto;
    }

    public void setIdNuevoProducto(int idNuevoProducto) {
        this.idNuevoProducto = idNuevoProducto;
    }

    public int getIdNuevaPlataforma() {
        return idNuevaPlataforma;
    }

    public void setIdNuevaPlataforma(int idNuevaPlataforma) {
        this.idNuevaPlataforma = idNuevaPlataforma;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombrePlataforma() {
        return nombrePlataforma;
    }

    public void setNombrePlataforma(String nombrePlataforma) {
        this.nombrePlataforma = nombrePlataforma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideojuegoPlataforma that = (VideojuegoPlataforma) o;
        return idProducto == that.idProducto && idPlataforma == that.idPlataforma && idNuevoProducto == that.idNuevoProducto && idNuevaPlataforma == that.idNuevaPlataforma && Objects.equals(nombreProducto, that.nombreProducto) && Objects.equals(nombrePlataforma, that.nombrePlataforma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, idPlataforma, idNuevoProducto, idNuevaPlataforma, nombreProducto, nombrePlataforma);
    }

    @Override
    public String toString() {
        return "VideojuegoPlataforma{" +
                "idProducto=" + idProducto +
                ", idPlataforma=" + idPlataforma +
                ", idNuevoProducto=" + idNuevoProducto +
                ", idNuevaPlataforma=" + idNuevaPlataforma +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", nombrePlataforma='" + nombrePlataforma + '\'' +
                '}';
    }
}
