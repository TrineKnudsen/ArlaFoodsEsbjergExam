package arlaScreens.gui.controller.admin;

import arlaScreens.be.Department;
import arlaScreens.gui.model.AdminModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    Department dep;
    AdminModel adminModel;

    @FXML
    private TextField columnField;
    @FXML
    private TextField rowField;
    @FXML
    private TextField fileNameField;
    @FXML
    private Label deplbl;
    @FXML
    private Button chosenFile;
    @FXML
    private Label lblid;
    @FXML
    private AnchorPane anchor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDepartment(Department dep){
        try {
            this.dep = dep;
            adminModel = new AdminModel();
            deplbl.setText(dep.getName());
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Department getDepartment(){
        return dep;
    }

    public void handleChoosefile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src/files/" )); //Sets the directory to the desktop

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files",  "*.png", "*.jpg"));
    File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                chosenFile.setText("\\files\\" + selectedFile.getName());
            }
        lblid.setText(String.valueOf(getDepartment().getId()));
    }

    public void handleSave(ActionEvent event) throws SQLException {
        String selectedFile = chosenFile.getText().trim();
        int row = Integer.parseInt(rowField.getText().trim());
        int column = Integer.parseInt(columnField.getText().trim());
        String url = chosenFile.getText().trim();
        String filetype = fileNameField.getText().trim();

        if (selectedFile != null){
            adminModel.createCFG(dep.getId(), row, column, url, filetype);
        }
    }

    public void handleBack(ActionEvent actionEvent) throws IOException {
        Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlascreens/gui/view/admin/DepAdmin.fxml"));
        Scene mainWindowScene = new Scene(mainWindowParent);
        Stage depAdmin = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        depAdmin.setScene(mainWindowScene);
        depAdmin.setTitle("Admin");
        depAdmin.show();
    }

    public void handleLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/arlaScreens/gui/view/Main.fxml"));
        Parent mainWindowParent = loader.load();
        Scene mainScene = new Scene(mainWindowParent);
        Stage window = (Stage) anchor.getScene().getWindow();
        window.setScene(mainScene);
        window.setTitle("Arla Foods-Esbjerg");
        window.show();
    }

    public void handleClose(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
