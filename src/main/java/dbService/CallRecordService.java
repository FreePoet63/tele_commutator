package dbService;

import dbService.exception.DBException;
import model.CallRecord;

import java.sql.SQLException;
import java.util.List;

/**
 * CallRecordService interface
 *
 * This interface defines methods for managing call records in a data storage system.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public interface CallRecordService {

    /**
     * Retrieves a list of call records from the data storage system.
     *
     * @return the list of call records retrieved from the data storage
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public List<CallRecord> getRecords() throws SQLException;

    /**
     * Adds a new call record to the data storage system.
     *
     * @param type      the type of the call record
     * @param msisdn    the MSISDN (phone number) associated with the call
     * @param startTime the start time of the call
     * @param endTime   the end time of the call
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void addCallRecord(String type, String msisdn, long startTime, long endTime) throws SQLException;

    /**
     * Creates a table to store call records in the data storage system.
     *
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    public void createCallRecordsTable() throws SQLException;

    /**
     * Cleans up the call records by dropping the call records table from the data storage system.
     *
     * @throws DBException if a database exception occurs during the clean-up process
     */
    public void cleanUpCallRecords() throws DBException;
}