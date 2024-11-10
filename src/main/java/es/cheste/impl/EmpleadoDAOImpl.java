package es.cheste.impl;

import es.cheste.gestion_bd.ConexionBD;
import es.cheste.DAO.EmpleadoDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Empleado;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz EmpleadoDAO para gestionar las operaciones CRUD de los empleados.
 */
public class EmpleadoDAOImpl implements EmpleadoDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String INSERTAR = "INSERT INTO EMPLEADO (NOMBRE, APELLIDOS, EMAIL, TELEFONO, PUESTO, SALARIO, FECHA_CONTRATACION, DESCUENTO_EMPLEADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM EMPLEADO WHERE ID_EMPLEADO = ?";
    private static final String OBTENER_TODOS = "SELECT * FROM EMPLEADO";
    private static final String OBTENER_POR_PUESTO = "SELECT * FROM EMPLEADO WHERE PUESTO = ?";
    private static final String OBTENER_POR_SALARIO = "SELECT * FROM EMPLEADO WHERE SALARIO BETWEEN ? AND ?";
    private static final String OBTENER_RANGO_FECHA = "SELECT * FROM EMPLEADO WHERE FECHA_CONTRATACION BETWEEN ? AND ?";
    private static final String ACTUALIZAR = "UPDATE EMPLEADO SET NOMBRE = ?, APELLIDOS = ?, EMAIL = ?, TELEFONO = ?, PUESTO = ?, SALARIO = ?, FECHA_CONTRATACION = ?, DESCUENTO_EMPLEADO = ? WHERE ID_EMPLEADO = ?";
    private static final String ELIMINAR = "DELETE FROM EMPLEADO WHERE ID_EMPLEADO = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo empleado en la base de datos.
     *
     * @param empleado El empleado a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarEmpleado(Empleado empleado) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getPuesto());
            ps.setDouble(6, empleado.getSalario());
            ps.setDate(7, empleado.getFechaContratacion());
            ps.setDouble(8, empleado.getDescuentoEmpleado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de empleado fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el empleado.", e);
        }
    }

    /**
     * Obtiene un empleado por su ID.
     *
     * @param id El ID del empleado a obtener.
     * @return El empleado obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Empleado obtenerPorID(int id) throws DAOException {
        Empleado empleado = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empleado = mapearEmpleado(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el empleado por ID.", e);
        }

        return empleado;
    }

    /**
     * Obtiene todos los empleados.
     *
     * @return Una lista de todos los empleados.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Empleado> obtenerTodos() throws DAOException {
        List<Empleado> empleados = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                empleados.add(mapearEmpleado(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los empleados.", e);
        }

        return empleados;
    }

    /**
     * Obtiene los empleados por puesto.
     *
     * @param puesto El puesto de los empleados a obtener.
     * @return Una lista de empleados con el puesto especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Empleado> obtenerEmpleadosPorPuesto(String puesto) throws DAOException {
        List<Empleado> empleados = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_PUESTO)) {
            ps.setString(1, puesto);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(mapearEmpleado(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener empleados por puesto.", e);
        }

        return empleados;
    }

    /**
     * Obtiene los empleados por rango de salario.
     *
     * @param salarioMin El salario mínimo del rango.
     * @param salarioMax El salario máximo del rango.
     * @return Una lista de empleados dentro del rango de salario especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Empleado> obtenerEmpleadosPorSalario(double salarioMin, double salarioMax) throws DAOException {
        List<Empleado> empleados = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_SALARIO)) {
            ps.setDouble(1, salarioMin);
            ps.setDouble(2, salarioMax);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(mapearEmpleado(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener empleados por salario.", e);
        }

        return empleados;
    }

    /**
     * Obtiene los empleados por rango de fecha de contratación.
     *
     * @param fechaInicio La fecha de inicio del rango.
     * @param fechaFin La fecha de fin del rango.
     * @return Una lista de empleados contratados dentro del rango de fechas especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Empleado> obtenerEmpleadosPorFechaContratacion(Date fechaInicio, Date fechaFin) throws DAOException {
        List<Empleado> empleados = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_RANGO_FECHA)) {
            ps.setDate(1, new Date(fechaInicio.getTime()));
            ps.setDate(2, new Date(fechaFin.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    empleados.add(mapearEmpleado(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener empleados por rango de fecha.", e);
        }

        return empleados;
    }

    /**
     * Actualiza un empleado existente en la base de datos.
     *
     * @param empleado El empleado a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Empleado empleado) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getEmail());
            ps.setString(4, empleado.getTelefono());
            ps.setString(5, empleado.getPuesto());
            ps.setDouble(6, empleado.getSalario());
            ps.setDate(7, empleado.getFechaContratacion());
            ps.setDouble(8, empleado.getDescuentoEmpleado());
            ps.setInt(9, empleado.getIdEmpleado());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de empleado fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el empleado.", e);
        }
    }

    /**
     * Elimina un empleado de la base de datos.
     *
     * @param id El ID del empleado a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de empleado fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el empleado.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Empleado.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Empleado mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        int idEmpleado = rs.getInt("ID_EMPLEADO");
        String nombre = rs.getString("NOMBRE");
        String apellidos = rs.getString("APELLIDOS");
        String email = rs.getString("EMAIL");
        String telefono = rs.getString("TELEFONO");
        String puesto = rs.getString("PUESTO");
        double salario = rs.getDouble("SALARIO");
        Date fechaContratacion = rs.getDate("FECHA_CONTRATACION");
        double descuentoEmpleado = rs.getDouble("DESCUENTO_EMPLEADO");

        return new Empleado(idEmpleado, nombre, apellidos, email, telefono, puesto, salario, fechaContratacion, descuentoEmpleado);
    }
}