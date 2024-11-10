package es.cheste.servicio;

import es.cheste.DAO.FacturaLineaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.FacturaLineaDAOImpl;
import es.cheste.objetos.FacturaLinea;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de FacturaLinea.
 */
public class FacturaLineaServicio {
    private FacturaLineaDAO facturaLineaDAO;

    /**
     * Constructor que inicializa el DAO de FacturaLinea.
     */
    public FacturaLineaServicio() {
        this.facturaLineaDAO = new FacturaLineaDAOImpl();
    }

    /**
     * Inserta una nueva FacturaLinea en la base de datos.
     *
     * @param facturaLinea La FacturaLinea a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarFacturaLinea(FacturaLinea facturaLinea) throws DAOException {
        facturaLineaDAO.insertarFacturaLinea(facturaLinea);
    }

    /**
     * Obtiene una FacturaLinea por su ID.
     *
     * @param idFactura El ID de la factura.
     * @param idFacturaLinea El ID de la línea de factura.
     * @return La FacturaLinea obtenida.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public FacturaLinea obtenerPorID(int idFactura, int idFacturaLinea) throws DAOException {
        return facturaLineaDAO.obtenerPorID(idFactura, idFacturaLinea);
    }

    /**
     * Obtiene todas las FacturaLinea de la base de datos.
     *
     * @return Una lista de todas las FacturaLinea.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<FacturaLinea> obtenerTodas() throws DAOException {
        return facturaLineaDAO.obtenerTodas();
    }

    /**
     * Obtiene las FacturaLinea por ID de factura.
     *
     * @param facturaId El ID de la factura.
     * @return Una lista de FacturaLinea con el ID de factura especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<FacturaLinea> obtenerFacturaLineaPorFactura(int facturaId) throws DAOException {
        return facturaLineaDAO.obtenerFacturaLineaPorFactura(facturaId);
    }

    /**
     * Obtiene las FacturaLinea por ID de producto.
     *
     * @param productoId El ID del producto.
     * @return Una lista de FacturaLinea con el ID de producto especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<FacturaLinea> obtenerFacturaLineaPorProducto(int productoId) throws DAOException {
        return facturaLineaDAO.obtenerFacturaLineaPorProducto(productoId);
    }

    /**
     * Obtiene las FacturaLinea por rango de cantidad.
     *
     * @param cantidadMin La cantidad mínima del rango.
     * @param cantidadMax La cantidad máxima del rango.
     * @return Una lista de FacturaLinea dentro del rango de cantidad especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<FacturaLinea> obtenerFacturaLineaPorRangoCantidad(int cantidadMin, int cantidadMax) throws DAOException {
        return facturaLineaDAO.obtenerFacturaLineaPorCantidad(cantidadMin, cantidadMax);
    }

    /**
     * Obtiene las FacturaLinea por rango de precio.
     *
     * @param precioMin El precio mínimo del rango.
     * @param precioMax El precio máximo del rango.
     * @return Una lista de FacturaLinea dentro del rango de precio especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<FacturaLinea> obtenerFacturaLineaPorPrecio(double precioMin, double precioMax) throws DAOException {
        return facturaLineaDAO.obtenerFacturaLineaPorPrecio(precioMin, precioMax);
    }

    /**
     * Actualiza una FacturaLinea en la base de datos.
     *
     * @param facturaLinea La FacturaLinea a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(FacturaLinea facturaLinea) throws DAOException {
        facturaLineaDAO.actualizar(facturaLinea);
    }

    /**
     * Elimina una FacturaLinea de la base de datos.
     *
     * @param idFactura El ID de la factura.
     * @param idFacturaLinea El ID de la línea de factura.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int idFactura, int idFacturaLinea) throws DAOException {
        facturaLineaDAO.eliminar(idFactura, idFacturaLinea);
    }
}