import com.example.neptunclone.controller.Login;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void loginWithValidCredentials() throws SQLException {
        // Arrange
        Login login = new Login();

        // Act
        boolean result = login.login();

        // Assert
        assertTrue(result);
    }

    @Test
    void loginWithInvalidCredentials() throws SQLException {
        // Arrange
        Login login = new Login();
        login.textfield_username.setText("invalidUser");
        login.passwordfield.setText("invalidPassword");

        // Act
        boolean result = login.login();

        // Assert
        assertFalse(result);
    }
}
