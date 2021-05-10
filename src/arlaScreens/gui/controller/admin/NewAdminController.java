package arlaScreens.gui.controller.admin;

import arlaScreens.bll.util.CustomError;
import arlaScreens.gui.model.AdminModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void handleBack(ActionEvent actionEvent) throws IOException {
        Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlascreens/gui/view/admin/DepAdmin.fxml"));
        Scene mainWindowScene = new Scene(mainWindowParent);
        Stage depAdmin = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        depAdmin.setScene(mainWindowScene);
        depAdmin.setTitle("Admin");
        depAdmin.show();

    }

}
