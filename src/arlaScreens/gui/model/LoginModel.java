package arlaScreens.gui.model;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.bll.IArlaFoodsLogicFacade;
import arlaScreens.bll.ArlaFoodsManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginModel {

    private IArlaFoodsLogicFacade logicFacade;

    public LoginModel() throws IOException {
        logicFacade = new ArlaFoodsManager();
    }

    public boolean checkAdminLogin(String username, String password) throws SQLException {
        return logicFacade.checkLogin(username, password);
    }

    public Department getUserLogin(String username, String password) throws SQLException {
        return logicFacade.getUserLogin(username, password);
    }

    public Department getAdminLogin(String username, String password) throws SQLException {
        return logicFacade.getAdminLogin(username, password);
    }
}
