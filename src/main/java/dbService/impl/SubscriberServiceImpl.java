package dbService.impl;

import dbService.SubscriberService;
import dbService.dao.impl.SubscribersDaoImpl;
import dbService.exception.DBException;
import model.Subscriber;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static dbService.DBService.getH2Connection;

/**
 * SubscriberServiceImpl class
 *
 * This class provides implementation for managing subscribers.
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
public class SubscriberServiceImpl implements SubscriberService {
    private final Connection connection;

    /**
     * Instantiates a new Subscriber service.
     * It sets up the connection to the database.
     */
    public SubscriberServiceImpl() {
        this.connection = getH2Connection();
    }

    /**
     * Retrieves a list of subscribers from the data storage system.
     *
     * @return the list of subscribers retrieved from the data storage
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    @Override
    public List<Subscriber> getSubscribers() throws SQLException {
        return new SubscribersDaoImpl(connection).findSubscribers();
    }

    /**
     * Adds a new subscriber to the data storage system.
     *
     * @param msisdn the MSISDN (phone number) of the new subscriber
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    @Override
    public void addSubscriber(String msisdn) throws SQLException {
        new SubscribersDaoImpl(connection).insertSubscriber(msisdn);
    }

    /**
     * Creates a table to store subscribers in the data storage system.
     *
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    @Override
    public void createSubscriberTable() throws SQLException {
        new SubscribersDaoImpl(connection).createSubscribersTable();
    }

    /**
     * Cleans up the subscribers by dropping the subscribers table from the data storage system.
     *
     * @throws DBException if a database exception occurs during the clean-up process
     */
    @Override
    public void cleanUpSubscriber() throws DBException {
        SubscribersDaoImpl dao = new SubscribersDaoImpl(connection);
        try {
            dao.dropSubscribersTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}