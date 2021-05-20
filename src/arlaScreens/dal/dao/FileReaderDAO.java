package arlaScreens.dal.dao;

import arlaScreens.be.DataPoint;
import com.opencsv.CSVReader;
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
        Workbook workbook = new XSSFWorkbook(file);
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

    public List<String> getCSVFile(String url) throws IOException {
        try {
            CSVReader reader = new CSVReader(new FileReader(url));
            String[] nextLine;
            while ((nextLine= reader.readNext()) != null){
                if (nextLine != null)

            }

        }

        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(url))){
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(",");
                data.add(values);
            }
            return (List<String>) data;

        }
    }
}
