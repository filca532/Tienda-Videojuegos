package es.cheste.servicio;

import es.cheste.DAO.MiembroDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.MiembroDAOImpl;
import es.cheste.objetos.Miembro;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Miembro.
 */
public class MiembroServicio {
    private MiembroDAO miembroDAO;

    /**
     * Constructor que inicializa el DAO de Miembro.
     */
    public MiembroServicio() {
        this.miembroDAO = new MiembroDAOImpl();
    }

    /**
     * Inserta un nuevo Miembro en la base de datos.
     *
     * @param miembro El Miembro a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarMiembro(Miembro miembro) throws DAOException {
        miembroDAO.insertarMiembro(miembro);
        System.out.println("Miembro insertado correctamente.");
    }

    /**
     * Obtiene un Miembro por su ID.
     *
     * @param id El ID del miembro.
     * @return El Miembro obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Miembro obtenerPorID(int id) throws DAOException {
        return miembroDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todos los Miembros de la base de datos.
     *
     * @return Una lista de todos los Miembros.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Miembro> obtenerTodos() throws DAOException {
        return miembroDAO.obtenerTodos();
    }

    /**
     * Obtiene los Miembros por tipo de membresía.
     *
     * @param tipoMembresia El tipo de membresía del miembro.
     * @return Una lista de Miembros con el tipo de membresía especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Miembro> obtenerPorTipoMembresia(String tipoMembresia) throws DAOException {
        return miembroDAO.obtenerPorTipoMembresia(tipoMembresia);
    }

    /**
     * Obtiene los Miembros por nivel de membresía.
     *
     * @param nivelMembresia El nivel de membresía del miembro.
     * @return Una lista de Miembros con el nivel de membresía especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Miembro> obtenerPorNivelMembresia(String nivelMembresia) throws DAOException {
        return miembroDAO.obtenerPorNivelMembresia(nivelMembresia);
    }

    /**
     * Actualiza un Miembro en la base de datos.
     *
     * @param miembro El Miembro a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizarMiembro(Miembro miembro) throws DAOException {
        miembroDAO.actualizar(miembro);
    }

    /**
     * Elimina un Miembro de la base de datos.
     *
     * @param id El ID del miembro.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminarMiembro(int id) throws DAOException {
        miembroDAO.eliminar(id);
    }
}