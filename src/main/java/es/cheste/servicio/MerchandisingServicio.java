package es.cheste.servicio;

import es.cheste.DAO.MerchandisingDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.MerchandisingDAOImpl;
import es.cheste.objetos.Merchandising;

import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Merchandising.
 */
public class MerchandisingServicio {
    private MerchandisingDAO merchandisingDAO;

    /**
     * Constructor que inicializa el DAO de Merchandising.
     */
    public MerchandisingServicio() {
        this.merchandisingDAO = new MerchandisingDAOImpl();
    }

    /**
     * Inserta un nuevo Merchandising en la base de datos.
     *
     * @param merchandising El Merchandising a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarMerchandising(Merchandising merchandising) throws DAOException {
        merchandisingDAO.insertarMerchandising(merchandising);
    }

    /**
     * Obtiene un Merchandising por su ID.
     *
     * @param id El ID del merchandising.
     * @return El Merchandising obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Merchandising obtenerPorID(int id) throws DAOException {
        return merchandisingDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todos los Merchandising de la base de datos.
     *
     * @return Una lista de todos los Merchandising.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Merchandising> obtenerTodos() throws DAOException {
        return merchandisingDAO.obtenerTodos();
    }

    /**
     * Obtiene los Merchandising por categoría.
     *
     * @param categoria La categoría del merchandising.
     * @return Una lista de Merchandising con la categoría especificada.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Merchandising> obtenerPorCategoria(String categoria) throws DAOException {
        return merchandisingDAO.obtenerPorCategoria(categoria);
    }

    /**
     * Actualiza un Merchandising en la base de datos.
     *
     * @param merchandising El Merchandising a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizar(Merchandising merchandising) throws DAOException {
        merchandisingDAO.actualizar(merchandising);
    }

    /**
     * Elimina un Merchandising de la base de datos.
     *
     * @param id El ID del merchandising.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminar(int id) throws DAOException {
        merchandisingDAO.eliminar(id);
    }
}