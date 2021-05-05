package arlaScreens.gui.Controller;

import arlaScreens.be.ScreenCFG;
import arlaScreens.gui.Model.DepartmentModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class LogisticsController implements Initializable {

    DepartmentModel depModel;
    List<ScreenCFG> screenCFGList;

    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchorpane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            depModel = new DepartmentModel();
            screenCFGList = new ArrayList<>();
            screenCFGList.addAll(depModel.getScreenCFGS(2));
            grid = new GridPane();

            for (ScreenCFG screenCFG : screenCFGList) {
                ImageView imageView = new ImageView(new Image(screenCFG.getImgUrl()));
                GridPane.setConstraints(imageView, screenCFG.getColIndex(), screenCFG.getRowIndex());
                grid.getChildren().addAll(imageView);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
            }

            anchorpane.getChildren().add(grid);
        }
        catch(IOException exception){
            exception.printStackTrace();
        } catch(SQLException throwables){
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
