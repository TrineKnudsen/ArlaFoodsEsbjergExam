package arlaScreens.dal.dao;

import arlaScreens.be.DataPoint;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileReaderDAO {

    public List<DataPoint> getExcelFile(String url) throws IOException {
        ArrayList<DataPoint> data = new ArrayList<>();

        FileInputStream file = new FileInputStream(new File(url));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Sheet> sheets = workbook.sheetIterator();
        while (sheets.hasNext()) {
            Sheet sg = sheets.next();
            Iterator<Row> iterator = sg.iterator();

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
                dp = new DataPoint(key, value, url);
                data.add(dp);
            }
            workbook.close();
        }
        return data;
    }

    public List<DataPoint> getCSVFile(String url) throws IOException, CsvValidationException {
        List<DataPoint> data = new ArrayList<>();
        CSVReader csvReader = new CSVReader(new FileReader(url));
        String valkey[];
        String key;
        int value;
        DataPoint dp;

        BufferedReader br = new BufferedReader(new FileReader(url));
        String line;

        while ((line = br.readLine()) != null) {
            valkey = line.split(",");
            key = valkey[0];
            value = Integer.parseInt(valkey[1]);
            dp = new DataPoint(key, value, url);
            data.add(dp);
        }
        csvReader.close();
        return data;
    }
}
