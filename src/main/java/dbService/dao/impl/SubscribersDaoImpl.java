package dbService.dao.impl;

import dbService.dao.SubscribersDao;
import dbService.executor.Executor;
import model.Subscriber;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.DataQuery.*;

/**
 * SubscribersDaoImpl class
 *
 * This class provides implementation for interacting with the subscribers database.
 * It includes methods for finding subscribers, inserting new subscribers, creating and dropping subscriber tables.
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
public class SubscribersDaoImpl implements SubscribersDao {
    private Executor executor;

    /**
     * Instantiates a new Subscribers dao.
     *
     * @param connection the connection
     */
    public SubscribersDaoImpl(Connection connection) {
        this.executor = new Executor(connection);
    }

    /**
     * Retrieve a list of subscribers from the database.
     *
     * @return the list of subscribers retrieved from the database
     * @throws SQLException if an SQL exception occurs while accessing the database
     */
    @Override
    public List<Subscriber> findSubscribers() throws SQLException {
        List<Subscriber> subscribers = new ArrayList<>();
        ResultSet result = executor.execQuery(findSubscribersQuery());

        while (result.next()) {
            Subscriber subscriber = new Subscriber(result.getString(1));
            subscribers.add(subscriber);
        }
        result.close();
        return subscribers;
    }

    /**
     * Insert a new subscriber into the database.
     *
     * @param msisdn the MSDN (phone number) of the new subscriber
     * @throws SQLException if an SQL exception occurs while accessing the database
     */
    @Override
    public void insertSubscriber(String msisdn) throws SQLException {
        executor.execUpdate("insert into subscribers (msisdn) values ('" + msisdn + "')");
    }

    /**
     * Create the subscribers table in the database.
     *
     * @throws SQLException if an SQL exception occurs while accessing the database
     */
    @Override
    public void createSubscribersTable() throws SQLException {
        executor.execUpdate(createSubscribersTableQuery());
    }

    /**
     * Drop the subscribers table from the database.
     *
     * @throws SQLException if an SQL exception occurs while accessing the database
     */
    @Override
    public void dropSubscribersTable() throws SQLException {
        executor.execUpdate(dropSubscribersTableQuery());
    }
}