package es.cheste.clases;

import java.util.Objects;

public class Videojuego {
    private int idProducto;
    private String fechaLanzamiento;
    private String clasificacionEdad;
    private int idProveedor;

    public Videojuego() {
        super();
    }

    public Videojuego(int idProducto, String fechaLanzamiento, String clasificacionEdad, int idProveedor) {
        this.idProducto = idProducto;
        this.fechaLanzamiento = fechaLanzamiento;
        this.clasificacionEdad = clasificacionEdad;
        this.idProveedor = idProveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Videojuego that = (Videojuego) o;
        return idProducto == that.idProducto && idProveedor == that.idProveedor && Objects.equals(fechaLanzamiento, that.fechaLanzamiento) && Objects.equals(clasificacionEdad, that.clasificacionEdad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, fechaLanzamiento, clasificacionEdad, idProveedor);
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "idProducto=" + idProducto +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", clasificacionEdad='" + clasificacionEdad + '\'' +
                ", idProveedor=" + idProveedor +
                '}';
    }
}
