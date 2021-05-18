package arlaScreens.gui.controller.dep;

import arlaScreens.be.DataPoint;
import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.gui.model.DepartmentModel;
import arlaScreens.gui.model.FileModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DepController implements Initializable {

    FileModel fileModel;
    DepartmentModel depModel;
    List<ScreenCFG> screenCFGList;
    ObservableList<DataPoint> dataPoints;

    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label depname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getDep(Department dep) {
        try {
            depModel = new DepartmentModel();
            fileModel = new FileModel();
            screenCFGList = new ArrayList<>();
            depname.setText(dep.getName());
            screenCFGList.addAll(depModel.getScreenCFGS(dep.getId()));
            anchorpane.setPrefSize(Window.getWindows().size() - 50, Window.getWindows().size() - 50);

            grid = new GridPane();

            for (ScreenCFG screenCFG : screenCFGList) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getChildren().add(buildBarChart());
                //ImageView imageView = new ImageView(new Image((InputStream) screenCFG.getImgUrl()));
                GridPane.setConstraints(anchorPane, screenCFG.getColIndex(), screenCFG.getRowIndex());
                GridPane.setConstraints(anchorPane, screenCFG.getColIndex(), screenCFG.getRowIndex());
                grid.getChildren().addAll(anchorPane);
                anchorPane.setPrefSize(400,790);
            }

            anchorpane.setPrefSize(Window.getWindows().size() - 50, Window.getWindows().size() - 50);
            anchorpane.getChildren().add(grid);
            grid.setGridLinesVisible(true);
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private BarChart buildBarChart(){
        dataPoints = fileModel.getExcelData();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Workdays");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount");

        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Production of cocio pr. day");

        for (DataPoint dataPoint: dataPoints) {
            dataSeries.getData().add(new XYChart.Data(dataPoint.getKey(), dataPoint.getValue()));
            barChart.getData().add(dataSeries);
        }
        return barChart;
    }

    /**public void handleBtn(ActionEvent event){
     try {
     Parent MainParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepAdmin.fxml"));
     Scene MainScene = new Scene(MainParent);
     Stage addMovieStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     addMovieStage.setScene(MainScene);
     addMovieStage.show();
     } catch (IOException ex){
     ex.printStackTrace();
     }
     }**/
}
