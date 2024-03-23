package dbService.dao.impl;

import dbService.dao.CallRecordDao;
import dbService.executor.Executor;
import model.CallRecord;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.DataQuery.*;

/**
 * CallRecordDaoImpl class
 *
 * This class provides implementation for interacting with the call records database.
 * It includes methods for finding call records, inserting new call records, creating and dropping call record tables.
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
public class CallRecordDaoImpl implements CallRecordDao {
    private Executor executor;

    /**
     * Instantiates a new Call record dao.
     *
     * @param connection the connection
     */
    public CallRecordDaoImpl(Connection connection) {
        this.executor = new Executor(connection);
    }

    /**
     * Find call records from the database.
     *
     * @return the list of call records retrieved from the database
     * @throws SQLException if an SQL exception occurs while accessing the database
     */
    @Override
    public List<CallRecord> findCallRecords() throws SQLException {
        List<CallRecord> records = new ArrayList<>();
        ResultSet result = executor.execQuery(findRecordsQuery());

        while (result.next()) {
            CallRecord callRecord = new CallRecord(
                    result.getString(1),
                    result.getString(2),
                    result.getLong(3),
                    result.getLong(4));
            records.add(callRecord);
        }
        result.close();
        return records;
    }

    /**
     * Insert a new call record into the database.
     *
     * @param type      the type of the call record
     * @param msisdn    the MSISDN (phone number) associated with the call
     * @param startTime the start time of the call
     * @param endTime   the end time of the call
     * @throws SQLException if an SQL exception occurs while accessing the database
     */
    @Override
    public void insertCallRecord(String type, String msisdn, long startTime, long endTime) throws SQLException {
        executor.execUpdate("insert into records (type, msisdn, start_time, end_time) values ('" + type + "', " +
                "'" + msisdn + "', '" + startTime + "', '" + endTime + "')");
    }

    /**
     * Create the call record table in the database.
     *
     * @throws SQLException if an SQL exception occurs while accessing the database
     */
    @Override
    public void createCallRecordTable() throws SQLException {
        executor.execUpdate(createRecordsTableQuery());
    }

    /**
     * Drop the call record table from the database.
     *
     * @throws SQLException if an SQL exception occurs while accessing the database
     */
    @Override
    public void dropCallRecordTable() throws SQLException {
        executor.execUpdate(dropRecordsTableQuery());
    }
}