package arlaScreens.bll;

import java.sql.SQLException;

public interface ArlaFoodsLogicFacade {

    boolean checkLogin(String username, String password) throws SQLException;
}
