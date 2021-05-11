package arlaScreens.gui.controller;

import arlaScreens.be.Department;
import arlaScreens.gui.controller.admin.AdminController;
import arlaScreens.gui.controller.dep.DepController;
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
        Department dep = loginModel.getUserLogin(txtFieldUsername.getText().trim(), txtFieldPassword.getText().trim());
        Department admin = loginModel.getAdminLogin(txtFieldUsername.getText().trim(), txtFieldPassword.getText().trim());
        if (dep != null){
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/arlaScreens/gui/view/dep/Dep1.fxml").openStream());
            DepController depController = (DepController)loader.getController();
            depController.getDep(dep);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/arlaScreens/gui/view/admin/DepAdmin.fxml").openStream());
            AdminController adminController = loader.getController();
            adminController.getAdmin(admin);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
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

    public void handleBtnExit (ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
}
