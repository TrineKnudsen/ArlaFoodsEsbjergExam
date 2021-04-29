package arlaScreens.gui.Model;

import arlaScreens.bll.ArlaFoodsLogicFacade;
import arlaScreens.bll.ArlaFoodsManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginModel {

    private ArlaFoodsLogicFacade logicFacade;

    public LoginModel() throws IOException {
        logicFacade = new ArlaFoodsManager();
    }

    public boolean checkLogin(String username, String password) throws SQLException {
        return logicFacade.checkLogin(username, password);
    }
}
