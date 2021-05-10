package arlaScreens.gui.controller.dep;

import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.gui.model.DepartmentModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import java.io.IOException;
import java.lang.module.Configuration;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DepController implements Initializable {

    DepartmentModel depModel;
    List<ScreenCFG> screenCFGList;

    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label depname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getDep(Department dep) throws SQLException {
        try {
            depModel = new DepartmentModel();
            screenCFGList = new ArrayList<>();
            depname.setText(dep.getName());
            screenCFGList.addAll(depModel.getScreenCFGS(dep.getId()));

            grid = new GridPane();

            for (ScreenCFG screenCFG : screenCFGList) {
                ImageView imageView = new ImageView(new Image(screenCFG.getImgUrl()));
                GridPane.setConstraints(imageView, screenCFG.getColIndex(), screenCFG.getRowIndex());
                GridPane.setConstraints(imageView, screenCFG.getColIndex(), screenCFG.getRowIndex());
                grid.getChildren().addAll(imageView);
                grid.setPrefSize(Window.getWindows().size(), Window.getWindows().size());
                imageView.setFitHeight(450);
                imageView.setFitWidth(800);
            }

            anchorpane.getChildren().add(grid);
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
