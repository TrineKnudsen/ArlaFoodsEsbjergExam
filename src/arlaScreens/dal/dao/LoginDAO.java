package arlaScreens.dal.dao;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.bll.util.CustomError;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;

public class LoginDAO {

    private final JDBCConnectionPool connectionPool;

    private CustomError error = new CustomError();

    public LoginDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public boolean checkAdminLogin(String username, String password) throws SQLException {
        try (Connection connection = connectionPool.checkOut()) {
            String sql = "SELECT Username, Password FROM [Admin] where Username='" + username + "' AND Password='" + password + "'";
            Statement statement = connection.createStatement();

            if(statement.execute(sql)){
               ResultSet resultSet = statement.getResultSet();
             if (resultSet.next()) {
                 System.out.println("Login successful");
                 return true;
             } else {
                 System.out.println("Admin Login failed");
             }
                  return false;
               }
        } return true;
    }

    public Department getDepLogin(String username, String password) throws SQLException{
        String sql = "SELECT id, depName, IsAdmin " +
                "FROM Department " +
                "WHERE Username = ? AND Password = ?";
        try (Connection connection = connectionPool.checkOut()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();

            Department department = null;
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int depId = resultSet.getInt("id");
                int isAdmin = resultSet.getInt("IsAdmin");
                String depName = resultSet.getString("depName");
                department = new Department(depId, depName, isAdmin);
            }
            return department;
        }
    }

    public Admin getAdminLogin(String username, String password) throws SQLException {
        String sqlAdmin = "SELECT id, IsAdmin FROM Admin WHERE Username = ? AND Password = ?";
        try (Connection connection = connectionPool.checkOut()) {
            PreparedStatement statement = connection.prepareStatement(sqlAdmin);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();

            Admin admin = null;

            ResultSet resultSetAdmin = statement.getResultSet();

            while (resultSetAdmin.next()) {
                int id = resultSetAdmin.getInt("id");
                int type = resultSetAdmin.getInt("ISAdmin");
                admin = new Admin(id, "Admin", type, username, password);
            }
            return admin;
        }
    }
}
