package arlaScreens.gui.controller;

import arlaScreens.be.Department;
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
        if (loginModel.checkAdminLogin(txtFieldUsername.getText().trim(), txtFieldPassword.getText().trim())) {
            Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/view/DepLogistics.fxml"));
            Scene mainWindowScene = new Scene(mainWindowParent);
            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            adminStage.setScene(mainWindowScene);
            adminStage.setTitle("Admin Controls");
            adminStage.show();
        } else
            handleDepLogin(event);
        System.out.printf("Dep Login successful");
    }

    public void handleAdminControls(ActionEvent actionEvent) throws IOException {
        Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/view/admin/AdminControls.fxml"));
        Scene mainWindowScene = new Scene(mainWindowParent);
        Stage adminStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        adminStage.setScene(mainWindowScene);
        adminStage.setTitle("Admin controls");
        adminStage.show();
    }

    public void handleDepLogin(ActionEvent event) throws IOException, SQLException {
        String username = txtFieldUsername.getText().trim();
        String password = txtFieldPassword.getText().trim();

        Department loggedInDep = loginModel.depLogin(username, password);

        switch (loggedInDep.getId()) {
            case 2:
                Parent MainParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/view/DepLogistics.fxml"));
                Scene MainScene = new Scene(MainParent);
                Stage logStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                logStage.setScene(MainScene);
                logStage.show();
                break;
        }
    }

    public void handleBtnExit (ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }


}
