package arlaScreens.gui.model;

import arlaScreens.bll.ArlaFoodsManager;
import arlaScreens.bll.IArlaFoodsLogicFacade;

import java.io.IOException;
import java.sql.SQLException;

public class AdminModel {

    private IArlaFoodsLogicFacade logicFacade;

    public AdminModel() throws IOException {
        logicFacade = new ArlaFoodsManager();
    }

    public void createAdmin(String username, String password) throws SQLException {
        logicFacade.createAdmin(username, password);
    }
}
