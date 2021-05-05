package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.dal.JDBCConnectionPool;

import javax.xml.transform.Result;
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
        String sql = "SELECT url, ColumnIndex, RowIndex FROM ScreenCFG WHERE depId = ?;";

        try (Connection con = connectionPool.checkOut()){
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, depId);
            st.execute();

            ResultSet rs = st.getResultSet();
            while (rs.next()){
                int rowIndex = rs.getInt("RowIndex");
                int colIndex = rs.getInt("ColumnIndex");
                String url = rs.getString("url");

                ScreenCFG screenCFG = new ScreenCFG(rowIndex, colIndex, url);
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

            ScreenCFG screenCFG = new ScreenCFG(rowIndex, colIndex, imgUrl);
            return screenCFG;
        } catch (SQLException exception) {
            throw new SQLException("Could not create ScreenCFG", exception);
        } finally {
            connectionPool.checkIn(con);
        }
    }
}
