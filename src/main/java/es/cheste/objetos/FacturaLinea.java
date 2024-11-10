package es.cheste.objetos;

import java.util.Objects;

public class FacturaLinea {
    private int idFactura;
    private int idFacturaLinea;
    private int idProducto;
    private String tipoProducto;
    private int cantidad;
    private double precioUnitario;

    public FacturaLinea() {
        super();
    }

    public FacturaLinea(int idFactura, int idFacturaLinea, int idProducto, String tipoProducto, int cantidad, double precioUnitario) {
        this.idFactura = idFactura;
        this.idFacturaLinea = idFacturaLinea;
        this.idProducto = idProducto;
        this.tipoProducto = tipoProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdFacturaLinea() {
        return idFacturaLinea;
    }

    public void setIdFacturaLinea(int idFacturaLinea) {
        this.idFacturaLinea = idFacturaLinea;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaLinea that = (FacturaLinea) o;
        return idFactura == that.idFactura && idFacturaLinea == that.idFacturaLinea && idProducto == that.idProducto && cantidad == that.cantidad && Double.compare(precioUnitario, that.precioUnitario) == 0 && Objects.equals(tipoProducto, that.tipoProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFactura, idFacturaLinea, idProducto, tipoProducto, cantidad, precioUnitario);
    }

    @Override
    public String toString() {
        return "FacturaLinea{" +
                "idFactura=" + idFactura +
                ", idFacturaLinea=" + idFacturaLinea +
                ", idProducto=" + idProducto +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}

