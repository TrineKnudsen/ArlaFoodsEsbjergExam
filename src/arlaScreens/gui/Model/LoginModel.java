package arlaScreens.gui.Model;

import arlaScreens.be.Department;
import arlaScreens.bll.ArlaFoodsLogicFacade;
import arlaScreens.bll.ArlaFoodsManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginModel {

    private ArlaFoodsLogicFacade logicFacade;

    public LoginModel() throws IOException {
        logicFacade = new ArlaFoodsManager();
    }

    public boolean checkAdminLogin(String username, String password) throws SQLException {
        return logicFacade.checkLogin(username, password);
    }

    public Department depLogin(String username, String password) throws SQLException {
        return logicFacade.depLogin(username, password);
    }
}
