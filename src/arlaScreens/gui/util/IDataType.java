package arlaScreens.gui.util;

import arlaScreens.be.ScreenCFG;
import javafx.scene.chart.BarChart;

import java.io.IOException;

public interface IDataType {

    BarChart drawExcel(ScreenCFG screenCFG) throws IOException;

    BarChart drawCSV(ScreenCFG screenCFG);

}
