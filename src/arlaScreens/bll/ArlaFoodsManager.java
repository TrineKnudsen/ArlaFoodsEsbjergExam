package arlaScreens.bll;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.be.User;
import arlaScreens.be.ScreenCFG;
import arlaScreens.dal.dao.AdminDAO;
import arlaScreens.dal.dao.DepartmentDAO;
import arlaScreens.dal.dao.LoginDAO;
import arlaScreens.dal.dao.ScreenConfigDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArlaFoodsManager implements IArlaFoodsLogicFacade {

    private LoginDAO loginDAO;
    private DepartmentDAO depDAO;
    private AdminDAO adminDAO;
    private ScreenConfigDAO cfgDAO;

    public ArlaFoodsManager() throws IOException {
        loginDAO = new LoginDAO();
        depDAO = new DepartmentDAO();
        adminDAO = new AdminDAO();
        cfgDAO = new ScreenConfigDAO();
    }

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        return loginDAO.checkAdminLogin(username, password);
    }

    @Override
    public List<User> getAllDep() throws SQLException {
        return depDAO.getAllDep();
    }

    @Override
    public void updateDep(User chosenDep, String updatedDep) throws SQLException {
        depDAO.updateDep(chosenDep, updatedDep);
    }

    @Override
    public User createDep(String username, String password, String depName) throws SQLException {
        return depDAO.createDep(username, password, depName);
    }

    @Override
    public Admin createAdmin(String username, String password) throws SQLException {
        return adminDAO.createAdmin(username, password);
    }

    @Override
    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String fileName, String imgUrl) throws SQLException {
        return cfgDAO.createCFG(depId, rowIndex, colIndex, fileName, imgUrl);
    }

    @Override
    public List<ScreenCFG> getScreenCFG(int depId) throws SQLException {
        return cfgDAO.getCFG(depId);
    }


    @Override
    public Department getAdminLogin(String username, String password) throws SQLException {
        return loginDAO.getAdminLogin(username, password);
    }

    @Override
    public Department getUserLogin(String username, String password) throws SQLException {
        return loginDAO.getUserLogin(username, password);
    }
}
