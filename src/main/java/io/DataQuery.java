package io;

/**
 * DataQuery class
 *
 * This class provides query strings for managing subscriber and call record tables in a data storage system.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public class DataQuery {

    /**
     * The constant FIND_SUBSCRIBERS.
     * This constant represents the query for finding subscribers.
     */
    public static final String FIND_SUBSCRIBERS = "findSubscriber";

    /**
     * The constant DROP_SUBSCRIBERS_TABLE.
     * This constant represents the query for dropping the subscribers table.
     */
    public static final String DROP_SUBSCRIBERS_TABLE = "dropSubscriberTable";

    /**
     * The constant CREATE_SUBSCRIBERS_TABLE.
     * This constant represents the query for creating the subscribers table.
     */
    public static final String CREATE_SUBSCRIBERS_TABLE = "createSubscriberTable";

    /**
     * The constant DROP_RECORDS_TABLE.
     * This constant represents the query for dropping the records table.
     */
    public static final String DROP_RECORDS_TABLE = "dropRecordTable";

    /**
     * The constant FIND_RECORDS_TABLE.
     * This constant represents the query for finding call records.
     */
    public static final String FIND_RECORDS_TABLE = "findRecord";

    /**
     * The constant CREATE_RECORDS_TABLE.
     * This constant represents the query for creating the call records table.
     */
    public static final String CREATE_RECORDS_TABLE = "createRecordTable";

    /**
     * Find subscribers query string.
     *
     * @return the string representing the query to find subscribers
     */
    public static String findSubscribersQuery() {
        return DataReader.getQuery(FIND_SUBSCRIBERS);
    }

    /**
     * Create subscribers table query string.
     *
     * @return the string representing the query to create the subscribers table
     */
    public static String createSubscribersTableQuery() {
        return DataReader.getQuery(CREATE_SUBSCRIBERS_TABLE);
    }

    /**
     * Drop subscribers table query string.
     *
     * @return the string representing the query to drop the subscribers table
     */
    public static String dropSubscribersTableQuery() {
        return DataReader.getQuery(DROP_SUBSCRIBERS_TABLE);
    }

    /**
     * Find records query string.
     *
     * @return the string representing the query to find call records
     */
    public static String findRecordsQuery() {
        return DataReader.getQuery(FIND_RECORDS_TABLE);
    }

    /**
     * Create records table query string.
     *
     * @return the string representing the query to create the call records table
     */
    public static String createRecordsTableQuery() {
        return DataReader.getQuery(CREATE_RECORDS_TABLE);
    }

    /**
     * Drop records table query string.
     *
     * @return the string representing the query to drop the call records table
     */
    public static String dropRecordsTableQuery() {
        return DataReader.getQuery(DROP_RECORDS_TABLE);
    }
}