package arlaScreens.bll;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.dal.DalManager;
import arlaScreens.dal.IDalManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ArlaFoodsManager implements IArlaFoodsLogicFacade {

    IDalManager dalManager;

    public ArlaFoodsManager() throws IOException {
        dalManager = new DalManager();
    }

    @Override
    public List<Department> getAllDep() throws SQLException {
        return dalManager.getAllDep();
    }

    @Override
    public void updateDep(int chosenDep, String updatedDep) throws SQLException {
        dalManager.updateDep(chosenDep, updatedDep);
    }

    @Override
    public Department createDep(String username, String password, String depName) throws SQLException {
        return dalManager.createDep(username, password, depName);
    }

    @Override
    public Department deleteDep(Department department) throws SQLException {
        return dalManager.deleteDepartment(department);
    }

    @Override
    public Admin createAdmin(String username, String password) throws SQLException {
        return dalManager.createAdmin(username, password);
    }

    @Override
    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl, String fileName) throws SQLException {
        return dalManager.createCFG(depId, rowIndex, colIndex, imgUrl, fileName);
    }

    @Override
    public List<ScreenCFG> getScreenCFG(int depId) throws SQLException {
        return dalManager.getCFGList(depId);
    }

    @Override
    public Admin getAdminLogin(String username, String password) throws SQLException {
        return dalManager.getAdminLogin(username, password);
    }

    @Override
    public ScreenCFG getCFG(int depid) throws SQLException {
        return dalManager.getCFG(depid);
    }

    @Override
    public Department getUserLogin(String username, String password) throws SQLException {
        return dalManager.getDepLogin(username, password);
    }
}
