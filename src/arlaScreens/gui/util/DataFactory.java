package arlaScreens.gui.util;

import arlaScreens.be.ScreenCFG;
import javafx.scene.chart.Chart;

import java.io.IOException;

public class DataFactory {

    IDataType iDataType;

    public DataFactory(){
        iDataType = new DataType();
    }

    public Chart getShape(ScreenCFG screenCFG) throws IOException {
        String type = screenCFG.getType();

        switch (type){
            case "barchart":
                return iDataType.drawExcel(screenCFG);
        }
        return null;
    }
}
