package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

class DepartmentDAOTest {

    @Test
    void getAllDep() throws IOException, SQLException {
        //Arrange - setup our test objects et.
        DepartmentDAO depDao = new DepartmentDAO();
        //Act - do the actual cals or method run
        String actualValue = String.valueOf(depDao.getAllDep());
        String expectedValue = "[Dep1, Dep2]";
        //Assert - check if actual value is equal to expected
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void createDep() throws IOException, SQLException {
        //Arrange - setup our test objects etc.
        DepartmentDAO depDAO = new DepartmentDAO();
        //Act - do the actual cals or method run
        String actualValue = String.valueOf(depDAO.createDep("dep4", "dep4", "dep4"));
        String expectedValue = String.valueOf(new Department(3, "dep4", 0));
        //Assert - check if actual value is equal to expected
        Assertions.assertEquals(expectedValue, actualValue);
    }
}