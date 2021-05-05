package arlaScreens.gui.controller;

import arlaScreens.be.Admin;
import arlaScreens.be.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import arlaScreens.gui.model.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public PasswordField txtFieldPassword;
    @FXML
    public TextField txtFieldUsername;
    @FXML
    private LoginModel loginModel;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane anchor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginModel = new LoginModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void handleBtnLogin(ActionEvent event) throws IOException, SQLException {
        String username = txtFieldUsername.getText().trim();
        String password = txtFieldPassword.getText().trim();

        User user = loginModel.getUserLogin(username, password);
        Admin admin = loginModel.getAdminLogin(username, password);

        if (user != null && admin == null){
            Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepLogistics.fxml"));
            Scene mainWindowScene = new Scene(mainWindowParent);
            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            adminStage.setScene(mainWindowScene);
            adminStage.setTitle("Department");
            adminStage.show();
        } else if (user == null && admin != null) {
            Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepAdmin.fxml"));
            Scene mainWindowScene = new Scene(mainWindowParent);
            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            adminStage.setScene(mainWindowScene);
            adminStage.setTitle("Admin Controls");
            adminStage.show();
        }
    }

    public void handleAdminControls(ActionEvent actionEvent) throws IOException {
        Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/view/admin/AdminControls.fxml"));
        Scene mainWindowScene = new Scene(mainWindowParent);
        Stage adminStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        adminStage.setScene(mainWindowScene);
        adminStage.setTitle("Admin controls");
        adminStage.show();
    }

    public void handleBtnExit (ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }


}
