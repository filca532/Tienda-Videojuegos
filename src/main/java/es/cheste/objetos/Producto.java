package es.cheste.clases;

import java.util.Objects;

public class Producto {
    private int idProducto;
    private String tipoProducto;
    private String nombre;
    private double precio;
    private int stock;

    public Producto() {
        super();
    }

    public Producto(int idProducto, String tipoProducto, String nombre, double precio, int stock) {
        this.idProducto = idProducto;
        this.tipoProducto = tipoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(String nombre, double precio, int stock, String tipoProducto) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.tipoProducto = tipoProducto;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return idProducto == producto.idProducto && Double.compare(precio, producto.precio) == 0 && stock == producto.stock && Objects.equals(tipoProducto, producto.tipoProducto) && Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, tipoProducto, nombre, precio, stock);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
