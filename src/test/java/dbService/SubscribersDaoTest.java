package dbService;

import dbService.dao.SubscribersDao;
import dbService.dao.impl.SubscribersDaoImpl;
import dbService.executor.Executor;
import model.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * SubscribersDaoTest class
 *
 * @author razlivinsky
 * @since 22.03.2024
 */
class SubscribersDaoTest {
    @Mock
    private Connection connection;

    @Mock
    private Executor executor;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private SubscribersDao subscribersDao = new SubscribersDaoImpl(connection);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindSubscribers() throws SQLException {
        when(executor.execQuery("SELECT * FROM subscribers")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(1)).thenReturn("123456789");

        List<Subscriber> subscribers = subscribersDao.findSubscribers();

        assertNotNull(subscribers);
        assertEquals(1, subscribers.size());
        assertEquals("123456789", subscribers.get(0).getMsisdn());

        verify(resultSet, times(1)).close();
    }

    @Test
    void testInsertSubscriber() throws SQLException {
        subscribersDao.insertSubscriber("123456789");
        verify(executor, times(1)).execUpdate(anyString());
    }

    @Test
    void testCreateSubscribersTable() throws SQLException {
        subscribersDao.createSubscribersTable();
        verify(executor, times(1)).execUpdate(contains("create table if not exists subscribers"));
    }

    @Test
    void testDropSubscribersTable() throws SQLException {
        subscribersDao.dropSubscribersTable();
        verify(executor, times(1)).execUpdate(contains("drop table subscribers"));
    }
}