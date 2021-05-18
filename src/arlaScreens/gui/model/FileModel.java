package arlaScreens.gui.model;

import arlaScreens.be.DataPoint;
import arlaScreens.bll.ArlaFoodsManager;
import arlaScreens.bll.IArlaFoodsLogicFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class FileModel {

    private IArlaFoodsLogicFacade logicFacade;

    private ObservableList<DataPoint> excelData;

    public FileModel() throws IOException {
        logicFacade = new ArlaFoodsManager();

        excelData = FXCollections.observableArrayList();
        excelData.addAll(logicFacade.getExcelFile());
    }

    public ObservableList<DataPoint> getExcelData(){
        return excelData;
    }
}
