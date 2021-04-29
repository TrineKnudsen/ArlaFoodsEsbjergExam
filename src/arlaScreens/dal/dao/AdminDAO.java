package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import arlaScreens.dal.JDBCConnectionPool;
import javafx.beans.property.ReadOnlySetProperty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    private final JDBCConnectionPool connectionPool;

    public AdminDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }


    /*public List<Admin> getAllAdmins() throws IOException {
        ArrayList<Admin> allAdmins = new ArrayList<>();
    }

    public Admin createAdmin(String username, String password) throws SQLException {
        String sql = "INSERT INTO AdminLogin (username, password) VALUES (?,?);";
        Connection con = connectionPool.checkOut();
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString( 1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            Admin admin = new Admin(id, depId, fullName, isAdmin, username, password);
        } return null;
    } */
}