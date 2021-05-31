package arlaScreens.dal.dao;

import arlaScreens.be.Admin;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdminDAO {

    private final JDBCConnectionPool connectionPool;

    public AdminDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public Admin createAdmin(String username, String password) throws SQLException {
        int isAdmin = 1;
        int id = getNextAvailableAdmintID();
        String sql = "INSERT INTO [Admin] (id, username, password, isAdmin) VALUES (?, ?,?, ?);";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, username);
            st.setString(3, password);
            st.setInt(4, isAdmin);
            st.executeUpdate();

            Admin admin = new Admin(id, "Admin", isAdmin);
            return admin;
        }
    }

    public int getNextAvailableAdmintID() throws SQLException {
        List<Admin> allAdmins = getAllAdmin();
        if (allAdmins == null || allAdmins.isEmpty()) {
            return 1;
        }
        allAdmins.sort(Comparator.comparingInt(Admin::getId));
        int id = allAdmins.get(0).getId();
        for (int i = 0; i < allAdmins.size(); i++) {
            if (allAdmins.get(i).getId() <= id) {
                id++;
            } else {
                return id;
            }
        }
        return id;
    }

    public List<Admin> getAllAdmin() throws SQLException {
        List<Admin> allAdmins = new ArrayList<>();
        Connection connection = connectionPool.checkOut();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, IsAdmin FROM Admin");

            Admin admin;
            while (resultSet.next()) {
                int isAdmin = resultSet.getInt("IsAdmin");
                int id = resultSet.getInt("id");

                admin = new Admin(id, "Admin", isAdmin);
                allAdmins.add(admin);
            }
            return allAdmins;
        }
    }
}