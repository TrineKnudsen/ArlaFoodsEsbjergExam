package arlaScreens.gui.model;

import arlaScreens.be.Department;
import arlaScreens.be.User;
import arlaScreens.be.ScreenCFG;
import arlaScreens.bll.IArlaFoodsLogicFacade;
import arlaScreens.bll.ArlaFoodsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentModel {

    private IArlaFoodsLogicFacade logicFacade;

    private ObservableList<User> allDeps;
    private List<ScreenCFG> screenCFGS;

    public DepartmentModel() throws IOException, SQLException {
        logicFacade = new ArlaFoodsManager();

        allDeps = FXCollections.observableArrayList();
        allDeps.addAll(logicFacade.getAllDep());
    }

    public ObservableList<User> getAllDep() throws SQLException {
        return allDeps;
    }

    public void updateDep(int chosenDep, String updatedDep) throws SQLException {
        logicFacade.updateDep(chosenDep, updatedDep);
    }

    public User createDep(String username, String password, String depName) throws SQLException {
        return logicFacade.createDep(username, password, depName);
    }

    public Department deleteDep(Department depToDelete) throws SQLException {
        return logicFacade.deleteDep(depToDelete);
    }

    public List<ScreenCFG> getScreenCFGS(int depId) throws SQLException, IOException {
        screenCFGS = new ArrayList<>();
        screenCFGS.addAll(logicFacade.getScreenCFG(depId));
        return screenCFGS;
    }


}
