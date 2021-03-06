package arlaScreens.bll.util;

import arlaScreens.be.DataPoint;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileReader {

    /**
     * Metode til at læse en Excel fil
     * @param url
     * @return
     * @throws IOException
     */

    public List<DataPoint> getExcelFile(String url) throws IOException {
        ArrayList<DataPoint> data = new ArrayList<>();

        FileInputStream file = new FileInputStream(new File(url));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Sheet> sheets = workbook.sheetIterator();

        while (sheets.hasNext()) {
            Sheet sh = sheets.next();
            Iterator<Row> iterator = sh.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                Iterator<Cell> cellIterator = row.iterator();
                DataPoint dp;
                String key = "";
                int value = -1;

                if (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    key = dataFormatter.formatCellValue(cell);
                }

                if (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    value = Integer.parseInt(dataFormatter.formatCellValue(cell));
                }
                dp = new DataPoint("", "", key, value);
                data.add(dp);
            }
            workbook.close();
        }
        return data;
    }

    /**
     * Metode til at læse en CSV fil
     * @param url
     * @return
     * @throws IOException
     */

    public List<DataPoint> getCSVFile(String url) throws IOException {
        List<DataPoint> data = new ArrayList<>();
        CSVReader csvReader = new CSVReader(new java.io.FileReader(url));
        String valkey[];
        String key;
        int value;
        DataPoint dp;

        BufferedReader br = new BufferedReader(new java.io.FileReader(url));
        String line;

        while ((line = br.readLine()) != null) {
            valkey = line.split(",");
            key = valkey[0];
            value = Integer.parseInt(valkey[1]);
            dp = new DataPoint(null, null, key, value);
            data.add(dp);
        }
        csvReader.close();
        return data;
    }

    /**
     * Metode til at læse en hjemmeside
     * @param url
     * @return
     */

    public File getWebPage(String url) {
        File htmlFile = new File(url);
        return htmlFile;
    }
}
