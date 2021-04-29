package arlaScreens.bll;

import arlaScreens.be.Department;
import arlaScreens.dal.dao.DepartmentDAO;
import arlaScreens.dal.dao.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArlaFoodsManager implements ArlaFoodsLogicFacade {

    LoginDAO loginDAO;
    DepartmentDAO depDAO;

    public ArlaFoodsManager() throws IOException {
        loginDAO = new LoginDAO();
        depDAO = new DepartmentDAO();
    }

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        return loginDAO.checkLogin(username, password);
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
}
