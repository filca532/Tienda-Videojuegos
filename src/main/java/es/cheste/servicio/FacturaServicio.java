package es.cheste.servicio;

import es.cheste.DAO.FacturaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.FacturaDAOImpl;
import es.cheste.objetos.Factura;

import java.sql.Date;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Factura.
 */
public class FacturaServicio {
    private FacturaDAO facturaDAO;

    /**
     * Constructor que inicializa el DAO de Factura.
     */
    public FacturaServicio() {
        this.facturaDAO = new FacturaDAOImpl();
    }

    /**
     * Inserta una nueva Factura en la base de datos.
     *
     * @param factura La Factura a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarFactura(Factura factura) throws DAOException {
        facturaDAO.insertarFactura(factura);
    }

    /**
     * Obtiene una Factura por su ID.
     *
     * @param id El ID de la factura.
     * @return La Factura obtenida.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Factura obtenerPorID(int id) throws DAOException {
        return facturaDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todas las Facturas de la base de datos.
     *
     * @return Una lista de todas las Facturas.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Factura> obtenerTodas() throws DAOException {
        return facturaDAO.obtenerTodas();
    }

    /**
     * Obtiene las Facturas por ID de cliente.
     *
     * @param clienteId El ID del cliente.
     * @return Una lista de Facturas con el ID de cliente especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Factura> obtenerFacturasPorCliente(int clienteId) throws DAOException {
        return facturaDAO.obtenerFacturasPorCliente(clienteId);
    }

    /**
     * Obtiene las Facturas por rango de fecha.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Una lista de Facturas dentro del rango de fecha especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Factura> obtenerFacturasPorRangoFecha(Date fechaInicio, Date fechaFin) throws DAOException {
        return facturaDAO.obtenerFacturasPorFecha(fechaInicio, fechaFin);
    }

    /**
     * Obtiene las Facturas por método de pago.
     *
     * @param metodoPago El método de pago.
     * @return Una lista de Facturas con el método de pago especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Factura> obtenerFacturasPorMetodoPago(String metodoPago) throws DAOException {
        return facturaDAO.obtenerFacturasPorMetodoPago(metodoPago);
    }

    /**
     * Obtiene las Facturas por rango de monto.
     *
     * @param montoMin El monto mínimo del rango.
     * @param montoMax El monto máximo del rango.
     * @return Una lista de Facturas dentro del rango de monto especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Factura> obtenerFacturasPorRangoMonto(double montoMin, double montoMax) throws DAOException {
        return facturaDAO.obtenerFacturasPorMonto(montoMin, montoMax);
    }

    /**
     * Actualiza una Factura en la base de datos.
     *
     * @param factura La Factura a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizarFactura(Factura factura) throws DAOException {
        facturaDAO.actualizar(factura);
    }

    /**
     * Elimina una Factura de la base de datos.
     *
     * @param id El ID de la factura.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminarFactura(int id) throws DAOException {
        facturaDAO.eliminar(id);
    }
}