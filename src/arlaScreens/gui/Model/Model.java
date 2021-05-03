package arlaScreens.gui.Model;

import arlaScreens.be.Department;
import arlaScreens.bll.ArlaFoodsLogicFacade;
import arlaScreens.bll.ArlaFoodsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Model {

    private ArlaFoodsLogicFacade logicFacade;

    private ObservableList<Department> allDeps;

    public Model() throws IOException, SQLException {
        logicFacade = new ArlaFoodsManager();

        allDeps = FXCollections.observableArrayList();
        allDeps.addAll(logicFacade.getAllDep());
    }

    public ObservableList<Department> getAllDep() throws SQLException {
        return allDeps;
    }

    public void updateDep(Department chosenDep, String updatedDep) throws SQLException {
        logicFacade.updateDep(chosenDep, updatedDep);
    }

    public Department addDep(String username, String password, String depName) throws SQLException {
        return logicFacade.addDep(username, password, depName);
    }

}
