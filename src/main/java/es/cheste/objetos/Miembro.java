package es.cheste.objetos;

import java.sql.Date;
import java.util.Objects;

public class Miembro {
    private int idMiembro;
    private int idCliente;
    private String nombreCliente;
    private String tipoMiembro;
    private String nivelMembresia;
    private Date fechaIngreso;

    public Miembro() {
        super();
    }

    public Miembro(int idMiembro, int idCliente, String tipoMiembro, String nivelMembresia, Date fechaIngreso) {
        this.idMiembro = idMiembro;
        this.idCliente = idCliente;
        this.tipoMiembro = tipoMiembro;
        this.nivelMembresia = nivelMembresia;
        this.fechaIngreso = fechaIngreso;
    }

    public Miembro(int idMiembro, int idCliente, String nombreCliente, String tipoMiembro, String nivelMembresia, Date fechaIngreso) {
        this.idMiembro = idMiembro;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.tipoMiembro = tipoMiembro;
        this.nivelMembresia = nivelMembresia;
        this.fechaIngreso = fechaIngreso;
    }

    public Miembro(int idCliente, String tipoMiembro, String nivelMembresia, Date fechaIngreso) {
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miembro miembro = (Miembro) o;
        return idMiembro == miembro.idMiembro && idCliente == miembro.idCliente && Objects.equals(nombreCliente, miembro.nombreCliente) && Objects.equals(tipoMiembro, miembro.tipoMiembro) && Objects.equals(nivelMembresia, miembro.nivelMembresia) && Objects.equals(fechaIngreso, miembro.fechaIngreso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMiembro, idCliente, nombreCliente, tipoMiembro, nivelMembresia, fechaIngreso);
    }

    @Override
    public String toString() {
        return "Miembro{" +
                "idMiembro=" + idMiembro +
                ", idCliente=" + idCliente +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", tipoMiembro='" + tipoMiembro + '\'' +
                ", nivelMembresia='" + nivelMembresia + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}
