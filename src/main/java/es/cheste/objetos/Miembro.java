package es.cheste.clases;

import java.util.Objects;

public class Miembro {
    private int idMiembro;
    private int idCliente;
    private String tipoMiembro;
    private String nivelMembresia;
    private String fechaIngreso;

    public Miembro() {
        super();
    }

    public Miembro(int idMiembro, int idCliente, String tipoMiembro, String nivelMembresia, String fechaIngreso) {
        this.idMiembro = idMiembro;
        this.idCliente = idCliente;
        this.tipoMiembro = tipoMiembro;
        this.nivelMembresia = nivelMembresia;
        this.fechaIngreso = fechaIngreso;
    }

    public Miembro(int idCliente, String tipoMiembro, String nivelMembresia, String fechaIngreso) {
        this.idCliente = idCliente;
        this.tipoMiembro = tipoMiembro;
        this.nivelMembresia = nivelMembresia;
        this.fechaIngreso = fechaIngreso;
    }

    public int getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(int idMiembro) {
        this.idMiembro = idMiembro;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoMiembro() {
        return tipoMiembro;
    }

    public void setTipoMiembro(String tipoMiembro) {
        this.tipoMiembro = tipoMiembro;
    }

    public String getNivelMembresia() {
        return nivelMembresia;
    }

    public void setNivelMembresia(String nivelMembresia) {
        this.nivelMembresia = nivelMembresia;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miembro miembro = (Miembro) o;
        return idMiembro == miembro.idMiembro && idCliente == miembro.idCliente && Objects.equals(tipoMiembro, miembro.tipoMiembro) && Objects.equals(nivelMembresia, miembro.nivelMembresia) && Objects.equals(fechaIngreso, miembro.fechaIngreso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMiembro, idCliente, tipoMiembro, nivelMembresia, fechaIngreso);
    }

    @Override
    public String toString() {
        return "Miembro{" +
                "idMiembro=" + idMiembro +
                ", idCliente=" + idCliente +
                ", tipoMiembro='" + tipoMiembro + '\'' +
                ", nivelMembresia='" + nivelMembresia + '\'' +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                '}';
    }
}
