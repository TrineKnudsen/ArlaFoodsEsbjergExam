package arlaScreens.dal;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;

import java.sql.SQLException;
import java.util.List;

public interface IDalManager {

    Admin createAdmin(String username, String password) throws SQLException;

    List<Admin> getAllAdmin() throws SQLException;

    List<Department> getAllDep() throws SQLException;

    void updateDep(int chosenUser, String updatedName) throws SQLException;

    Department createDep(String username, String password, String depName) throws SQLException;

    Department deleteDepartment(Department depToDelete) throws SQLException;

    Department getDepLogin(String username, String password) throws SQLException;

    Admin getAdminLogin(String username, String password) throws SQLException;

    ScreenCFG getCFG(int depid) throws SQLException;

    List<ScreenCFG> getCFGList(int depId) throws SQLException;

    ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl, String fileName) throws SQLException;
}
