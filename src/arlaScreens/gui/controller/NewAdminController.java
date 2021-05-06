package arlaScreens.gui.controller;

import arlaScreens.bll.util.CustomError;
import arlaScreens.gui.model.AdminModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class NewAdminController {
    public TextField txtFieldUsername;
    public TextField txtFieldPass1;
    public TextField txtFieldPass2;
    private AdminModel adminModel;
    private CustomError error = new CustomError();

    public NewAdminController() throws IOException {
        adminModel = new AdminModel();
    }



    public void handleAddNewAdmin(ActionEvent actionEvent) throws SQLException {
        if (txtFieldUsername.getText().isEmpty()) {
            error.error("Enter a username");
        }
        else if (txtFieldPass1.getText().trim().equalsIgnoreCase(txtFieldPass2.getText().trim()) && !txtFieldUsername.getText().isEmpty() && !txtFieldPass1.getText().isEmpty()) {
            adminModel.createAdmin(txtFieldUsername.getText(), txtFieldPass1.getText());
        }
        else {
            error.error("Passwords don't match");
        }
    }

    public void handleBack(ActionEvent actionEvent) {

    }

    public void handleAddNewDepartment(ActionEvent actionEvent) {
    }
}
