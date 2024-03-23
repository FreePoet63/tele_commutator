package service;

import dbService.impl.CallRecordServiceImpl;
import dbService.impl.SubscriberServiceImpl;
import model.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.impl.CdrGeneratorImpl;

import java.io.FileWriter;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * CdrGeneratorTest class
 *
 * @author razlivinsky
 * @since 22.03.2024
 */
class CdrGeneratorTest {
    @Mock
    private SubscriberServiceImpl subscriberService;

    @Mock
    private CallRecordServiceImpl callRecordService;

    @Mock
    private FileWriter fileWriter;

    @InjectMocks
    private CdrGenerator cdrGenerator = new CdrGeneratorImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateCdrFiles() throws Exception {
        List<Subscriber> subscribers = List.of(new Subscriber("123456789"), new Subscriber("987654321"));
        when(subscriberService.getSubscribers()).thenReturn(subscribers);

        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(5);
        when(random.nextBoolean()).thenReturn(true).thenReturn(false);

        cdrGenerator.generateCdrFiles();

        verify(subscriberService, times(1)).getSubscribers();
        verify(callRecordService, atLeastOnce()).addCallRecord(anyString(), anyString(), anyLong(), anyLong());
    }
}