package es.cheste.servicio;

import es.cheste.DAO.ProveedorDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.ProveedorDAOImpl;
import es.cheste.objetos.Proveedor;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Proveedor.
 */
public class ProveedorServicio {
    private ProveedorDAO proveedorDAO;

    /**
     * Constructor que inicializa el DAO de Proveedor.
     */
    public ProveedorServicio() {
        this.proveedorDAO = new ProveedorDAOImpl();
    }

    /**
     * Inserta un nuevo Proveedor en la base de datos.
     *
     * @param proveedor El Proveedor a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarProveedor(Proveedor proveedor) throws DAOException {
        proveedorDAO.insertarProveedor(proveedor);
    }

    /**
     * Obtiene un Proveedor por su ID.
     *
     * @param id El ID del proveedor.
     * @return El Proveedor obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Proveedor obtenerPorID(int id) throws DAOException {
        return proveedorDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todos los Proveedores de la base de datos.
     *
     * @return Una lista de todos los Proveedores.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Proveedor> obtenerTodos() throws DAOException {
        return proveedorDAO.obtenerTodos();
    }

    /**
     * Actualiza un Proveedor en la base de datos.
     *
     * @param proveedor El Proveedor a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(Proveedor proveedor) throws DAOException {
        proveedorDAO.actualizar(proveedor);
    }

    /**
     * Elimina un Proveedor de la base de datos.
     *
     * @param id El ID del proveedor.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int id) throws DAOException {
        proveedorDAO.eliminar(id);
    }
}