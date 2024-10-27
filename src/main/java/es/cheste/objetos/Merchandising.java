package es.cheste.clases;

import java.util.Objects;

public class Merchandising {
    private int idProducto;
    private String categoria;
    private String material;
    private String dimensiones;
    private double peso;
    private int proveedor;

    public Merchandising() {
        super();
    }

    public Merchandising(int idProducto, String categoria, String material, String dimensiones, double peso, int proveedor) {
        this.idProducto = idProducto;
        this.categoria = categoria;
        this.material = material;
        this.dimensiones = dimensiones;
        this.peso = peso;
        this.proveedor = proveedor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
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
        Merchandising that = (Merchandising) o;
        return idProducto == that.idProducto && Double.compare(peso, that.peso) == 0 && proveedor == that.proveedor && Objects.equals(categoria, that.categoria) && Objects.equals(material, that.material) && Objects.equals(dimensiones, that.dimensiones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto, categoria, material, dimensiones, peso, proveedor);
    }

    @Override
    public String toString() {
        return "Merchandising{" +
                "idProducto=" + idProducto +
                ", categoria='" + categoria + '\'' +
                ", material='" + material + '\'' +
                ", dimensiones='" + dimensiones + '\'' +
                ", peso=" + peso +
                ", proveedor=" + proveedor +
                '}';
    }
}
