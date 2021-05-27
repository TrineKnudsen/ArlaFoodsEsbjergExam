package arlaScreens.bll;

import arlaScreens.be.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IArlaFoodsLogicFacade {

    boolean checkLogin(String username, String password) throws SQLException;

    List<User> getAllDep() throws SQLException;

    void updateDep(int chosenDep, String updatedDep) throws SQLException;

    User createDep(String username, String password, String depName) throws SQLException;

    Department deleteDep(Department department) throws SQLException;

    Admin createAdmin(String username, String password) throws SQLException;

    ScreenCFG createCFG(int depId, int rowIndex, int colIndex,String imgUrl,String fileName) throws SQLException;

    List<ScreenCFG> getScreenCFG(int depId) throws SQLException, IOException;

    Department getUserLogin(String username, String password) throws SQLException;

    Department getAdminLogin(String username, String password) throws SQLException;

    ScreenCFG getCFG(int depid) throws SQLException;
}
