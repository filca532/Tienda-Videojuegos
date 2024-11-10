package es.cheste.objetos;

import java.sql.Date;
import java.util.Objects;

public class Videojuego {
    private int idProducto;
    private Date fechaLanzamiento;
    private String clasificacionEdad;
    private int proveedor;

    public Videojuego() {
        super();
    }

    public Videojuego(int idProducto, Date fechaLanzamiento, String clasificacionEdad, int proveedor) {
        this.idProducto = idProducto;
        this.fechaLanzamiento = fechaLanzamiento;
        this.clasificacionEdad = clasificacionEdad;
        this.proveedor = proveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setIdProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Videojuego that = (Videojuego) o;
        return idProducto == that.idProducto && proveedor == that.proveedor && Objects.equals(fechaLanzamiento, that.fechaLanzamiento) && Objects.equals(clasificacionEdad, that.clasificacionEdad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, fechaLanzamiento, clasificacionEdad, proveedor);
    }

    @Override
    public String toString() {
        return "Videojuego{" +
                "idProducto=" + idProducto +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", clasificacionEdad='" + clasificacionEdad + '\'' +
                ", proveedor=" + proveedor +
                '}';
    }
}
