package es.cheste.clases;

import java.util.Objects;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String pais;

    public Proveedor() {
        super();
    }

    public Proveedor(int idProveedor, String nombre, String direccion, String telefono, String email, String pais) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.pais = pais;
    }

    public Proveedor(String nombre, String direccion, String telefono, String email, String pais) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.pais = pais;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return idProveedor == proveedor.idProveedor && Objects.equals(nombre, proveedor.nombre) && Objects.equals(direccion, proveedor.direccion) && Objects.equals(telefono, proveedor.telefono) && Objects.equals(email, proveedor.email) && Objects.equals(pais, proveedor.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor, nombre, direccion, telefono, email, pais);
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
