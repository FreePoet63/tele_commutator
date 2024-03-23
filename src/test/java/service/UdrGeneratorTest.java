package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import service.impl.UdrGeneratorImpl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * UdrGeneratorTest class
 *
 * @author razlivinsky
 * @since 22.03.2024
 */
public class UdrGeneratorTest {
    @InjectMocks
    private UdrGenerator udrGenerator = new UdrGeneratorImpl();

    private MockedStatic<Files> mockedFiles;
    private MockedStatic<Paths> mockedPaths;

    @BeforeEach
    void setUp() throws IOException {
        mockedFiles = Mockito.mockStatic(Files.class);
        mockedPaths = Mockito.mockStatic(Paths.class);

        mockedFiles.when(() -> Files.createDirectories(any(Path.class))).thenReturn(null);
        mockedFiles.when(() -> Files.newBufferedWriter(any(Path.class))).thenReturn(mock(BufferedWriter.class));
        mockedFiles.when(() -> Files.readAllLines(any(Path.class))).thenReturn(Collections.emptyList());

        Path mockPath = mock(Path.class);
        when(mockPath.getParent()).thenReturn(mock(Path.class));
        mockedPaths.when(() -> Paths.get(anyString())).thenReturn(mockPath);
    }

    @Test
    void testGenerateReport() throws IOException {
        udrGenerator.generateReport();
    }

    @Test
    void testGenerateReportForMsisdnAndMonth() throws IOException {
        String msisdn = "123456789";
        int month = 5;
        udrGenerator.generateReport(msisdn, month);
    }

    @AfterEach
    void tearDown() {
        mockedFiles.close();
        mockedPaths.close();
    }
}