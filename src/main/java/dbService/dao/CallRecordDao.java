package dbService.dao;

import model.CallRecord;

import java.sql.SQLException;
import java.util.List;

/**
 * CallRecordDao interface
 *
 * This interface defines methods for managing call records in a data storage system.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public interface CallRecordDao {

    /**
     * Retrieves a list of call records from the data storage system.
     *
     * @return the list of call records retrieved from the data storage
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public List<CallRecord> findCallRecords() throws SQLException;

    /**
     * Inserts a new call record into the data storage system.
     *
     * @param type      the type of the call record
     * @param msisdn    the MSDN (phone number) associated with the call
     * @param startTime the start time of the call
     * @param endTime   the end time of the call
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void insertCallRecord(String type, String msisdn, long startTime, long endTime) throws SQLException;

    /**
     * Creates a table to store call records in the data storage system.
     *
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void createCallRecordTable() throws SQLException;

    /**
     * Drops the table storing call records from the data storage system.
     *
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void dropCallRecordTable() throws SQLException;
}