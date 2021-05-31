package arlaScreens.gui.controller.dep;

import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.bll.util.UserError;
import arlaScreens.gui.model.DepartmentModel;
import arlaScreens.gui.controller.util.DataFactory;
import arlaScreens.gui.controller.util.DataType;
import arlaScreens.gui.controller.util.IDataType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DepController implements Initializable {

    private final String ERROR_HEADER = "Error occurred";
    DepartmentModel depModel;
    List<ScreenCFG> screenCFGList;
    DataFactory dataFactory;
    IDataType iDataType;
    WatchService watchService = FileSystems.getDefault().newWatchService();
    Path path = Paths.get("/arlaScreens/files");
    WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

    @FXML
    private AnchorPane anchorpane;

    public DepController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        iDataType = new DataType();

        WatchService watchService;
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get("/arlaScreens/files");
            WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getDep(Department dep) {
        try {
            iDataType = new DataType();
            dataFactory = new DataFactory();
            depModel = new DepartmentModel();
            screenCFGList = new ArrayList<>();
            screenCFGList.addAll(depModel.getScreenCFGS(dep.getId()));
            anchorpane.setPrefSize(Window.getWindows().size() - 50, Window.getWindows().size() - 50);

            anchorpane.getChildren().add(depSetup());

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private GridPane depSetup(){
        anchorpane.setMaxHeight(689);
        GridPane grid = new GridPane();
        grid.setMaxHeight(689);
        grid.setGridLinesVisible(true);
        try {
            for (ScreenCFG screenCFG : screenCFGList) {
                String type = screenCFG.getType();
                AnchorPane anchorPane = new AnchorPane();
                Label typelbl = new Label();
                typelbl.setText(type);
                anchorPane.getChildren().add(typelbl);
                anchorPane.setMaxHeight(689/2);
                GridPane.setConstraints(anchorPane, screenCFG.getColIndex(), screenCFG.getRowIndex());
                switch (type) {
                    case "barchart":
                        anchorPane.getChildren().add(iDataType.drawExcel(screenCFG));
                        grid.getChildren().add(anchorPane);
                        break;
                    case "linechart":
                        anchorPane.getChildren().add(iDataType.drawLineCSV(screenCFG));
                        grid.getChildren().add(anchorPane);
                        break;
                    case "webpage":
                        WebView webView = new WebView();
                        WebEngine webEngine = webView.getEngine();
                        webView.setMaxHeight(689/2);
                        String url = iDataType.getWebPage(screenCFG).toURI().toURL().toExternalForm();
                        webEngine.load(url);
                        anchorPane.getChildren().add(webView);
                        grid.getChildren().add(anchorPane);
                        break;
                    case "piechart":
                        anchorPane.getChildren().add(iDataType.drawPieCSV(screenCFG));
                        grid.getChildren().add(anchorPane);
                        break;
                }
            }
        } catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Couldn't set up department");
        }
        return grid;
    }

    public void handleUpdate(MouseEvent mouseEvent) {

    }

    public void handleReset(MouseEvent mouseEvent) {
    }
}
