package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Factura;

import java.sql.Date;
import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Factura.
 * Proporciona métodos para realizar operaciones CRUD en objetos Factura.
 */
public interface FacturaDAO {

    /**
     * Inserta una nueva Factura en la base de datos.
     *
     * @param factura el objeto Factura a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarFactura(Factura factura) throws DAOException;

    /**
     * Recupera una Factura por su ID.
     *
     * @param id el ID de la Factura a recuperar
     * @return el objeto Factura con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Factura obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todas las Facturas de la base de datos.
     *
     * @return una lista de todas las Facturas
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Factura> obtenerTodas() throws DAOException;

    /**
     * Recupera Facturas por el ID del Cliente.
     *
     * @param clienteId el ID del Cliente cuyas Facturas se desean recuperar
     * @return una lista de Facturas del Cliente especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Factura> obtenerFacturasPorCliente(int clienteId) throws DAOException;

    /**
     * Recupera Facturas dentro de un rango de fechas especificado.
     *
     * @param fechaInicio la fecha de inicio del rango
     * @param fechaFin la fecha de fin del rango
     * @return una lista de Facturas dentro del rango de fechas especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Factura> obtenerFacturasPorFecha(Date fechaInicio, Date fechaFin) throws DAOException;

    /**
     * Recupera Facturas por su método de pago.
     *
     * @param metodoPago el método de pago de las Facturas a recuperar
     * @return una lista de Facturas con el método de pago especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Factura> obtenerFacturasPorMetodoPago(String metodoPago) throws DAOException;

    /**
     * Recupera Facturas dentro de un rango de montos especificado.
     *
     * @param montoMin el monto mínimo del rango
     * @param montoMax el monto máximo del rango
     * @return una lista de Facturas dentro del rango de montos especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Factura> obtenerFacturasPorMonto(double montoMin, double montoMax) throws DAOException;

    /**
     * Actualiza una Factura existente en la base de datos.
     *
     * @param factura el objeto Factura con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Factura factura) throws DAOException;

    /**
     * Elimina una Factura de la base de datos por su ID.
     *
     * @param id el ID de la Factura a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}