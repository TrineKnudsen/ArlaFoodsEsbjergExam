package arlaScreens.bll;

import arlaScreens.be.*;
import arlaScreens.dal.dao.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArlaFoodsManager implements IArlaFoodsLogicFacade {

    private LoginDAO loginDAO;
    private DepartmentDAO departmentDAO;
    private AdminDAO adminDAO;
    private ScreenConfigDAO cfgDAO;
    private FileReaderDAO fileReaderDAO;

    public ArlaFoodsManager() throws IOException {
        loginDAO = new LoginDAO();
        departmentDAO = new DepartmentDAO();
        adminDAO = new AdminDAO();
        cfgDAO = new ScreenConfigDAO();
        fileReaderDAO = new FileReaderDAO();
    }

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        return loginDAO.checkAdminLogin(username, password);
    }

    @Override
    public List<Department> getAllDep() throws SQLException {
        return departmentDAO.getAllDep();
    }

    @Override
    public void updateDep(int chosenDep, String updatedDep) throws SQLException {
        departmentDAO.updateDep(chosenDep, updatedDep);
    }

    @Override
    public Department createDep(String username, String password, String depName) throws SQLException {
        return departmentDAO.createDep(username, password, depName);
    }

    @Override
    public Department deleteDep(Department department) throws SQLException {
        return departmentDAO.deleteDepartment(department);
    }

    @Override
    public Admin createAdmin(String username, String password) throws SQLException {
        return adminDAO.createAdmin(username, password);
    }

    @Override
    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl, String fileName) throws SQLException {
        return cfgDAO.createCFG(depId, rowIndex, colIndex, imgUrl, fileName);
    }

    @Override
    public List<ScreenCFG> getScreenCFG(int depId) throws SQLException, IOException {
        return cfgDAO.getCFGList(depId);
    }

    @Override
    public Admin getAdminLogin(String username, String password) throws SQLException {
        return loginDAO.getAdminLogin(username, password);
    }

    @Override
    public ScreenCFG getCFG(int depid) throws SQLException {
        return cfgDAO.getCFG(depid);
    }

    @Override
    public Department getUserLogin(String username, String password) throws SQLException {
        return loginDAO.getDepLogin(username, password);
    }
}
