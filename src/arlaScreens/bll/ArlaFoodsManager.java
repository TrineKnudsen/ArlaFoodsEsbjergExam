package arlaScreens.bll;

import arlaScreens.be.Department;
import arlaScreens.dal.dao.LoginDAO;

import java.io.IOException;
import java.sql.SQLException;

public class ArlaFoodsManager implements ArlaFoodsLogicFacade {

    LoginDAO loginDAO;

    public ArlaFoodsManager() throws IOException {
        loginDAO = new LoginDAO();
    }

    @Override
    public boolean checkLogin(String username, String password) throws SQLException {
        return loginDAO.checkLogin(username, password);
    }

    @Override
    public Department depLogin(String username, String password) throws SQLException {
        return loginDAO.depLogin(username, password);
    }
}
