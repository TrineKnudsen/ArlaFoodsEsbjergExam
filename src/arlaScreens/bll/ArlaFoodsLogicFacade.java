package arlaScreens.bll;

import arlaScreens.be.Department;

import java.sql.SQLException;

public interface ArlaFoodsLogicFacade {

    boolean checkLogin(String username, String password) throws SQLException;

    Department depLogin(String username, String password) throws SQLException;
}
