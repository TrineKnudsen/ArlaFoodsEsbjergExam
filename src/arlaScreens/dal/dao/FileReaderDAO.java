package arlaScreens.dal.dao;

import arlaScreens.be.DataPoint;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileReaderDAO {


    public List<DataPoint> getExcelFile(String url) throws IOException {
        ArrayList<DataPoint> data = new ArrayList<>();

        FileInputStream file = new FileInputStream(new File(url));
        Workbook workbook = new XSSFWorkbook(file);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Sheet> sheets = workbook.sheetIterator();
        while (sheets.hasNext()) {
            Sheet sg = sheets.next();
            Iterator<Row> iterator = sg.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.iterator();

                DataPoint dp = null;
                String key = "";
                int value = -1;

                if (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    key = dataFormatter.formatCellValue(cell).toString();
                }

                if (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    value = Integer.parseInt(dataFormatter.formatCellValue(cell));
                }
                dp = new DataPoint(key, value, url);
                data.add(dp);
            }
            workbook.close();
        }
        return data;
    }
}
