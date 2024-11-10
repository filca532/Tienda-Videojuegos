package es.cheste.impl;

import es.cheste.DAO.MiembroDAO;
import es.cheste.gestion_bd.ConexionBD;
import es.cheste.excepciones.DAOException;
import es.cheste.objetos.Miembro;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz MiembroDAO para gestionar las operaciones CRUD de Miembro.
 */
public class MiembroDAOImpl implements MiembroDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();

    // Consultas SQL
    private static final String INSERTAR = "INSERT INTO MIEMBRO (ID_CLIENTE, TIPO_MIEMBRO, NIVEL_MEMBRESIA, FECHA_INGRESO) VALUES (?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT MIEMBRO.ID_MIEMBRO, MIEMBRO.ID_CLIENTE, CLIENTE.NOMBRE AS NOMBRE_CLIENTE, MIEMBRO.TIPO_MIEMBRO, MIEMBRO.NIVEL_MEMBRESIA, MIEMBRO.FECHA_INGRESO FROM MIEMBRO JOIN CLIENTE ON MIEMBRO.ID_CLIENTE = CLIENTE.ID_CLIENTE WHERE MIEMBRO.ID_MIEMBRO = ?";
    private static final String OBTENER_TODOS = "SELECT MIEMBRO.ID_MIEMBRO, MIEMBRO.ID_CLIENTE, CLIENTE.NOMBRE AS NOMBRE_CLIENTE, MIEMBRO.TIPO_MIEMBRO, MIEMBRO.NIVEL_MEMBRESIA, MIEMBRO.FECHA_INGRESO FROM MIEMBRO JOIN CLIENTE ON MIEMBRO.ID_CLIENTE = CLIENTE.ID_CLIENTE";
    private static final String OBTENER_POR_TIPO_MEMBRESIA = "SELECT MIEMBRO.ID_MIEMBRO, MIEMBRO.ID_CLIENTE, CLIENTE.NOMBRE AS NOMBRE_CLIENTE, MIEMBRO.TIPO_MIEMBRO, MIEMBRO.NIVEL_MEMBRESIA, MIEMBRO.FECHA_INGRESO FROM MIEMBRO JOIN CLIENTE ON MIEMBRO.ID_CLIENTE = CLIENTE.ID_CLIENTE WHERE MIEMBRO.TIPO_MIEMBRO = ?";
    private static final String OBTENER_POR_NIVEL_MEMBRESIA = "SELECT MIEMBRO.ID_MIEMBRO, MIEMBRO.ID_CLIENTE, CLIENTE.NOMBRE AS NOMBRE_CLIENTE, MIEMBRO.TIPO_MIEMBRO, MIEMBRO.NIVEL_MEMBRESIA, MIEMBRO.FECHA_INGRESO FROM MIEMBRO JOIN CLIENTE ON MIEMBRO.ID_CLIENTE = CLIENTE.ID_CLIENTE WHERE MIEMBRO.NIVEL_MEMBRESIA = ?";
    private static final String ACTUALIZAR = "UPDATE MIEMBRO SET ID_CLIENTE = ?, TIPO_MIEMBRO = ?, NIVEL_MEMBRESIA = ?, FECHA_INGRESO = ? WHERE ID_MIEMBRO = ?";
    private static final String ELIMINAR = "DELETE FROM MIEMBRO WHERE ID_MIEMBRO = ?";

    ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta un nuevo Miembro en la base de datos.
     *
     * @param miembro El Miembro a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarMiembro(Miembro miembro) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, miembro.getIdCliente());
            ps.setString(2, miembro.getTipoMiembro());
            ps.setString(3, miembro.getNivelMembresia());
            ps.setDate(4, miembro.getFechaIngreso());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Inserción de miembro fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al insertar el miembro.", e);
        }
    }

    /**
     * Obtiene un Miembro por su ID.
     *
     * @param id El ID del miembro.
     * @return El Miembro obtenido.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Miembro obtenerPorID(int id) throws DAOException {
        Miembro miembro = null;

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    miembro = mapearMiembro(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener el miembro por ID.", e);
        }

        return miembro;
    }

    /**
     * Obtiene todos los Miembro de la base de datos.
     *
     * @return Una lista de todos los Miembro.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Miembro> obtenerTodos() throws DAOException {
        List<Miembro> miembros = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                miembros.add(mapearMiembro(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener todos los miembros.", e);
        }

        return miembros;
    }

    /**
     * Obtiene los Miembro por tipo de membresía.
     *
     * @param tipoMembresia El tipo de membresía.
     * @return Una lista de Miembro del tipo de membresía especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Miembro> obtenerPorTipoMembresia(String tipoMembresia) throws DAOException {
        List<Miembro> miembros = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_TIPO_MEMBRESIA)) {
            ps.setString(1, tipoMembresia);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    miembros.add(mapearMiembro(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener miembros por tipo de membresía.", e);
        }

        return miembros;
    }

    /**
     * Obtiene los Miembro por nivel de membresía.
     *
     * @param nivelMembresia El nivel de membresía.
     * @return Una lista de Miembro del nivel de membresía especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    public List<Miembro> obtenerPorNivelMembresia(String nivelMembresia) throws DAOException {
        List<Miembro> miembros = new ArrayList<>();

        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_NIVEL_MEMBRESIA)) {
            ps.setString(1, nivelMembresia);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    miembros.add(mapearMiembro(rs));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al obtener miembros por nivel de membresía.", e);
        }

        return miembros;
    }

    /**
     * Actualiza un Miembro en la base de datos.
     *
     * @param miembro El Miembro a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Miembro miembro) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setInt(1, miembro.getIdCliente());
            ps.setString(2, miembro.getTipoMiembro());
            ps.setString(3, miembro.getNivelMembresia());
            ps.setDate(4, miembro.getFechaIngreso());
            ps.setInt(5, miembro.getIdMiembro());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Actualización de miembro fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el miembro.", e);
        }
    }

    /**
     * Elimina un Miembro de la base de datos.
     *
     * @param id El ID del miembro.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Eliminación de miembro fallida, no se afectaron filas.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el miembro.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Miembro.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Miembro mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Miembro mapearMiembro(ResultSet rs) throws SQLException {
        int idMiembro = rs.getInt("ID_MIEMBRO");
        int idCliente = rs.getInt("ID_CLIENTE");
        String nombreCliente = rs.getString("NOMBRE_CLIENTE");
        String tipoMiembro = rs.getString("TIPO_MIEMBRO");
        String nivelMembresia = rs.getString("NIVEL_MEMBRESIA");
        Date fechaIngreso = rs.getDate("FECHA_INGRESO");

        return new Miembro(idMiembro, idCliente, nombreCliente, tipoMiembro, nivelMembresia, fechaIngreso);
    }
}