package service;

import java.sql.SQLException;

/**
 * CdrGenerator interface
 *
 * Interface for generating CDR (Call Detail Record) files.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public interface CdrGenerator {

    /**
     * Generates CDR files for call records.
     *
     * @throws SQLException if an SQL exception occurs during the operation
     */
    public void generateCdrFiles() throws SQLException;
}