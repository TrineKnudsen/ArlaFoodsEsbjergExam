package arlaScreens.gui.controller.admin;

import arlaScreens.be.Department;
import arlaScreens.be.User;
import arlaScreens.bll.util.UserError;
import arlaScreens.gui.model.DepartmentModel;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {


    private final String ERROR_HEADER = "Error occurred!";
    private DepartmentModel departmentModel;
    ObservableList<User> allDep;

    @FXML
    private JFXTextField nameField;
    @FXML
    private AnchorPane anchor;
    @FXML
    private TableView<User> deplst;
    @FXML
    private TableColumn<User, String> nameColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            departmentModel = new DepartmentModel();

            allDep = departmentModel.getAllDep();
            deplst.setItems(allDep);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void getAdmin(Department admin){
        admin.getName();
    }

    public void handleCreateDep(ActionEvent actionEvent) throws IOException {
        Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/view/admin/NewObject.fxml"));
        Scene mainWindowScene = new Scene(mainWindowParent);
        Stage adminStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        adminStage.setScene(mainWindowScene);
        adminStage.setTitle("Admin controls - new admin");
        adminStage.show();
    }

    public void handleUpdateDepartment(ActionEvent actionEvent) throws SQLException {
        int chosenDep = deplst.getSelectionModel().getSelectedItem().getId();
        String updatedDep = nameField.getText().trim();

        if (updatedDep != null){
            departmentModel.updateDep(chosenDep, updatedDep);
        }

    }

    public void handleDeleteDepartment(ActionEvent actionEvent) {
        User depToDelete = deplst.getSelectionModel().getSelectedItem();
        try {
            if (depToDelete != null) {
                departmentModel.deleteDep(depToDelete);
                deplst.getItems().remove(depToDelete);
            } else {
                String message = "Choose the department you wish to delete";
                UserError.displayError(ERROR_HEADER, message);
            }
        } catch (Exception e) {
            UserError.displayError(ERROR_HEADER, e.getMessage());
        }
    }

    public void handleOpenCFG(ActionEvent event) throws IOException {
        Department chosenDep = deplst.getSelectionModel().getSelectedItem();

        if (chosenDep != null) {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/arlaScreens/gui/view/admin/EditCFG.fxml").openStream());
            CFGController cfgController = loader.getController();
            cfgController.getDepartment(chosenDep);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
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

    public void handleExit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
