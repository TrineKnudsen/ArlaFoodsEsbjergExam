package arlaScreens.gui.controller.admin;

import arlaScreens.be.Department;
import arlaScreens.bll.util.UserError;
import arlaScreens.gui.model.AdminModel;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CFGController implements Initializable {

    private final String ERROR_HEADER = "Error occurred";
    Department dep;
    AdminModel adminModel;

    @FXML
    private TextField columnField;
    @FXML
    private TextField rowField;
    @FXML
    private JFXComboBox graphlst;
    @FXML
    private Button chosenFile;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button closeBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graphlst.getItems().add("piechart");
        graphlst.getItems().add("barchart");
        graphlst.getItems().add("linechart");
        graphlst.getItems().add("webpage");
    }

    public void setDepartment(Department dep) {
        try {
            this.dep = dep;
            adminModel = new AdminModel();
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Department getDepartment() {
        return dep;
    }

    @FXML
    private void handleChoosefile(ActionEvent event) {
        try {
            String selectedGraph = graphlst.getSelectionModel().getSelectedItem().toString();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("src/files/")); //Sets the directory to the desktop

            if (selectedGraph == "piechart" || selectedGraph == "linechart") {
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", "*.csv"));
            } else if (selectedGraph == "barchart") {
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", "*.xlsx"));
            } else if (selectedGraph == "webpage") {
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", "*.html"));
            }
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                chosenFile.setText("src\\files\\" + selectedFile.getName());
            }
        } catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Choose type of graph first");
        }
    }

    @FXML
    private void handleSave(ActionEvent event) {
        try {
            String selectedFile = chosenFile.getText().trim();
            int row = Integer.parseInt(rowField.getText().trim());
            int column = Integer.parseInt(columnField.getText().trim());
            String url = chosenFile.getText().trim();
            String graphType = graphlst.getSelectionModel().getSelectedItem().toString();

            if (selectedFile != null) {
                adminModel.createCFG(dep.getId(), row, column, url, graphType);
            }
        } catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Check values");
        }
    }

    @FXML
    private void handleBack(ActionEvent actionEvent){
        try {
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.close();
        }
        catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Try again");
        }
    }

    @FXML
    private void handleLogout(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/arlaScreens/gui/view/Main.fxml"));
            Parent mainWindowParent = loader.load();
            Scene mainScene = new Scene(mainWindowParent);
            Stage window = (Stage) anchor.getScene().getWindow();
            window.setScene(mainScene);
            window.setTitle("Arla Foods-Esbjerg");
            window.show();
        } catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Can't log you out");
        }

    }

    @FXML
    private void handleClose(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
