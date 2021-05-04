package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;

public class LoginDAO {

    private final JDBCConnectionPool connectionPool;

    public LoginDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public boolean checkAdminLogin(String username, String password) throws SQLException {
        try (Connection connection = connectionPool.checkOut()) {
            String sql = "SELECT username, password FROM [Admin] where username='" + username + "' AND password='" + password + "'";
            Statement statement = connection.createStatement();

            if(statement.execute(sql)){
               ResultSet resultSet = statement.getResultSet();
             if (resultSet.next()) {
                 System.out.println("Login successful");
                 return true;
             } else {
                 System.out.println("Login failed");
             }
                  return false;
               }
        } return true;
    }

    public Department depLogin(String username, String password) throws SQLException{
        String sql = "SELECT Department.depName, Department.id " +
                "FROM Department " +
                "INNER JOIN " +
                "[Login] ON Department.depLoginId = [Login].id " +
                "WHERE username = ? AND password = ?;";
        try (Connection connection = connectionPool.checkOut()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();

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
