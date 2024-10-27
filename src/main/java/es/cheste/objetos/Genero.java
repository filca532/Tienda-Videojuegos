package es.cheste.clases;

import java.util.Objects;

public class Genero {
    private int idGenero;
    private String nombre;

    public Genero() {
        super();
    }

    public Genero(int idGenero, String nombre) {
        this.idGenero = idGenero;
        this.nombre = nombre;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genero genero = (Genero) o;
        return idGenero == genero.idGenero && Objects.equals(nombre, genero.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenero, nombre);
    }

    @Override
    public String toString() {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
