package arlaScreens.dal.dao;

import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.dal.JDBCConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScreenConfigDAO {

    private final JDBCConnectionPool connectionPool;

    public ScreenConfigDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Metode der returnerer en skærmkonfiguration
     * @param depid
     * @return
     * @throws SQLException
     */

    public ScreenCFG getCFG(int depid) throws SQLException {
        String sql = "SELECT ScreenCFG.url, ScreenCFG.ColumnIndex, ScreenCFG.RowIndex, ScreenCFG.GraphType, Department.id, Department.depName, Department.IsAdmin " +
                "FROM ScreenCFG " +
                "INNER JOIN Department " +
                "ON ScreenCFG.depId = Department.id " +
                "WHERE depId = ?";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, depid);
            st.execute();

            ScreenCFG screenCFG = null;
            ResultSet rs = st.getResultSet();
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("depName");
                int type = rs.getInt("IsAdmin");
                int rowIndex = rs.getInt("RowIndex");
                int colIndex = rs.getInt("ColumnIndex");
                String url = rs.getString("url");
                String dataType = rs.getString("GraphType");

                Department department = new Department(id, name, type);
                screenCFG = new ScreenCFG(colIndex, rowIndex, dataType, url, department);
            }
            return screenCFG;
        }
    }

    /**
     * Metode det returnerer en liste af skærmkonfigurationer
     * @param depId
     * @return
     * @throws SQLException
     */

    public List<ScreenCFG> getCFGList(int depId) throws SQLException {
        List<ScreenCFG> screenCFGList = new ArrayList<>();
        String sql = "SELECT ScreenCFG.url, ScreenCFG.ColumnIndex, ScreenCFG.RowIndex, " +
                "ScreenCFG.GraphType, Department.id, Department.depName, Department.IsAdmin " +
                "FROM ScreenCFG " +
                "INNER JOIN Department " +
                "ON ScreenCFG.depId = Department.id " +
                "WHERE depId = ?";

        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, depId);
            st.execute();

            ResultSet rs = st.getResultSet();
            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("depName");
                int isAdmin = rs.getInt("IsAdmin");
                int rowIndex = rs.getInt("RowIndex");
                int colIndex = rs.getInt("ColumnIndex");
                String url = rs.getString("url");
                String dataType = rs.getString("GraphType");

                ScreenCFG screenCFG;
                Department department = new Department(id, name, isAdmin);
                screenCFG = new ScreenCFG(colIndex, rowIndex, dataType, url, department);
                screenCFGList.add(screenCFG);
            }
        }
        return screenCFGList;
    }

    /**
     * Metode der laver en ny skærmkonfiguration
     * @param depId
     * @param rowIndex
     * @param colIndex
     * @param imgUrl
     * @param fileName
     * @return
     * @throws SQLException
     */

    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl, String fileName) throws SQLException {
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st1 = con.prepareStatement("INSERT INTO ScreenCFG(depId, RowIndex, "
                    + "ColumnIndex, [url], GraphType) VALUES(?,?,?,?,?)");
            st1.setInt(1, depId);
            st1.setInt(2, rowIndex);
            st1.setInt(3, colIndex);
            st1.setString(4, imgUrl);
            st1.setString(5, fileName);
            st1.executeUpdate();

            PreparedStatement st2 = con.prepareStatement("SELECT ScreenCFG.url, ScreenCFG.ColumnIndex, ScreenCFG.RowIndex, ScreenCFG.GraphType, Department.id, Department.depName, Department.IsAdmin " +
                    "FROM ScreenCFG " +
                    "INNER JOIN Department " +
                    "ON ScreenCFG.depId = Department.id " +
                    "WHERE depId = ?");

            st2.setInt(1, depId);

            ScreenCFG screenCFG = null;
            st2.execute();
            ResultSet rs = st2.getResultSet();
            while (rs.next()) {
                String name = rs.getString("depName");
                int type = rs.getInt("IsAdmin");
                rowIndex = rs.getInt("RowIndex");
                colIndex = rs.getInt("ColumnIndex");
                imgUrl = rs.getString("url");
                fileName = rs.getString("GraphType");

                Department department = new Department(depId, name, type);
                screenCFG = new ScreenCFG(rowIndex, colIndex, imgUrl, fileName, department);
            }
            return screenCFG;
        }
    }
}
