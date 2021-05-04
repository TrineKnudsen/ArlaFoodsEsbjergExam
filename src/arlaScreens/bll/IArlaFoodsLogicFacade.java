package arlaScreens.bll;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;

import java.sql.SQLException;
import java.util.List;

public interface IArlaFoodsLogicFacade {

    boolean checkLogin(String username, String password) throws SQLException;

    Department depLogin(String username, String password) throws SQLException;

    List<Department> getAllDep() throws SQLException;

    void updateDep(Department chosenDep, String updatedDep) throws SQLException;

    Department createDep(String username, String password, String depName) throws SQLException;

    Admin createAdmin(String username, String password) throws SQLException;
}
