package arlaScreens.gui.model;

import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.bll.ArlaFoodsManager;
import arlaScreens.bll.IArlaFoodsLogicFacade;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {

    private IArlaFoodsLogicFacade logicFacade;
    private List<Department> allDeps;

    public AdminModel() throws IOException, SQLException {
        logicFacade = new ArlaFoodsManager();
        allDeps = new ArrayList<>();
        allDeps.addAll(logicFacade.getAllDep());
    }

    public void createAdmin(String username, String password) throws SQLException {
        logicFacade.createAdmin(username, password);
    }

    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl, String fileName) throws SQLException {
        return logicFacade.createCFG(depId, rowIndex, colIndex, imgUrl, fileName);
    }

    public ScreenCFG getCFG(int depid) throws SQLException {
        return logicFacade.getCFG(depid);
    }
}
