package dbService;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static io.DataResult.*;

/**
 * DBService class
 *
 * This class provides methods for managing database connections.
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
public class DBService {

    /**
     * Gets an H2 database connection.
     *
     * @return the H2 database connection
     */
    public static Connection getH2Connection() {
        try {
            String url = getUrl();
            String name = getName();
            String pass = getPassword();

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}