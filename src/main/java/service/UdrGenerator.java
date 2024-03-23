package service;

import java.io.IOException;

/**
 * UdrGenerator interface
 *
 * Interface for generating UDR (Usage Detail Record) reports.
 *
 * author razlivinsky
 * @since 23.03.2024
 */
public interface UdrGenerator {

    /**
     * Generates a summary report containing usage details for all subscribers.
     *
     * @throws IOException if an I/O exception occurs during the operation
     */
    public void generateReport() throws IOException;

    /**
     * Generates a report containing usage details for a specific subscriber.
     *
     * @param msisdn the MSISDN (phone number) for which to generate the report
     * @throws IOException if an I/O exception occurs during the operation
     */
    public void generateReport(String msisdn) throws IOException;

    /**
     * Generates a report containing usage details for a specific subscriber and month.
     *
     * @param msisdn the MSISDN (phone number) for which to generate the report
     * @param month  the month for which to generate the report
     * @throws IOException if an I/O exception occurs during the operation
     */
    public void generateReport(String msisdn, int month) throws IOException;
}