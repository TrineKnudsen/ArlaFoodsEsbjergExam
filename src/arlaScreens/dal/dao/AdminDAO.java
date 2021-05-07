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


    public Admin createAdmin(String username, String password) throws SQLException {
        String sql = "INSERT INTO [Admin] (username, password) VALUES (?,?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            int id = 0;
            int type = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                type = resultSet.getInt("IsAmin");
            }
            Admin admin = new Admin(id, "Admin", type, username, password);
            return admin;
        }
    }
}