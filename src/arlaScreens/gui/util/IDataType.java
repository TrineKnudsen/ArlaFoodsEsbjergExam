package arlaScreens.gui.util;

import arlaScreens.be.ScreenCFG;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.Chart;

import java.io.IOException;

public interface IDataType {

    BarChart drawExcel(ScreenCFG screenCFG) throws IOException;

    Chart drawCSV(ScreenCFG screenCFG) throws IOException, CsvValidationException;

}
