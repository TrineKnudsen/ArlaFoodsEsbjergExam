package arlaScreens.gui.util;

import arlaScreens.be.DataPoint;
import arlaScreens.be.ScreenCFG;
import arlaScreens.dal.dao.FileReaderDAO;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.chart.*;

import java.io.IOException;
import java.util.List;

public class DataType implements IDataType {

    FileReaderDAO fileReaderDAO;
    private List<DataPoint> dataPoints;

    public DataType(){
        fileReaderDAO = new FileReaderDAO();
    }

    @Override
    public BarChart drawExcel(ScreenCFG screenCFG) throws IOException {
        dataPoints = fileReaderDAO.getExcelFile(screenCFG.getUrl());
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Workdays");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount");

        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Production of cocio pr. day");

        for (DataPoint dataPoint: dataPoints) {
            dataSeries.getData().add(new XYChart.Data(dataPoint.getKey(), dataPoint.getValue()));
        }
        barChart.getData().add(dataSeries);
        return barChart;
    }

    @Override
    public Chart drawCSV(ScreenCFG screenCFG) throws IOException, CsvValidationException {
        dataPoints = fileReaderDAO.getCSVFile(screenCFG.getUrl());
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Workdays");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();

        for (DataPoint dataPoint : dataPoints){
            series.getData().add(new XYChart.Data(dataPoint.getKey(), dataPoint.getValue()));
        }
        lineChart.getData().add(series);
        return lineChart;
    }
}
