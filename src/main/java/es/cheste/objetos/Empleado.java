package es.cheste.clases;

import java.util.Objects;

public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String puesto;
    private double salario;
    private String fechaContratacion;
    private double descuentoEmpleado;

    public Empleado() {
        super();
    }

    public Empleado(int idEmpleado, String nombre, String apellidos, String email, String telefono, String puesto, double salario, String fechaContratacion, double descuentoEmpleado) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.descuentoEmpleado = descuentoEmpleado;
    }

    public Empleado(String nombre, String apellidos, String email, String telefono, String puesto, double salario, String fechaContratacion, double descuentoEmpleado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
        this.descuentoEmpleado = descuentoEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public double getDescuentoEmpleado() {
        return descuentoEmpleado;
    }

    public void setDescuentoEmpleado(double descuentoEmpleado) {
        this.descuentoEmpleado = descuentoEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return idEmpleado == empleado.idEmpleado && Double.compare(salario, empleado.salario) == 0 && Double.compare(descuentoEmpleado, empleado.descuentoEmpleado) == 0 && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellidos, empleado.apellidos) && Objects.equals(email, empleado.email) && Objects.equals(telefono, empleado.telefono) && Objects.equals(puesto, empleado.puesto) && Objects.equals(fechaContratacion, empleado.fechaContratacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpleado, nombre, apellidos, email, telefono, puesto, salario, fechaContratacion, descuentoEmpleado);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", puesto='" + puesto + '\'' +
                ", salario=" + salario +
                ", fechaContratacion='" + fechaContratacion + '\'' +
                ", descuentoEmpleado=" + descuentoEmpleado +
                '}';
    }
}
