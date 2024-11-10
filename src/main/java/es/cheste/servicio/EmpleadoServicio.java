package es.cheste.servicio;

import es.cheste.DAO.EmpleadoDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.impl.EmpleadoDAOImpl;
import es.cheste.objetos.Empleado;

import java.sql.Date;
import java.util.List;

/**
 * Servicio para gestionar las operaciones CRUD de Empleado.
 */
public class EmpleadoServicio {
    private EmpleadoDAO empleadoDAO;

    /**
     * Constructor que inicializa el DAO de Empleado.
     */
    public EmpleadoServicio() {
        this.empleadoDAO = new EmpleadoDAOImpl();
    }

    /**
     * Inserta un nuevo Empleado en la base de datos.
     *
     * @param empleado El Empleado a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    public void insertarEmpleado(Empleado empleado) throws DAOException {
        empleadoDAO.insertarEmpleado(empleado);
        System.out.println("Empleado insertado correctamente.");
    }

    /**
     * Obtiene un Empleado por su ID.
     *
     * @param id El ID del empleado.
     * @return El Empleado obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public Empleado obtenerPorID(int id) throws DAOException {
        return empleadoDAO.obtenerPorID(id);
    }

    /**
     * Obtiene todos los Empleados de la base de datos.
     *
     * @return Una lista de todos los Empleados.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Empleado> obtenerTodos() throws DAOException {
        return empleadoDAO.obtenerTodos();
    }

    /**
     * Obtiene los Empleados por puesto.
     *
     * @param puesto El puesto del empleado.
     * @return Una lista de Empleados con el puesto especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Empleado> obtenerEmpleadosPorPuesto(String puesto) throws DAOException {
        return empleadoDAO.obtenerEmpleadosPorPuesto(puesto);
    }

    /**
     * Obtiene los Empleados por rango de salario.
     *
     * @param salarioMin El salario mínimo del rango.
     * @param salarioMax El salario máximo del rango.
     * @return Una lista de Empleados dentro del rango de salario especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Empleado> obtenerEmpleadosRangoSalario(double salarioMin, double salarioMax) throws DAOException {
        return empleadoDAO.obtenerEmpleadosPorSalario(salarioMin, salarioMax);
    }

    /**
     * Obtiene los Empleados por rango de fecha de contratación.
     *
     * @param fechaInicial La fecha inicial del rango.
     * @param fechaFinal La fecha final del rango.
     * @return Una lista de Empleados dentro del rango de fecha especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Empleado> obtenerEmpleadosPorFechaContratacion(Date fechaInicial, Date fechaFinal) throws DAOException {
        return empleadoDAO.obtenerEmpleadosPorFechaContratacion(fechaInicial, fechaFinal);
    }

    /**
     * Actualiza un Empleado en la base de datos.
     *
     * @param empleado El Empleado a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    public void actualizarEmpleado(Empleado empleado) throws DAOException {
        empleadoDAO.actualizar(empleado);
    }

    /**
     * Elimina un Empleado de la base de datos.
     *
     * @param id El ID del empleado.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    public void eliminarEmpleado(int id) throws DAOException {
        empleadoDAO.eliminar(id);
    }
}