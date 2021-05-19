package arlaScreens.gui.controller.admin;

import arlaScreens.bll.util.CustomError;
import arlaScreens.gui.model.AdminModel;
import arlaScreens.gui.model.DepartmentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NewObjectController {

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField depNameTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private PasswordField password2Txt;
    @FXML
    private CheckBox checkAdminBox;

    private AdminModel adminModel;
    private DepartmentModel departmentModel;
    private CustomError error = new CustomError();

    public NewObjectController() throws IOException, SQLException {
        adminModel = new AdminModel();
        departmentModel= new DepartmentModel();
    }

    public void handleSaveUser(ActionEvent actionEvent) throws SQLException, IOException {
        String depName = depNameTxt.getText().trim();
        String username = txtFieldUsername.getText().trim();
        String password1 = passwordTxt.getText().trim();
        String password2 = password2Txt.getText().trim();

        if (username != null && password1.equalsIgnoreCase(password2) && checkAdminBox.isSelected()) {
            adminModel.createAdmin(username, password1);
        }  else if (depName != null && username != null && password1.equalsIgnoreCase(password2) && !checkAdminBox.isSelected()) {
            departmentModel.createDep(username, password1, depName);
        }
        handleBack(actionEvent);
    }

    public void handleBack(ActionEvent actionEvent) throws IOException {
        Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlascreens/gui/view/admin/DepAdmin.fxml"));
        Scene mainWindowScene = new Scene(mainWindowParent);
        Stage depAdmin = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        depAdmin.setScene(mainWindowScene);
        depAdmin.setTitle("Admin");
        depAdmin.show();
    }
}
