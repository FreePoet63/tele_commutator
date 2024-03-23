package service.impl;

import dbService.impl.CallRecordServiceImpl;
import dbService.impl.SubscriberServiceImpl;
import model.Subscriber;
import service.CdrGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static logger.Log.info;

/**
 * CdrGeneratorImpl class
 *
 * Generates Call Detail Record (CDR) files and adds corresponding call records using SubscriberService and CallRecordService.
 *
 * @author razlivinsky
 * @since 21.03.2024
 */
public class CdrGeneratorImpl implements CdrGenerator {
    private static final int MONTHS_IN_YEAR = 12;
    private Random random = new Random();

    /**
     * The Subscriber service.
     */
    SubscriberServiceImpl subscriberService = new SubscriberServiceImpl();

    /**
     * The Call record service.
     */
    CallRecordServiceImpl callRecordService = new CallRecordServiceImpl();

    /**
     * Generates CDR files and adds call records based on the generated data.
     *
     * @throws SQLException if an SQL exception occurs during the operation
     */
    @Override
    public void generateCdrFiles() throws SQLException {
        List<Subscriber> msisdns = subscriberService.getSubscribers();
        List<String> msisdnStrings = msisdns.stream()
                .map(Subscriber::getMsisdn)
                .collect(Collectors.toList());
        for (int month = 1; month <= MONTHS_IN_YEAR; month++) {
            try (FileWriter writer = new FileWriter("cdr_month_" + month + ".txt", true)) {
                for (String msisdn : msisdnStrings) {
                    int recordsCount = random.nextInt(10) + 1;
                    for (int i = 0; i < recordsCount; i++) {
                        String type = random.nextBoolean() ? "01" : "02";
                        long startTime = getRandomUnixTime(month);
                        long endTime = getRandomUnixTime(month);

                        String record = type + "," + msisdn + "," + startTime + "," + endTime + "\n";
                        writer.write(record);
                        callRecordService.addCallRecord(type, msisdn, startTime, endTime);
                    }
                }
            } catch (IOException e) {
                info(e.getMessage());
            }
        }
    }

    /**
     * Generates a random UNIX time within the given month.
     *
     * @param month the month for which the time should be generated
     * @return a random UNIX time within the specified month
     */
    private static long getRandomUnixTime(int month) {
        LocalDateTime start = LocalDateTime.of(2024, month, 1, 0, 0);
        LocalDateTime end = start.plusMonths(1).minusSeconds(1);
        long startEpoch = start.toEpochSecond(ZoneOffset.UTC);
        long endEpoch = end.toEpochSecond(ZoneOffset.UTC);
        return ThreadLocalRandom.current().nextLong(startEpoch, endEpoch);
    }
}