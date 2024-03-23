package service.impl;

import model.CallDuration;
import service.UdrGenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static logger.Log.error;
import static logger.Log.info;

/**
 * UdrGeneratorImpl class
 *
 * Generates UDR files and reports based on parsed CDR files and call durations.
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
public class UdrGeneratorImpl implements UdrGenerator {
    private static final String REPORTS_DIRECTORY = "report";
    private static final int MONTHS_IN_YEAR = 12;

    /**
     * Generates and prints summary reports for all subscribers based on parsed CDR files.
     *
     * @throws IOException if an I/O exception occurs during the operation
     */
    @Override
    public void generateReport() throws IOException {
        Map<String, Map<Integer, CallDuration>> reports = parseCdrFiles();
        generateUdrFiles(reports);
        printSummaryReport(reports);
    }

    /**
     * Generates and prints a report for a specific subscriber based on parsed CDR files.
     *
     * @param msisdn the MSISDN (phone number) for which to generate the report
     * @throws IOException if an I/O exception occurs during the operation
     */
    @Override
    public void generateReport(String msisdn) throws IOException {
        Map<String, Map<Integer, CallDuration>> reports = parseCdrFiles();
        generateUdrFiles(Collections.singletonMap(msisdn, reports.get(msisdn)));
        printSingleSubscriberReport(msisdn, reports.get(msisdn));
    }

    /**
     * Generates and prints a report for a specific subscriber and month, based on parsed CDR files.
     *
     * @param msisdn the MSISDN (phone number) for which to generate the report
     * @param month  the month for which to generate the report
     * @throws IOException if an I/O exception occurs during the operation
     */
    @Override
    public void generateReport(String msisdn, int month) throws IOException {
        Map<String, Map<Integer, CallDuration>> reports = parseCdrFiles();
        if (reports.containsKey(msisdn)) {
            Map<Integer, CallDuration> monthlyReports = reports.get(msisdn);
            if (monthlyReports.containsKey(month)) {
                CallDuration report = monthlyReports.get(month);
                generateUdrFiles(msisdn, month, report);
                printMonthlyReport(msisdn, month, report);
            }
        }
    }

    /**
     * Parses CDR files and calculates call durations for each subscriber and month.
     *
     * @return a map containing call duration reports for each subscriber and month
     * @throws IOException if an I/O exception occurs during the operation
     */
    private Map<String, Map<Integer, CallDuration>> parseCdrFiles() throws IOException {
        Map<String, Map<Integer, CallDuration>> reports = new HashMap<>();
        for (int month = 1; month <= MONTHS_IN_YEAR; month++) {
            Path path = Paths.get("cdr_month_" + month + ".txt");
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(",");
                String type = parts[0];
                String msisdn = parts[1];
                long startTime = Long.parseLong(parts[2]);
                long endTime = Long.parseLong(parts[3]);
                long duration = endTime - startTime;

                reports.computeIfAbsent(msisdn, k -> new HashMap<>())
                        .computeIfAbsent(month, k -> new CallDuration())
                        .addDuration(type, duration);
            }
        }
        return reports;
    }

    /**
     * Generates UDR files for a specific subscriber and month, based on the call duration report.
     *
     * @param msisdn the MSISDN (phone number) for which to generate the report
     * @param month  the month for which to generate the report
     * @param report the call duration report for the specific subscriber and month
     * @throws IOException if an I/O exception occurs during the operation
     */
    private void generateUdrFiles(String msisdn, int month, CallDuration report) throws IOException {
        String reportFileName = REPORTS_DIRECTORY + "/" + msisdn + "_" + month + ".json";
        Path reportPath = Paths.get(reportFileName);
        if (reportPath != null) {
            Files.createDirectories(reportPath.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(reportPath)) {
                writer.write(report.toJson(msisdn));
            }
        } else {
            error("Error creating reportPath for " + msisdn + " and month " + month);
        }
    }

    /**
     * Generates UDR files for multiple subscribers and months, based on the call duration reports.
     *
     * @param reports a map containing call duration reports for each subscriber and month
     * @throws IOException if an I/O exception occurs during the operation
     */
    private void generateUdrFiles(Map<String, Map<Integer, CallDuration>> reports) throws IOException {
        for (Map.Entry<String, Map<Integer, CallDuration>> entry : reports.entrySet()) {
            String msisdn = entry.getKey();
            Map<Integer, CallDuration> monthlyReports = entry.getValue();
            for (Map.Entry<Integer, CallDuration> monthlyEntry : monthlyReports.entrySet()) {
                int month = monthlyEntry.getKey();
                CallDuration report = monthlyEntry.getValue();
                String reportFileName = REPORTS_DIRECTORY + "/" + msisdn + "_" + month + ".json";
                Path reportPath = Paths.get(reportFileName);
                if (reportPath != null) {
                    Files.createDirectories(reportPath.getParent());
                    try (BufferedWriter writer = Files.newBufferedWriter(reportPath)) {
                        writer.write(report.toJson(msisdn));
                    }
                } else {
                    error("Error creating reportPath for " + msisdn + " and month " + month);
                }
            }
        }
    }

    /**
     * Prints a summary report containing the total call duration for each subscriber.
     *
     * @param reports a map containing call duration reports for each subscriber and month
     */
    private void printSummaryReport(Map<String, Map<Integer, CallDuration>> reports) {
        System.out.println("MSISDN | Total Duration");
        for (Map.Entry<String, Map<Integer, CallDuration>> entry : reports.entrySet()) {
            String msisdn = entry.getKey();
            long totalDuration = entry.getValue().values().stream()
                    .mapToLong(CallDuration::getTotalDuration)
                    .sum();
            info(msisdn + " | " + totalDuration);
        }
    }

    /**
     * Prints a report containing the total call duration for a specific subscriber, broken down by month.
     *
     * @param msisdn  the MSISDN (phone number) for which to generate the report
     * @param reports a map containing call duration reports for the specific subscriber
     */
    private void printSingleSubscriberReport(String msisdn, Map<Integer, CallDuration> reports) {
        System.out.println("Month | Total Duration");
        for (Map.Entry<Integer, CallDuration> entry : reports.entrySet()) {
            int month = entry.getKey();
            long totalDuration = entry.getValue().getTotalDuration();
            info(month + " | " + totalDuration);
        }
    }

    /**
     * Prints a report containing the total call duration for a specific subscriber and month.
     *
     * @param msisdn the MSISDN (phone number) for which to generate the report
     * @param month  the month for which to generate the report
     * @param report the call duration report for the specific subscriber and month
     */
    private void printMonthlyReport(String msisdn, int month, CallDuration report) {
        info("MSISDN: " + msisdn);
        info("Month: " + month);
        info("Total Duration: " + report.getTotalDuration());
    }
}
