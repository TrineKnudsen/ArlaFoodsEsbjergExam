package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    private final JDBCConnectionPool connectionPool;

    public DepartmentDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public List<Department> getAllDep() throws SQLException {
        List<Department> allDeps = new ArrayList<>();
        Connection connection = connectionPool.checkOut();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Department");

            Department dep = null;
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String depName = resultSet.getString("depName");
                dep = new Department(id, depName);
                allDeps.add(dep);
            } return allDeps;
        }
    }

    public void updateDep(Department chosenDepartment, String updatedName) throws SQLException{
        try (Connection con = connectionPool.checkOut()){
            String sql = "UPDATE Department SET depName = ? WHERE depName = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(2, String.valueOf(chosenDepartment));
            preparedStatement.setString(1, String.valueOf(updatedName));

            preparedStatement.execute();
        }
    }
}
