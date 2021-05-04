package arlaScreens.dal.dao;

import arlaScreens.be.ScreenCFG;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.*;

public class ScreenConfigDAO {

    private final JDBCConnectionPool connectionPool;

    public ScreenConfigDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl) throws SQLException {
        String sql = "INSERT INTO ScreenCFG (rowIndex, colIndex, imgUrl) VALUES(?,?,?) where depId = ?;";
        Connection con = connectionPool.checkOut();

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, rowIndex);
            ps.setInt(2, colIndex);
            ps.setString(3, imgUrl);
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
