package es.cheste.impl;

import es.cheste.DAO.ConsolaDAO;
import es.cheste.excepciones.DAOException;
import es.cheste.gestion_bd.ConexionBD;
import es.cheste.objetos.Consola;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz ConsolaDAO para gestionar las operaciones CRUD de las consolas.
 */
public class ConsolaDAOImpl implements ConsolaDAO {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String INSERTAR = "INSERT INTO CONSOLA (ID_PRODUCTO, TIPO_PRODUCTO, MARCA, MODELO, CAPACIDAD_ALMACENAMIENTO, COLOR, NUM_MANDOS_INCLUIDOS, ID_PROVEEDOR) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String OBTENER_POR_ID = "SELECT * FROM CONSOLA WHERE ID_PRODUCTO = ?";
    private static final String OBTENER_TODAS = "SELECT * FROM CONSOLA";
    private static final String OBTENER_POR_MARCA = "SELECT * FROM CONSOLA WHERE MARCA = ?";
    private static final String OBTENER_POR_PROVEEDOR = "SELECT * FROM CONSOLA WHERE ID_PROVEEDOR = ?";
    private static final String ACTUALIZAR = "UPDATE CONSOLA SET MARCA = ?, MODELO = ?, CAPACIDAD_ALMACENAMIENTO = ?, COLOR = ?, NUM_MANDOS_INCLUIDOS = ?, ID_PROVEEDOR = ? WHERE ID_PRODUCTO = ?";
    private static final String ELIMINAR = "DELETE FROM CONSOLA WHERE ID_PRODUCTO = ?";

    private final ConexionBD conexionBD = new ConexionBD();

    /**
     * Inserta una nueva consola en la base de datos.
     *
     * @param consola La consola a insertar.
     * @throws DAOException Si ocurre un error durante la inserción.
     */
    @Override
    public void insertarConsola(Consola consola) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(INSERTAR)) {
            ps.setInt(1, consola.getIdProducto());
            ps.setString(2, "consola");
            ps.setString(3, consola.getMarca());
            ps.setString(4, consola.getModelo());
            ps.setString(5, consola.getAlmacenamiento());
            ps.setString(6, consola.getColor());
            ps.setInt(7, consola.getMandosIncluidos());
            ps.setInt(8, consola.getProveedor());

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Inserción fallida.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al insertar.", e);
        }
    }

    /**
     * Obtiene una consola por su ID.
     *
     * @param id El ID de la consola a obtener.
     * @return La consola obtenida.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public Consola obtenerPorID(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearConsola(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener por ID.", e);
        }
        return null;
    }

    /**
     * Obtiene todas las consolas.
     *
     * @return Una lista de todas las consolas.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Consola> obtenerTodas() throws DAOException {
        List<Consola> lista = new ArrayList<>();
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_TODAS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearConsola(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener todas.", e);
        }
        return lista;
    }

    /**
     * Obtiene las consolas por marca.
     *
     * @param marca La marca de las consolas a obtener.
     * @return Una lista de consolas de la marca especificada.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Consola> obtenerConsolasPorMarca(String marca) throws DAOException {
        List<Consola> lista = new ArrayList<>();
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_MARCA)) {
            ps.setString(1, marca);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearConsola(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener por marca.", e);
        }
        return lista;
    }

    /**
     * Obtiene las consolas por proveedor.
     *
     * @param idProveedor El ID del proveedor de las consolas a obtener.
     * @return Una lista de consolas del proveedor especificado.
     * @throws DAOException Si ocurre un error durante la obtención.
     */
    @Override
    public List<Consola> obtenerPorProveedor(int idProveedor) throws DAOException {
        List<Consola> lista = new ArrayList<>();
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(OBTENER_POR_PROVEEDOR)) {
            ps.setInt(1, idProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearConsola(rs));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener por marca.", e);
        }
        return lista;
    }

    /**
     * Actualiza una consola existente en la base de datos.
     *
     * @param consola La consola a actualizar.
     * @throws DAOException Si ocurre un error durante la actualización.
     */
    @Override
    public void actualizar(Consola consola) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ACTUALIZAR)) {
            ps.setString(1, consola.getMarca());
            ps.setString(2, consola.getModelo());
            ps.setString(3, consola.getAlmacenamiento());
            ps.setString(4, consola.getColor());
            ps.setInt(5, consola.getMandosIncluidos());
            ps.setInt(6, consola.getProveedor());
            ps.setInt(7, consola.getIdProducto());

            if (ps.executeUpdate() == 0) {
                throw new DAOException("Actualización fallida.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al actualizar.", e);
        }
    }

    /**
     * Elimina una consola de la base de datos.
     *
     * @param id El ID de la consola a eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación.
     */
    @Override
    public void eliminar(int id) throws DAOException {
        try (PreparedStatement ps = conexionBD.getConnection().prepareStatement(ELIMINAR)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new DAOException("Eliminación fallida.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar.", e);
        }
    }

    /**
     * Mapea un ResultSet a un objeto Consola.
     *
     * @param rs El ResultSet a mapear.
     * @return El objeto Consola mapeado.
     * @throws SQLException Si ocurre un error durante el mapeo.
     */
    private Consola mapearConsola(ResultSet rs) throws SQLException {
        int idProducto = rs.getInt("ID_PRODUCTO");
        String tipoProducto = rs.getString("TIPO_PRODUCTO");
        String marca = rs.getString("MARCA");
        String modelo = rs.getString("MODELO");
        String capacidadAlmacenamiento = rs.getString("CAPACIDAD_ALMACENAMIENTO");
        String color = rs.getString("COLOR");
        int numMandosIncluidos = rs.getInt("NUM_MANDOS_INCLUIDOS");
        int idProveedor = rs.getInt("ID_PROVEEDOR");

        return new Consola(idProducto, tipoProducto, marca, modelo, capacidadAlmacenamiento, color, numMandosIncluidos, idProveedor);
    }
}