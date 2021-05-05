package arlaScreens.dal.dao;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.be.User;
import arlaScreens.be.ScreenCFG;
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

    public User getUserLogin(String username, String password) throws SQLException{
        String sql = "SELECT Department.depName, Department.id, ScreenCFG.url, Department.IsAdmin, ScreenCFG.ColumnIndex, ScreenCFG.RowIndex " +
                "FROM Department " +
                "INNER JOIN " +
                "ScreenCFG ON Department.id = ScreenCFG.depId " +
                "WHERE Username = ? AND Password = ?;";
        try (Connection connection = connectionPool.checkOut()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();

            ScreenCFG screenCFG = null;
            User user = null;
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int rowIndex = resultSet.getInt("RowIndex");
                int columnIndex = resultSet.getInt("ColumnIndex");
                String url = resultSet.getString("url");
                screenCFG = new ScreenCFG(rowIndex, columnIndex, url);


                int depId = resultSet.getInt("id");
                int type = resultSet.getInt("IsAdmin");
                String depName = resultSet.getString("depName");
                user = new User(depId, depName, type, screenCFG);
            }return user;
        }
    }

    public Admin getAdminLogin(String username, String password) throws SQLException {
        String sqlAdmin = "SELECT id, IsAdmin FROM Admin WHERE Username = ? AND Password = ?";
        try (Connection connection = connectionPool.checkOut()) {
            PreparedStatement statement1 = connection.prepareStatement(sqlAdmin);
            statement1.setString(1, username);
            statement1.setString(2, password);
            statement1.execute();

            Admin admin = null;

            ResultSet resultSetAdmin = statement1.getResultSet();

            while (resultSetAdmin.next()) {
                int id = resultSetAdmin.getInt("id");
                int type = resultSetAdmin.getInt("ISAdmin");
                admin = new Admin(id, "Admin", type, username, password);
            }
            return admin;
        }
    }
}
