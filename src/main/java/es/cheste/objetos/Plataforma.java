package es.cheste.objetos;

import java.util.Objects;

public class Plataforma {
    private int idPlataforma;
    private String nombre;

    public Plataforma() {
        super();
    }

    public Plataforma(int idPlataforma, String nombre) {
        this.idPlataforma = idPlataforma;
        this.nombre = nombre;
    }

    public Plataforma(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
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
        Plataforma that = (Plataforma) o;
        return idPlataforma == that.idPlataforma && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlataforma, nombre);
    }

    @Override
    public String toString() {
        return "Plataforma{" +
                "idPlataforma=" + idPlataforma +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
