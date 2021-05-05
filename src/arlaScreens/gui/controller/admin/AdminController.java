package arlaScreens.gui.controller.admin;

import arlaScreens.be.Department;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private DepartmentModel departmentModel;
    ObservableList<Department> allDep;

    @FXML
    private JFXListView<Department> deplst;
    @FXML
    private JFXTextField nameField;
    @FXML
    private TextField newNameField;
    @FXML
    private TextField userField;
    @FXML
    private TextField passField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            departmentModel = new DepartmentModel();

            allDep = departmentModel.getAllDep();
//            deplst.getItems().addAll(allDep);
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void updateDep(ActionEvent event) throws SQLException {
        Department chosenDep = deplst.getSelectionModel().getSelectedItem();
        String updatedDep = nameField.getText().trim();

        departmentModel.updateDep(chosenDep, updatedDep);
        allDep = departmentModel.getAllDep();
        deplst.getItems().addAll(allDep);
    }

    public void handleCreateDep(ActionEvent event) throws SQLException {
        String name = newNameField.getText().trim();
        String username = userField.getText().trim();
        String password = passField.getText().trim();

        Department dep = departmentModel.createDep(username, password, name);
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
}
