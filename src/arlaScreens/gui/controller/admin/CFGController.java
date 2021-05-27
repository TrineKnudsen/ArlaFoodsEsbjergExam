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
import javafx.scene.control.Label;
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
    private Label deplbl;
    @FXML
    private Button chosenFile;
    @FXML
    private Label lblid;
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
            deplbl.setText(dep.getName());
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Department getDepartment() {
        return dep;
    }

    public void handleChoosefile(ActionEvent event) {
        try {
            lblid.setText(String.valueOf(getDepartment().getName()));
            String selectedgraph = graphlst.getSelectionModel().getSelectedItem().toString();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("src/files/")); //Sets the directory to the desktop

            if (selectedgraph == "piechart" || selectedgraph == "linechart") {
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", "*.csv"));
            } else if (selectedgraph == "barchart") {
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", "*.xlsx"));
            } else if (selectedgraph == "webpage") {
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


    public void handleSave(ActionEvent event) {
        try {
            String selectedFile = chosenFile.getText().trim();
            int row = Integer.parseInt(rowField.getText().trim());
            int column = Integer.parseInt(columnField.getText().trim());
            String url = chosenFile.getText().trim();
            String filetype = graphlst.getSelectionModel().getSelectedItem().toString();

            if (selectedFile != null) {
                adminModel.createCFG(dep.getId(), row, column, url, filetype);
            }
        } catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Check values");
        }
    }

    public void handleBack(ActionEvent actionEvent){
        try {
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.close();
        }
        catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Try again");
        }
    }

    public void handleLogout(ActionEvent actionEvent){
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
            UserError.displayError(ERROR_HEADER, "Try again");
        }

    }

    public void handleClose(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
