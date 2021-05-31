package arlaScreens.gui.controller.admin;

import arlaScreens.bll.util.CustomError;
import arlaScreens.bll.util.UserError;
import arlaScreens.gui.model.AdminModel;
import arlaScreens.gui.model.DepartmentModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    @FXML
    private Button closeBtn;

    private final String ERROR_HEADER = "Error occurred";
    private AdminModel adminModel;
    private DepartmentModel departmentModel;

    public NewObjectController(){
        try {
            adminModel = new AdminModel();
            departmentModel= new DepartmentModel();
        }catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Try again");
        }
    }

    @FXML
    private void handleSaveUser(ActionEvent actionEvent) {
        try {
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
        }catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Check values");
        }
    }

    @FXML
    private void handleBack(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) closeBtn.getScene().getWindow();
            stage.close();
        }catch (Exception ex){
            UserError.displayError(ERROR_HEADER, "Can't take you back to previous page");
        }
    }
}
