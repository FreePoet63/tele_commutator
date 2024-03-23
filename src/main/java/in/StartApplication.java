package in;

import dbService.CallRecordService;
import dbService.SubscriberService;
import dbService.impl.CallRecordServiceImpl;
import dbService.impl.SubscriberServiceImpl;
import dbService.exception.DBException;
import service.CdrGenerator;
import service.UdrGenerator;
import service.impl.CdrGeneratorImpl;
import service.impl.UdrGeneratorImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

/**
 * StartApplication class
 *
 * The main class to start the application, performs initialization, generation, and clean-up processes.
 *
 * @author razlivinsky
 * @since 23.03.2024
 */
public class StartApplication {

    /**
     * The main method to start the application.
     *
     * @param args command line arguments
     * @throws SQLException   if an SQL exception occurs
     * @throws DBException   if a database exception occurs
     * @throws IOException   if an I/O exception occurs
     */
    public static void main(String[] args) throws SQLException, DBException, IOException {
        SubscriberService subscriberService = new SubscriberServiceImpl();
        CallRecordService callRecordService = new CallRecordServiceImpl();
        CdrGenerator cdrGenerator = new CdrGeneratorImpl();
        UdrGenerator udrGenerator = new UdrGeneratorImpl();
        subscriberService.createSubscriberTable();
        callRecordService.createCallRecordsTable();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 12; j++) {
                sb.append(random.nextInt(10));
            }
            String randomNumber = sb.toString();
            subscriberService.addSubscriber(randomNumber);
        }
        cdrGenerator.generateCdrFiles();
        System.out.println(callRecordService.getRecords());
        udrGenerator.generateReport();
        subscriberService.cleanUpSubscriber();
        callRecordService.cleanUpCallRecords();
    }
}