package arlaScreens.dal.dao;

import arlaScreens.be.ScreenCFG;
import arlaScreens.be.User;
import arlaScreens.dal.JDBCConnectionPool;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScreenConfigDAO {

    private final JDBCConnectionPool connectionPool;

    public ScreenConfigDAO() throws IOException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public List<ScreenCFG> getCFG(int depId) throws SQLException, IOException {
        List<ScreenCFG> screenCFGList = new ArrayList<>();
        String sql = "SELECT ScreenCFG.url, ScreenCFG.ColumnIndex, ScreenCFG.RowIndex, ScreenCFG.FileType, Department.id, Department.depName, Department.IsAdmin " +
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
                int type = rs.getInt("IsAdmin");
                int rowIndex = rs.getInt("RowIndex");
                int colIndex = rs.getInt("ColumnIndex");
                String url = rs.getString("url");
                String fileName = rs.getString("FileType");

                FileInputStream file = new FileInputStream(new File(url));
                Workbook workbook = new XSSFWorkbook(file);
                DataFormatter dataFormatter = new DataFormatter();
                Iterator<Sheet> sheets = workbook.sheetIterator();
                while (sheets.hasNext()) {
                    Sheet sg = sheets.next();
                    System.out.println("Sheet name is " + sg.getSheetName());
                    System.out.println("------------");
                    Iterator<Row> iterator = sg.iterator();
                    while (iterator.hasNext()) {
                        Row row = iterator.next();
                        Iterator<Cell> cellIterator = row.iterator();
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            String cellValue = dataFormatter.formatCellValue(cell);
                            //if (cell.getCellType() == CellType.STRING) {
                            //
                            System.out.println(cellValue + "\t");
                        }
                        System.out.println();
                    }
                    workbook.close();

                    User user = new User(id, name, type);
                    ScreenCFG screenCFG = new ScreenCFG(rowIndex, colIndex, workbook, fileName, user);
                    screenCFGList.add(screenCFG);
                }
            }
            return screenCFGList;
        }
    }

    public ScreenCFG createCFG(int depId, int rowIndex, int colIndex, String imgUrl, String fileName) throws SQLException {
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st1 = con.prepareStatement("INSERT INTO ScreenCFG(depId, RowIndex, "
                    + "ColumnIndex, [url], FileType) VALUES(?,?,?,?,?)");
            st1.setInt(1, depId);
            st1.setInt(2, rowIndex);
            st1.setInt(3, colIndex);
            st1.setString(4, imgUrl);
            st1.setString(5, fileName);
            st1.executeUpdate();

            PreparedStatement st2 = con.prepareStatement("SELECT ScreenCFG.url, ScreenCFG.ColumnIndex, ScreenCFG.RowIndex, ScreenCFG.FileType, Department.id, Department.depName, Department.IsAdmin " +
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
                fileName = rs.getString("FileType");

                User user = new User(depId, name, type);
                //screenCFG = new ScreenCFG(rowIndex, colIndex, imgUrl, fileName, user);
            }
            return screenCFG;
        }
    }
}
