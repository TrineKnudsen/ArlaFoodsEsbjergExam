package arlaScreens.gui.util;

import arlaScreens.be.DataPoint;
import arlaScreens.be.ScreenCFG;
import arlaScreens.dal.dao.FileReaderDAO;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

import java.io.File;
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

        NumberAxis yAxis = new NumberAxis();

        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Production of cocio pr. day");

        for (DataPoint dataPoint: dataPoints) {
            xAxis.setLabel(dataPoint.getColumnA());
            yAxis.setLabel(dataPoint.getColumnB());
            dataSeries.getData().add(new XYChart.Data(dataPoint.getKey(), dataPoint.getValue()));
        }
        barChart.getData().add(dataSeries);
        return barChart;
    }

    @Override
    public Chart drawLineCSV(ScreenCFG screenCFG) throws IOException, CsvValidationException {
        dataPoints = fileReaderDAO.getCSVFile(screenCFG.getUrl());
        CategoryAxis xAxis = new CategoryAxis();

        NumberAxis yAxis = new NumberAxis();

        LineChart lineChart = new LineChart(xAxis, yAxis);


        XYChart.Series series = new XYChart.Series();
        series.setName("Overview of Work hours");

        for (DataPoint dataPoint : dataPoints){
            xAxis.setLabel(dataPoint.getColumnA());
            yAxis.setLabel(dataPoint.getColumnB());
            series.getData().add(new XYChart.Data(dataPoint.getKey(), dataPoint.getValue()));
        }
        lineChart.getData().add(series);
        return lineChart;
    }

    @Override
    public Chart drawPieCSV(ScreenCFG screenCFG) throws IOException {
        dataPoints = fileReaderDAO.getCSVFile(screenCFG.getUrl());
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (DataPoint dataPoint : dataPoints) {
            pieChartData.add(new PieChart.Data(dataPoint.getKey(), dataPoint.getValue()));
        }
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        return pieChart;
    }

    @Override
    public File getWebPage(ScreenCFG screenCFG) {
        return fileReaderDAO.getWebPage(screenCFG.getUrl());
    }
}


