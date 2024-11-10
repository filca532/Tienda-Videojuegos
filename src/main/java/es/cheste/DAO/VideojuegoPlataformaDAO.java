package es.cheste.DAO;

import es.cheste.excepciones.DAOException;

import java.util.List;

public interface VideojuegoPlataforma {
    void insertarVideojuegoPlataforma(VideojuegoPlataforma videojuegoPlataforma) throws DAOException;
    VideojuegoPlataforma obtenerPorID(int id) throws DAOException;
    List<VideojuegoPlataforma> obtenerTodos() throws DAOException;
    List<VideojuegoPlataforma> obtenerPorVideojuegoNombre(String nombreJuego) throws DAOException;
    List<VideojuegoPlataforma> obtenerPorPlataformaNombre(String nombrePlataforma) throws DAOException;
    void actualizar(VideojuegoPlataforma videojuegoPlataforma) throws DAOException;
    void eliminar(int id) throws DAOException;
}
