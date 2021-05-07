package arlaScreens.gui.controller;

import arlaScreens.be.Department;
import arlaScreens.gui.model.LoginModel;
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

        Department user = loginModel.getUserLogin(username, password);
        Department admin = loginModel.getAdminLogin(username, password);

        if (user != null && user.getId() == 2) {
            getScreen(event, "/arlaScreens/gui/view/dep/Dep1.fxml", user.getName());
        } else if (user != null && user.getId() == 16) {
            getScreen(event, "/arlaScreens/gui/view/dep/Dep16.fxml", user.getName());
        } else if (user != null && user.getId() == 18) {
            getScreen(event, "/arlaScreens/gui/view/dep/Dep18.fxml", user.getName());
        } else if (admin != null && admin.getId() == 16) {
            getScreen(event, "/arlaScreens/gui/view/admin/DepAdmin.fxml", "Admin");
        }
    }

    public void getScreen(ActionEvent event, String view, String title) throws IOException {
        Parent mainwindow = FXMLLoader.load(getClass().getResource(view));
        Scene mainWinsowScene = new Scene(mainwindow);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(mainWinsowScene);
        stage.setTitle(title);
        stage.show();
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
