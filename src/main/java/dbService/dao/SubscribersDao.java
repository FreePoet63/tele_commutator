package dbService.dao;

import model.Subscriber;

import java.sql.SQLException;
import java.util.List;

/**
 * SubscribersDao interface
 *
 * This interface defines methods for managing subscribers in a data storage system.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public interface SubscribersDao {

    /**
     * Retrieves a list of subscribers from the data storage system.
     *
     * @return the list of subscribers retrieved from the data storage
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public List<Subscriber> findSubscribers() throws SQLException;

    /**
     * Inserts a new subscriber into the data storage system.
     *
     * @param msisdn the MSDN (phone number) of the new subscriber
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void insertSubscriber(String msisdn) throws SQLException;

    /**
     * Creates a table to store subscribers in the data storage system.
     *
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void createSubscribersTable() throws SQLException;

    /**
     * Drops the table storing subscribers from the data storage system.
     *
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void dropSubscribersTable() throws SQLException;
}