package es.cheste.excepciones;

/**
 * Excepción personalizada para errores de acceso a datos (DAO).
 */
public class DAOException extends Exception {

    /**
     * Constructor para DAOException.
     *
     * @param mensaje el mensaje de error
     * @param causa la causa del error
     */
    public DAOException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}