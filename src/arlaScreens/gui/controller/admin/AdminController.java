package arlaScreens.gui.controller.admin;

import arlaScreens.be.Department;
import arlaScreens.be.User;
import arlaScreens.bll.util.UserError;
import arlaScreens.gui.model.DepartmentModel;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
    private JFXListView<User> deplst;
    @FXML
    private JFXTextField nameField;
    @FXML
    private TextField newNameField;
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;

    public AnchorPane arlaAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            departmentModel = new DepartmentModel();

            allDep = departmentModel.getAllDep();
            deplst.getItems().addAll(allDep);
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void handleCreateDep(ActionEvent event) throws SQLException {
        String name = newNameField.getText().trim();
        String username = userField.getText().trim();
        String password = passField.getText().trim();

        User dep = departmentModel.createDep(username, password, name);
        allDep.add(dep);
        deplst.setItems(allDep);
    }

    public void handleAddNewAdmin(ActionEvent actionEvent) throws IOException {
        Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/view/admin/NewAdmin.fxml"));
        Scene mainWindowScene = new Scene(mainWindowParent);
        Stage adminStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        adminStage.setScene(mainWindowScene);
        adminStage.setTitle("Admin controls - new admin");
        adminStage.show();
    }

    public void handleAddNewDepartment(ActionEvent actionEvent) throws IOException {
        Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/view/admin/NewDepartment.fxml"));
        Scene mainWindowScene = new Scene(mainWindowParent);
        Stage adminStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        adminStage.setScene(mainWindowScene);
        adminStage.setTitle("Admin controls - new department");
        adminStage.show();
    }



    public void handleUpdateDepartment(ActionEvent actionEvent) throws SQLException {
        User chosenDep = deplst.getSelectionModel().getSelectedItem();
        String updatedDep = nameField.getText().trim();

        departmentModel.updateDep(chosenDep, updatedDep);
        allDep = departmentModel.getAllDep();
        deplst.getItems().addAll(allDep);
    }

    public void handleDeleteDepartment(ActionEvent actionEvent) {
        Department depToDelete = deplst.getSelectionModel().getSelectedItem();
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

    public void handleLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/arlaScreens/gui/view/Main.fxml"));
        Parent main = loader.load();
        Scene mainScene = new Scene(main);
        Stage window = (Stage)arlaAnchorPane.getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    public void handleExit(ActionEvent actionEvent) {

    }


}
