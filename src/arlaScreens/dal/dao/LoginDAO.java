package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {

    private final JDBCConnectionPool connectionPool;

    public LoginDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public boolean checkLogin(String username, String password) throws SQLException {
        try (Connection connection = connectionPool.checkOut()) {
            String sql = "SELECT id FROM AdminLogin where username='" + username + "' AND password='" + password + "'";
            Statement statement = connection.createStatement();
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Department depLogin(String username, String password) throws SQLException{
        try (Connection connection = connectionPool.checkOut()){
            String sql = "SELECT Department.depName, Department.id\n" +
                    "FROM Department\n" +
                    "INNER JOIN\n" +
                    "[Login] ON Department.depLoginId = [Login].id\n" +
                    "WHERE username = '" +username+ "' AND password = '" +password+ "'";
            Statement statement = connection.createStatement();
            statement.execute(sql);

            Department dep = null;
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int depId = resultSet.getInt("id");
                String depName = resultSet.getString("depName");
                dep = new Department(depId, depName);
            } return dep;
        }
    }
}
