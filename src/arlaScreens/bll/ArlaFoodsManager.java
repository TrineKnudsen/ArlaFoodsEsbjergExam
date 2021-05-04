package arlaScreens.bll;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.dal.dao.AdminDAO;
import arlaScreens.dal.dao.DepartmentDAO;
import arlaScreens.dal.dao.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArlaFoodsManager implements IArlaFoodsLogicFacade {

    private LoginDAO loginDAO;
    private DepartmentDAO depDAO;
    private AdminDAO adminDAO;

    public ArlaFoodsManager() throws IOException {
        loginDAO = new LoginDAO();
        depDAO = new DepartmentDAO();
        adminDAO = new AdminDAO();
    }

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        return loginDAO.checkAdminLogin(username, password);
    }

    @Override
    public Department depLogin(String username, String password) throws SQLException {
        return loginDAO.depLogin(username, password);
    }

    @Override
    public List<Department> getAllDep() throws SQLException {
        return depDAO.getAllDep();
    }

    @Override
    public void updateDep(Department chosenDep, String updatedDep) throws SQLException {
        depDAO.updateDep(chosenDep, updatedDep);
    }

    @Override
    public Department createDep(String username, String password, String depName) throws SQLException {
        return depDAO.createDep(username, password, depName);
    }

    @Override
    public Admin createAdmin(String username, String password) throws SQLException {
        return adminDAO.createAdmin(username, password);
    }
}
