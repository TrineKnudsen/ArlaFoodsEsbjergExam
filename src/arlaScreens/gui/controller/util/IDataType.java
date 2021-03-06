package arlaScreens.gui.controller.util;

import arlaScreens.be.ScreenCFG;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;

import java.io.File;
import java.io.IOException;

public interface IDataType {

    BarChart drawExcel(ScreenCFG screenCFG) throws IOException;

    Chart drawLineCSV(ScreenCFG screenCFG) throws IOException, CsvValidationException;

    Chart drawPieCSV(ScreenCFG screenCFG) throws IOException;

    File getWebPage(ScreenCFG screenCFG);
}
