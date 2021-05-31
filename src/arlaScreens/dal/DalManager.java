package arlaScreens.dal;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.dal.dao.AdminDAO;
import arlaScreens.dal.dao.DepartmentDAO;
import arlaScreens.dal.dao.LoginDAO;
import arlaScreens.dal.dao.ScreenConfigDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DalManager implements IDalManager{

    AdminDAO adminDAO;
    DepartmentDAO departmentDAO;
    LoginDAO loginDAO;
    ScreenConfigDAO screenConfigDAO;

    public DalManager() throws IOException {
        adminDAO = new AdminDAO();
        departmentDAO = new DepartmentDAO();
        loginDAO = new LoginDAO();
        screenConfigDAO = new ScreenConfigDAO();
    }


    @Override
    public Admin createAdmin(String username, String password) throws SQLException {
        return adminDAO.createAdmin(username, password);
    }

    @Override
    public List<Admin> getAllAdmin() throws SQLException {
        return adminDAO.getAllAdmin();
    }

    @Override
    public List<Department> getAllDep() throws SQLException {
        return departmentDAO.getAllDep();
    }

    @Override
    public void updateDep(int chosenUser, String updatedName) throws SQLException {
        departmentDAO.updateDep(chosenUser, updatedName);
    }

    @Override
    public Department createDep(String username, String password, String depName) throws SQLException {
        return departmentDAO.createDep(username, password, depName);
    }

    @Override
    public Department deleteDepartment(Department depToDelete) throws SQLException {
        return departmentDAO.deleteDepartment(depToDelete);
    }

    @Override
    public Department getDepLogin(String username, String password) throws SQLException {
        return loginDAO.getDepLogin(username, password);
    }

    @Override
    public Admin getAdminLogin(String username, String password) throws SQLException {
        return loginDAO.getAdminLogin(username, password);
    }

    @Override
    public ScreenCFG getCFG(int depid) throws SQLException {
        return screenConfigDAO.getCFG(depid);
    }

    @Override
    public List<ScreenCFG> getCFGList(int depId) throws SQLException {
        return screenConfigDAO.getCFGList(depId);
    }

    @Override
    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl, String fileName) throws SQLException {
        return screenConfigDAO.createCFG(depId, rowIndex, colIndex, imgUrl, fileName);
    }
}
