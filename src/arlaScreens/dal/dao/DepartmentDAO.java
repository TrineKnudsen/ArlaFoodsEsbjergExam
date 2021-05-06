package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import arlaScreens.be.User;
import arlaScreens.be.ScreenCFG;
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

    public List<User> getAllDep() throws SQLException {
        List<User> allDeps = new ArrayList<>();
        Connection connection = connectionPool.checkOut();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, depName, IsAdmin " +
                    "FROM Department");

            ScreenCFG screenCFG = null;
            User dep = null;
            while (resultSet.next()) {
                int type = resultSet.getInt("IsAdmin");
                int id = resultSet.getInt("id");
                String depName = resultSet.getString("depName");


                dep = new User(id, depName, type);
                allDeps.add(dep);
            }
            return allDeps;
        }
    }

    public void updateDep(User chosenUser, String updatedName) throws SQLException {
        try (Connection con = connectionPool.checkOut()) {
            String sql = "UPDATE Department SET depName = ? WHERE depName = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(2, String.valueOf(chosenUser));
            preparedStatement.setString(1, String.valueOf(updatedName));

            preparedStatement.execute();
        }
    }

    public User createDep(String username, String password, String depName) throws SQLException {
        String sql = "INSERT INTO Login(username, password) VALUES(?,?);";
        String sql2 = "INSERT INTO Department(depName, depLoginId) VALUES(?, (SELECT MAX(id) FROM [Login]));";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();

            PreparedStatement st2 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            st2.setString(1, depName);
            st2.executeUpdate();

            User dep = null;
            ResultSet rs = st2.getGeneratedKeys();
            int id= 0;
            while (rs.next()){
                id = rs.getInt(1);

                dep = new User(id, depName, 0);
            }
            return dep;
        }
    }

    public Department deleteDepartment (Department depToDelete) throws SQLException {
        try (Connection con = connectionPool.checkOut()){
            PreparedStatement statement = con.prepareStatement("DELETE FROM Department WHERE id =?;");
            statement.setInt(1, depToDelete.getId());
            if(statement.executeUpdate() != 1) {
                throw new Exception("Could not delete department"+ depToDelete.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}