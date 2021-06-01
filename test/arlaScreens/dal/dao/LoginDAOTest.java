package arlaScreens.dal.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginDAOTest {

    @Test
    void getDepLogin() throws IOException, SQLException {
        //Arrange - setup our test objects et.
        LoginDAO loginDAO = new LoginDAO();
        //Act - do the actual cals or method run
        String actualValue = String.valueOf(loginDAO.getDepLogin("dep1", "dep1"));
        String expectedValue = "Dep1";
        //Assert - check if actual value is equal to expected
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void getAdminLogin() throws SQLException, IOException {
        //Arrange - setup our test objects et.
        LoginDAO loginDAO = new LoginDAO();
        //Act - do the actual cals or method run
        String actualValue = String.valueOf(loginDAO.getAdminLogin("admin1", "admin1"));
        String expectedValue = "Admin";
        //Assert - check if actual value is equal to expected
        Assertions.assertEquals(expectedValue, actualValue);
    }
}