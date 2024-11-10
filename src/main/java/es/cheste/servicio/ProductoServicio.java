package es.cheste.servicio;

import es.cheste.DAO.ProductoDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.ProductoDAOImpl;
import es.cheste.objetos.Producto;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Producto.
 */
public class ProductoServicio {
    private ProductoDAO productoDAO;

    /**
     * Constructor que inicializa el DAO de Producto.
     */
    public ProductoServicio() {
        this.productoDAO = new ProductoDAOImpl();
    }

    /**
     * Inserta un nuevo Producto en la base de datos.
     *
     * @param producto El Producto a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarProducto(Producto producto) throws DAOException {
        productoDAO.insertarProducto(producto);
    }

    /**
     * Obtiene un Producto por su ID.
     *
     * @param id El ID del producto.
     * @return El Producto obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Producto obtenerPorID(int id) throws DAOException {
        return productoDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todos los Productos de la base de datos.
     *
     * @return Una lista de todos los Productos.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Producto> obtenerTodos() throws DAOException {
        return productoDAO.obtenerTodos();
    }

    /**
     * Obtiene los Productos por rango de stock.
     *
     * @param stockInicial El stock inicial del rango.
     * @param stockFinal El stock final del rango.
     * @return Una lista de Productos dentro del rango de stock especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Producto> obtenerProductosPorRangoStock(int stockInicial, int stockFinal) throws DAOException {
        return productoDAO.obtenerProductosPorRangoStock(stockInicial, stockFinal);
    }

    /**
     * Obtiene los Productos por rango de precio.
     *
     * @param precioMin El precio mínimo del rango.
     * @param precioMax El precio máximo del rango.
     * @return Una lista de Productos dentro del rango de precio especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Producto> obtenerProductosPorRangoPrecio(double precioMin, double precioMax) throws DAOException {
        return productoDAO.obtenerProductosPorRangoPrecio(precioMin, precioMax);
    }

    /**
     * Obtiene los Productos por tipo.
     *
     * @param tipoProducto El tipo de producto.
     * @return Una lista de Productos con el tipo especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Producto> obtenerProductosPorTipo(String tipoProducto) throws DAOException {
        return productoDAO.obtenerProductosPorTipo(tipoProducto);
    }

    /**
     * Actualiza un Producto en la base de datos.
     *
     * @param producto El Producto a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(Producto producto) throws DAOException {
        productoDAO.actualizar(producto);
    }

    /**
     * Elimina un Producto de la base de datos.
     *
     * @param id El ID del producto.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int id) throws DAOException {
        productoDAO.eliminar(id);
    }
}