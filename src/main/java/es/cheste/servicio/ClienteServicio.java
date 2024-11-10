package es.cheste.servicio;

import es.cheste.DAO.ClienteDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.ClienteDAOImpl;
import es.cheste.objetos.Cliente;
import java.sql.Date;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Cliente.
 */
public class ClienteServicio {
    private ClienteDAO clienteDAO;

    /**
     * Constructor que inicializa el DAO de Cliente.
     */
    public ClienteServicio() {
        this.clienteDAO = new ClienteDAOImpl();
    }

    /**
     * Inserta un nuevo Cliente en la base de datos.
     *
     * @param cliente El Cliente a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarCliente(Cliente cliente) throws DAOException {
        clienteDAO.insertarCliente(cliente);
        System.out.println("Cliente insertado correctamente.");
    }

    /**
     * Obtiene un Cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return El Cliente obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Cliente obtenerPorID(int id) throws DAOException {
        return clienteDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todos los Clientes de la base de datos.
     *
     * @return Una lista de todos los Clientes.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Cliente> obtenerTodos() throws DAOException {
        return clienteDAO.obtenerTodos();
    }

    /**
     * Obtiene los Clientes por rango de fecha.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Una lista de Clientes dentro del rango de fecha especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Cliente> obtenerClientesRangoFecha(Date fechaInicio, Date fechaFin) throws DAOException {
        return clienteDAO.obtenerClientesRangoFecha(fechaInicio, fechaFin);
    }

    /**
     * Actualiza un Cliente en la base de datos.
     *
     * @param cliente El Cliente a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizarCliente(Cliente cliente) throws DAOException {
        clienteDAO.actualizar(cliente);
    }

    /**
     * Elimina un Cliente de la base de datos.
     *
     * @param id El ID del cliente.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminarCliente(int id) throws DAOException {
        clienteDAO.eliminar(id);
    }
}