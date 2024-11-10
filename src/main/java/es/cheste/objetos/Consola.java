package es.cheste.objetos;

import java.util.Objects;

public class Consola {
    private int idProducto;
    private String tipoProducto;
    private String marca;
    private String modelo;
    private String almacenamiento;
    private String color;
    private int mandosIncluidos;
    private int proveedor;

    public Consola() {
        super();
    }

    public Consola(int idProducto, String marca, String modelo, String almacenamiento, String color, int mandosIncluidos, int proveedor) {
        this.idProducto = idProducto;
        this.tipoProducto = tipoProducto;
        this.marca = marca;
        this.modelo = modelo;
        this.almacenamiento = almacenamiento;
        this.color = color;
        this.mandosIncluidos = mandosIncluidos;
        this.proveedor = proveedor;
    }

    public Consola(int idProducto, String tipoProducto, String marca, String modelo, String almacenamiento, String color, int mandosIncluidos, int proveedor) {
        this.idProducto = idProducto;
        this.tipoProducto = tipoProducto;
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

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
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

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
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
        return idProducto == consola.idProducto && almacenamiento == consola.almacenamiento && mandosIncluidos == consola.mandosIncluidos && proveedor == consola.proveedor && Objects.equals(tipoProducto, consola.tipoProducto) && Objects.equals(marca, consola.marca) && Objects.equals(modelo, consola.modelo) && Objects.equals(color, consola.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, tipoProducto, marca, modelo, almacenamiento, color, mandosIncluidos, proveedor);
    }

    @Override
    public String toString() {
        return "Consola{" +
                "idProducto=" + idProducto +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", almacenamiento=" + almacenamiento +
                ", color='" + color + '\'' +
                ", mandosIncluidos=" + mandosIncluidos +
                ", proveedor=" + proveedor +
                '}';
    }
}
