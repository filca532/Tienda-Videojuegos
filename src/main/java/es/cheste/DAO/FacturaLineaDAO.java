package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.FacturaLinea;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de FacturaLinea.
 * Proporciona métodos para realizar operaciones CRUD en objetos FacturaLinea.
 */
public interface FacturaLineaDAO {

    /**
     * Inserta una nueva FacturaLinea en la base de datos.
     *
     * @param facturaLinea el objeto FacturaLinea a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarFacturaLinea(FacturaLinea facturaLinea) throws DAOException;

    /**
     * Recupera una FacturaLinea por su ID.
     *
     * @param idFactura el ID de la Factura
     * @param idFacturaLinea el ID de la FacturaLinea
     * @return el objeto FacturaLinea con los IDs especificados
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    FacturaLinea obtenerPorID(int idFactura, int idFacturaLinea) throws DAOException;

    /**
     * Recupera todas las FacturaLineas de la base de datos.
     *
     * @return una lista de todas las FacturaLineas
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<FacturaLinea> obtenerTodas() throws DAOException;

    /**
     * Recupera FacturaLineas por el ID de la Factura.
     *
     * @param facturaId el ID de la Factura cuyas FacturaLineas se desean recuperar
     * @return una lista de FacturaLineas de la Factura especificada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<FacturaLinea> obtenerFacturaLineaPorFactura(int facturaId) throws DAOException;

    /**
     * Recupera FacturaLineas por el ID del Producto.
     *
     * @param productoId el ID del Producto cuyas FacturaLineas se desean recuperar
     * @return una lista de FacturaLineas del Producto especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<FacturaLinea> obtenerFacturaLineaPorProducto(int productoId) throws DAOException;

    /**
     * Recupera FacturaLineas dentro de un rango de cantidades especificado.
     *
     * @param cantidadMin la cantidad mínima del rango
     * @param cantidadMax la cantidad máxima del rango
     * @return una lista de FacturaLineas dentro del rango de cantidades especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<FacturaLinea> obtenerFacturaLineaPorCantidad(int cantidadMin, int cantidadMax) throws DAOException;

    /**
     * Recupera FacturaLineas dentro de un rango de precios especificado.
     *
     * @param precioMin el precio mínimo del rango
     * @param precioMax el precio máximo del rango
     * @return una lista de FacturaLineas dentro del rango de precios especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<FacturaLinea> obtenerFacturaLineaPorPrecio(double precioMin, double precioMax) throws DAOException;

    /**
     * Actualiza una FacturaLinea existente en la base de datos.
     *
     * @param facturaLinea el objeto FacturaLinea con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(FacturaLinea facturaLinea) throws DAOException;

    /**
     * Elimina una FacturaLinea de la base de datos por su ID.
     *
     * @param idFactura el ID de la Factura
     * @param idFacturaLinea el ID de la FacturaLinea a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int idFactura, int idFacturaLinea) throws DAOException;
}