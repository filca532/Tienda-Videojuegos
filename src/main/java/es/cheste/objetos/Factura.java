package es.cheste.clases;

public class Factura {
    private int idFactura;
    private int idCliente;
    private int idEmpleado;
    private String fechaPedido;
    private double totalPedido;
    private String metodoPago;

    public Factura() {
        super();
    }

    public Factura(int idFactura, int idCliente, int idEmpleado, String fechaPedido, double totalPedido, String metodoPago) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fechaPedido = fechaPedido;
        this.totalPedido = totalPedido;
        this.metodoPago = metodoPago;
    }

    public Factura(int idCliente, int idEmpleado, String fechaPedido, double totalPedido, String metodoPago) {
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fechaPedido = fechaPedido;
        this.totalPedido = totalPedido;
        this.metodoPago = metodoPago;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", idCliente=" + idCliente +
                ", idEmpleado=" + idEmpleado +
                ", fechaPedido='" + fechaPedido + '\'' +
                ", totalPedido=" + totalPedido +
                ", metodoPago='" + metodoPago + '\'' +
                '}';
    }
}
