package arlaScreens.gui.model;

import arlaScreens.be.ScreenCFG;
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

    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String fileName, String imgUrl) throws SQLException {
        return logicFacade.createCFG(depId, rowIndex, colIndex, fileName, imgUrl);
    }
}
