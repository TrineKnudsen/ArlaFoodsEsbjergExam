package arlaScreens.gui.util;

import arlaScreens.be.ScreenCFG;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.chart.Chart;

import java.io.File;
import java.io.IOException;

public class DataFactory {

    IDataType iDataType;

    public DataFactory(){
        iDataType = new DataType();
    }

    public Chart getShape(ScreenCFG screenCFG) throws IOException, CsvValidationException {
        String type = screenCFG.getType();

        switch (type){
            case "barchart":
                return iDataType.drawExcel(screenCFG);
            case "linechart":
                return iDataType.drawBarCSV(screenCFG);
        }
        return null;
    }

    public File getPDF(ScreenCFG screenCFG){
        return iDataType.getWebPage(screenCFG);
    }
}
