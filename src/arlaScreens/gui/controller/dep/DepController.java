package arlaScreens.gui.controller.dep;

import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.gui.model.DepartmentModel;
import arlaScreens.gui.util.DataFactory;
import com.opencsv.exceptions.CsvValidationException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    DepartmentModel depModel;
    List<ScreenCFG> screenCFGList;
    DataFactory dataFactory;

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
            dataFactory = new DataFactory();
            depModel = new DepartmentModel();
            screenCFGList = new ArrayList<>();
            depname.setText(dep.getName());
            screenCFGList.addAll(depModel.getScreenCFGS(dep.getId()));
            anchorpane.setPrefSize(Window.getWindows().size() - 50, Window.getWindows().size() - 50);

            grid = new GridPane();

            for (ScreenCFG screenCFG : screenCFGList) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.getChildren().add(dataFactory.getShape(screenCFG));
                GridPane.setConstraints(anchorPane, screenCFG.getColIndex(), screenCFG.getRowIndex());
                grid.getChildren().addAll(anchorPane);
            }

            anchorpane.setPrefSize(Window.getWindows().size() - 50, Window.getWindows().size() - 50);
            anchorpane.getChildren().add(grid);
            grid.setGridLinesVisible(true);
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
