package es.cheste.servicio;

import es.cheste.DAO.ConsolaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.ConsolaDAOImpl;
import es.cheste.objetos.Consola;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Consola.
 */
public class ConsolaServicio {
    private ConsolaDAO consolaDAO;

    /**
     * Constructor que inicializa el DAO de Consola.
     */
    public ConsolaServicio() {
        this.consolaDAO = new ConsolaDAOImpl();
    }

    /**
     * Inserta una nueva Consola en la base de datos.
     *
     * @param consola La Consola a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarConsola(Consola consola) throws DAOException {
        consolaDAO.insertarConsola(consola);
    }

    /**
     * Obtiene una Consola por su ID.
     *
     * @param id El ID de la consola.
     * @return La Consola obtenida.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Consola obtenerPorID(int id) throws DAOException {
        return consolaDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todas las Consolas de la base de datos.
     *
     * @return Una lista de todas las Consolas.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Consola> obtenerTodas() throws DAOException {
        return consolaDAO.obtenerTodas();
    }

    /**
     * Obtiene las Consolas por marca.
     *
     * @param marca La marca de la consola.
     * @return Una lista de Consolas con la marca especificada.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Consola> obtenerPorMarca(String marca) throws DAOException {
        return consolaDAO.obtenerConsolasPorMarca(marca);
    }

    /**
     * Obtiene las Consolas por proveedor.
     *
     * @param idProveedor El ID del proveedor.
     * @return Una lista de Consolas del proveedor especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Consola> obtenerPorProveedor(int idProveedor) throws DAOException {
        return consolaDAO.obtenerPorProveedor(idProveedor);
    }

    /**
     * Actualiza una Consola en la base de datos.
     *
     * @param consola La Consola a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(Consola consola) throws DAOException {
        consolaDAO.actualizar(consola);
    }

    /**
     * Elimina una Consola de la base de datos.
     *
     * @param id El ID de la consola.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int id) throws DAOException {
        consolaDAO.eliminar(id);
    }
}