package arlaScreens.dal.dao;

import arlaScreens.be.Admin;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    private final JDBCConnectionPool connectionPool;

    public AdminDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }


//    public List<Admin> getAllAdmins() throws IOException {
//
//        ArrayList<Admin> allAdmins = new ArrayList<>();
//
//        try(Connection connection = connectionPool.checkOut()) {
//            String sql = "SELECT * FROM Admin;";
//            Statement statement = connection.createStatement();
//            if (statement.execute(sql))
//        }
//    }

    public Admin createAdmin(String username, String password) throws SQLException {
        String sql = "INSERT INTO [Admin] (username, password) VALUES (?,?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            Admin admin = new Admin(id, username, password);
            return admin;
        } catch (SQLException e){
            throw new SQLException("Failed to create Admin", e);
        } finally {
            connectionPool.checkIn(con);
        }
    }

}