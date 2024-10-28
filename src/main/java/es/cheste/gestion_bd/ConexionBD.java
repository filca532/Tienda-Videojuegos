package es.cheste;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String RUTA_PROPIEDAS = "src/main/resources/application.properties";

    Connection connection = null;

    public Connection getConnection() {
        if (connection == null) {
            conectarBD();
        }

        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void conectarBD() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new File(RUTA_PROPIEDAS)));

            String url = properties.getProperty("connectionURL");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
