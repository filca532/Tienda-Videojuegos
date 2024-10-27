package es.cheste.clases;

import java.util.Objects;

public class Consola {
    private int idProducto;
    private String marca;
    private String modelo;
    private int almacenamiento;
    private String color;
    private int mandosIncluidos;
    private int proveedor;

    public Consola() {
        super();
    }

    public Consola(int idProducto, String marca, String modelo, int almacenamiento, String color, int mandosIncluidos, int proveedor) {
        this.idProducto = idProducto;
        this.marca = marca;
        this.modelo = modelo;
        this.almacenamiento = almacenamiento;
        this.color = color;
        this.mandosIncluidos = mandosIncluidos;
        this.proveedor = proveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMandosIncluidos() {
        return mandosIncluidos;
    }

    public void setMandosIncluidos(int mandosIncluidos) {
        this.mandosIncluidos = mandosIncluidos;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consola consola = (Consola) o;
        return idProducto == consola.idProducto && almacenamiento == consola.almacenamiento && mandosIncluidos == consola.mandosIncluidos && proveedor == consola.proveedor && Objects.equals(marca, consola.marca) && Objects.equals(modelo, consola.modelo) && Objects.equals(color, consola.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, marca, modelo, almacenamiento, color, mandosIncluidos, proveedor);
    }

    @Override
    public String toString() {
        return "Consola{" +
                "idProducto=" + idProducto +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", almacenamiento=" + almacenamiento +
                ", color='" + color + '\'' +
                ", mandosIncluidos=" + mandosIncluidos +
                ", proveedor=" + proveedor +
                '}';
    }
}
