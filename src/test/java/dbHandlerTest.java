import com.example.neptunclone.connect.dbHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;

public class dbHandlerTest {

    @Mock
    private Connection mockConnection;

    @BeforeEach
    void setUp() {
        mockConnection = mock(Connection.class);
        dbHandler.setConnection(mockConnection);
    }

    @Test
    void testGetConnection() {
        try {
            when(mockConnection.isValid(1)).thenReturn(true);
            assertNotNull(dbHandler.getConnection());
        } catch (SQLException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    void testIsConnected() {
        assertTrue(dbHandler.isConnected());
    }
}
