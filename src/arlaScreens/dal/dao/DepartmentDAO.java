package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DepartmentDAO {

    private final JDBCConnectionPool connectionPool;

    public DepartmentDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Metode der returnerer en liste af alle afdelinger
     * @return
     * @throws SQLException
     */
    public List<Department> getAllDep() throws SQLException {
        List<Department> allDeps = new ArrayList<>();
        Connection connection = connectionPool.checkOut();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, depName, IsAdmin " +
                    "FROM Department");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String depName = resultSet.getString("depName");
                int isAdmin = resultSet.getInt("IsAdmin");

                Department dep = new Department(id, depName, isAdmin);
                allDeps.add(dep);
            }
        }
        return allDeps;
    }

    /**
     * Metode der trækker data fra databasen for at opdatere en afdeling
     * @param chosenUser
     * @param updatedName
     * @throws SQLException
     */
    public void updateDep(int chosenUser, String updatedName) throws SQLException {
        try (Connection con = connectionPool.checkOut()) {
            String sql = "UPDATE Department SET depName = ? WHERE id = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, updatedName);
            preparedStatement.setInt(2, chosenUser);

            preparedStatement.execute();
        }
    }

    /**
     * Metode der trækker data fra databasen for at lave en ny afdeling
     * @param username
     * @param password
     * @param depName
     * @return
     * @throws SQLException
     */

    public Department createDep(String username, String password, String depName) throws SQLException {
        int isAdmin = 0;
        int id = getNextAvailableDepartmentID();
        String sql = "INSERT INTO Department(id, Username, Password, isAdmin, depName) VALUES(?,?,?,?,?);";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, username);
            st.setString(3, password);
            st.setInt(4, isAdmin);
            st.setString(5, depName);
            st.executeUpdate();

            Department dep = new Department(id, depName, isAdmin);
            return dep;
        }
    }

    /**
     * Metode, der giver en ny afdeling det næste tilgængelige ID
     * @return
     * @throws SQLException
     */

    public int getNextAvailableDepartmentID() throws SQLException {
        List<Department> allDepartments = getAllDep();
        if (allDepartments == null || allDepartments.isEmpty()) {
            return 1;
        }
        allDepartments.sort(Comparator.comparingInt(Department::getId));
        int id = allDepartments.get(0).getId();
        for (int i = 0; i < allDepartments.size(); i++) {
            if (allDepartments.get(i).getId() <= id) {
                id++;
            } else {
                return id;
            }
        }
        return id;
    }

    /**
     * Metode der trækker data fra databasen til at slette en afdeling
     * @param depToDelete
     * @return
     * @throws SQLException
     */

    public Department deleteDepartment(Department depToDelete) throws SQLException {
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