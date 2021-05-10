package arlaScreens.dal.dao;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.be.User;
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
        int id = getNextAvailableAdmintID();
        String sql = "INSERT INTO [Admin] (id, username, password) VALUES (?, ?,?);";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, username);
            st.setString(3, password);
            st.executeUpdate();

            Admin admin = new Admin(id, "Admin",1, username, password);
            return admin;
        }
    }

    private int getNextAvailableAdmintID() throws SQLException {
        List<User> allAdmins = getAllAdmin();
        if (allAdmins == null || allAdmins.isEmpty()) {
            return 1;
        }
        allAdmins.sort(Comparator.comparingInt(Department::getId));
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

    public List<User> getAllAdmin() throws SQLException {
        List<User> allAdmins = new ArrayList<>();
        Connection connection = connectionPool.checkOut();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, IsAdmin, username, password " +
                    "FROM Admin");

            User admin = null;
            while (resultSet.next()) {
                int type = resultSet.getInt("IsAdmin");
                int id = resultSet.getInt("id");

                admin = new User(id, "Admin", type);
                allAdmins.add(admin);
            }
            return allAdmins;
        }
    }
}