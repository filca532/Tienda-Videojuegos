package es.cheste.DAO;

import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Empleado;

import java.sql.Date;
import java.util.List;

/**
 * Interfaz para el Data Access Object (DAO) de Empleado.
 * Proporciona métodos para realizar operaciones CRUD en objetos Empleado.
 */
public interface EmpleadoDAO {

    /**
     * Inserta un nuevo Empleado en la base de datos.
     *
     * @param empleado el objeto Empleado a insertar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void insertarEmpleado(Empleado empleado) throws DAOException;

    /**
     * Recupera un Empleado por su ID.
     *
     * @param id el ID del Empleado a recuperar
     * @return el objeto Empleado con el ID especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    Empleado obtenerPorID(int id) throws DAOException;

    /**
     * Recupera todos los objetos Empleado de la base de datos.
     *
     * @return una lista de todos los objetos Empleado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Empleado> obtenerTodos() throws DAOException;

    /**
     * Recupera Empleados por su puesto.
     *
     * @param puesto el puesto de los Empleados a recuperar
     * @return una lista de Empleados con el puesto especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Empleado> obtenerEmpleadosPorPuesto(String puesto) throws DAOException;

    /**
     * Recupera Empleados por su rango salarial.
     *
     * @param salarioMin el salario mínimo del rango
     * @param salarioMax el salario máximo del rango
     * @return una lista de Empleados dentro del rango salarial especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Empleado> obtenerEmpleadosPorSalario(double salarioMin, double salarioMax) throws DAOException;

    /**
     * Recupera Empleados por su fecha de contratación.
     *
     * @param fechaInicio la fecha de inicio del rango
     * @param fechaFin la fecha de fin del rango
     * @return una lista de Empleados contratados dentro del rango de fechas especificado
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    List<Empleado> obtenerEmpleadosPorFechaContratacion(Date fechaInicio, Date fechaFin) throws DAOException;

    /**
     * Actualiza un Empleado existente en la base de datos.
     *
     * @param empleado el objeto Empleado con la información actualizada
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void actualizar(Empleado empleado) throws DAOException;

    /**
     * Elimina un Empleado de la base de datos por su ID.
     *
     * @param id el ID del Empleado a eliminar
     * @throws DAOException si ocurre un error de acceso a la base de datos
     */
    void eliminar(int id) throws DAOException;
}