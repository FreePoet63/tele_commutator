package dbService.impl;

import dbService.CallRecordService;
import dbService.dao.impl.CallRecordDaoImpl;
import dbService.exception.DBException;
import model.CallRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static dbService.DBService.getH2Connection;

/**
 * CallRecordServiceImpl class
 *
 * This class provides implementation for managing call records.
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
public class CallRecordServiceImpl implements CallRecordService {
    private final Connection connection;

    /**
     * Instantiates a new Call record service.
     * It sets up the connection to the database.
     */
    public CallRecordServiceImpl() {
        this.connection = getH2Connection();
    }

    /**
     * Retrieves a list of call records from the data storage system.
     *
     * @return the list of call records retrieved from the data storage
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    @Override
    public List<CallRecord> getRecords() throws SQLException {
        return new CallRecordDaoImpl(connection).findCallRecords();
    }

    /**
     * Adds a new call record to the data storage system.
     *
     * @param type      the type of the call record
     * @param msisdn    the MSDN (phone number) associated with the call
     * @param startTime the start time of the call
     * @param endTime   the end time of the call
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    @Override
    public void addCallRecord(String type, String msisdn, long startTime, long endTime) throws SQLException {
        new CallRecordDaoImpl(connection).insertCallRecord(type, msisdn, startTime, endTime);
    }

    /**
     * Creates a table to store call records in the data storage system.
     *
     * @throws SQLException if an SQL exception occurs while accessing the data storage
     */
    @Override
    public void createCallRecordsTable() throws SQLException {
        new CallRecordDaoImpl(connection).createCallRecordTable();
    }

    /**
     * Cleans up the call records by dropping the call records table from the data storage system.
     *
     * @throws DBException if a database exception occurs during the clean-up process
     */
    @Override
    public void cleanUpCallRecords() throws DBException {
        CallRecordDaoImpl dao = new CallRecordDaoImpl(connection);
        try {
            dao.dropCallRecordTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}