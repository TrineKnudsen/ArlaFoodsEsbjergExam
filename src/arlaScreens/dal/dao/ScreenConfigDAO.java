package arlaScreens.dal.dao;

import arlaScreens.be.ScreenCFG;
import arlaScreens.be.User;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScreenConfigDAO {

    private final JDBCConnectionPool connectionPool;

    public ScreenConfigDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public List<ScreenCFG> getCFG(int depId) throws SQLException{
        List<ScreenCFG> screenCFGList = new ArrayList<>();
        String sql = "SELECT ScreenCFG.url, ScreenCFG.ColumnIndex, ScreenCFG.RowIndex, Department.id, Department.depName, Department.IsAdmin " +
                "FROM ScreenCFG " +
                "INNER JOIN Department " +
                "ON ScreenCFG.depId = Department.id " +
                "WHERE depId = ?";

        try (Connection con = connectionPool.checkOut()){
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, depId);
            st.execute();

            ResultSet rs = st.getResultSet();
            while (rs.next()){

                int id = rs.getInt("id");
                String name = rs.getString("depName");
                int type = rs.getInt("IsAdmin");
                int rowIndex = rs.getInt("RowIndex");
                int colIndex = rs.getInt("ColumnIndex");
                String url = rs.getString("url");

                User user = new User(id, name, type);
                ScreenCFG screenCFG = new ScreenCFG(rowIndex, colIndex, url, user);
                screenCFGList.add(screenCFG);
            }
            return screenCFGList;
        }
    }

    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl) throws SQLException {
        String sql = "INSERT INTO ScreenCFG (rowIndex, colIndex, imgUrl) VALUES(?,?,?) where depId = ?;";
        Connection con = connectionPool.checkOut();

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rowIndex);
            ps.setInt(2, colIndex);
            ps.setString(3, imgUrl);
            ps.setInt(4, depId);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            ScreenCFG screenCFG = new ScreenCFG(rowIndex, colIndex, imgUrl, null);
            return screenCFG;
        } catch (SQLException exception) {
            throw new SQLException("Could not create ScreenCFG", exception);
        } finally {
            connectionPool.checkIn(con);
        }
    }
}
