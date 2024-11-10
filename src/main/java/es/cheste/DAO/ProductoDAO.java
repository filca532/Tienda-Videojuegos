package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Producto;

import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Producto.
 * Proporciona métodos para realizar operaciones CRUD en objetos Producto.
 */
public interface ProductoDAO {

    /**
     * Inserta un nuevo Producto en la base de datos.
     *
     * @param producto el objeto Producto a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarProducto(Producto producto) throws DAOException;

    /**
     * Recupera un Producto por su ID.
     *
     * @param id el ID del Producto a recuperar
     * @return el objeto Producto con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Producto obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todos los objetos Producto de la base de datos.
     *
     * @return una lista de todos los objetos Producto
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Producto> obtenerTodos() throws DAOException;

    /**
     * Recupera Productos dentro de un rango de stock especificado.
     *
     * @param stockInicial el stock mínimo del rango
     * @param stockFinal el stock máximo del rango
     * @return una lista de Productos dentro del rango de stock especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Producto> obtenerProductosPorRangoStock(int stockInicial, int stockFinal) throws DAOException;

    /**
     * Recupera Productos dentro de un rango de precios especificado.
     *
     * @param precioMin el precio mínimo del rango
     * @param precioMax el precio máximo del rango
     * @return una lista de Productos dentro del rango de precios especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Producto> obtenerProductosPorRangoPrecio(double precioMin, double precioMax) throws DAOException;

    /**
     * Recupera Productos por su tipo.
     *
     * @param tipoProducto el tipo de Producto a recuperar
     * @return una lista de Productos del tipo especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Producto> obtenerProductosPorTipo(String tipoProducto) throws DAOException;

    /**
     * Actualiza un Producto existente en la base de datos.
     *
     * @param producto el objeto Producto con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Producto producto) throws DAOException;

    /**
     * Elimina un Producto de la base de datos por su ID.
     *
     * @param id el ID del Producto a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}