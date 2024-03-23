package dbService;

import dbService.dao.CallRecordDao;
import dbService.dao.impl.CallRecordDaoImpl;
import dbService.executor.Executor;
import model.CallRecord;
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
 * CallRecordDaoTest class
 *
 * @author razlivinsky
 * @since 22.03.2024
 */
class CallRecordDaoTest {

    @Mock
    private Connection connection;

    @Mock
    private Executor executor;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private CallRecordDao callRecordDao = new CallRecordDaoImpl(connection);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCallRecords() throws SQLException {
        when(executor.execQuery("SELECT * FROM records")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(1)).thenReturn("01");
        when(resultSet.getString(2)).thenReturn("123456789");
        when(resultSet.getLong(3)).thenReturn(1609459200L);
        when(resultSet.getLong(4)).thenReturn(1609462800L);
        List<CallRecord> records = callRecordDao.findCallRecords();

        assertNotNull(records);
        assertEquals(1, records.size());
        CallRecord record = records.get(0);
        assertEquals("01", record.getType());
        assertEquals("123456789", record.getMsisdn());
        assertEquals(1609459200L, record.getStartTime());
        assertEquals(1609462800L, record.getEndTime());

        verify(resultSet, times(1)).close();
    }

    @Test
    void testInsertCallRecord() throws SQLException {
        callRecordDao.insertCallRecord("01", "123456789", 1609459200L, 1609462800L);
        verify(executor, times(1)).execUpdate(anyString());
    }

    @Test
    void testCreateCallRecordTable() throws SQLException {
        callRecordDao.createCallRecordTable();
        verify(executor, times(1)).execUpdate(contains("create table if not exists records"));
    }

    @Test
    void testDropCallRecordTable() throws SQLException {
        callRecordDao.dropCallRecordTable();
        verify(executor, times(1)).execUpdate(contains("drop table records"));
    }
}