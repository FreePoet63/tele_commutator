package dbService;

import dbService.exception.DBException;
import model.Subscriber;

import java.sql.SQLException;
import java.util.List;

/**
 * SubscriberService interface
 *
 * This defines methods for managing subscribers in a data storage system.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public interface SubscriberService {

    /**
     * Retrieves a list of subscribers from the data storage system.
     *
     * @return the list of subscribers retrieved from the data storage
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public List<Subscriber> getSubscribers() throws SQLException;

    /**
     * Adds a new subscriber to the data storage system.
     *
     * @param msisdn the MSISDN (phone number) of the new subscriber
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void addSubscriber(String msisdn) throws SQLException;

    /**
     * Creates a table to store subscribers in the data storage system.
     *
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void createSubscriberTable() throws SQLException;

    /**
     * Cleans up the subscribers by dropping the subscribers table from the data storage system.
     *
     * @throws DBException if a database exception occurs during the clean-up process
     */
    public void cleanUpSubscriber() throws DBException;
}