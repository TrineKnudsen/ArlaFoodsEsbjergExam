package arlaScreens.gui.controller;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.bll.util.UserError;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private LoginModel loginModel;

    private final String ERROR_HEADER = "Error occurred!";
    @FXML
    public PasswordField txtFieldPassword;
    @FXML
    public TextField txtFieldUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginModel = new LoginModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Metoden til login knappen. Metoden henter et login fra databasen ud fra det input brugeren har indtastet i txtFieldUsername og txtFieldPassword.
     * @param event
     * @throws SQLException
     */
    public void handleBtnLogin(ActionEvent event) throws SQLException {
        Department dep = loginModel.getUserLogin(txtFieldUsername.getText().trim(), txtFieldPassword.getText().trim());
        Admin admin = loginModel.getAdminLogin(txtFieldUsername.getText().trim(), txtFieldPassword.getText().trim());
        try {
        if (dep != null){
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/arlaScreens/gui/view/dep/Dep1.fxml").openStream());
            DepController depController = loader.getController();
            depController.getDep(dep);
            Scene scene = new Scene(root);
            primaryStage.setTitle(dep.getName());
            primaryStage.setScene(scene);
            primaryStage.setHeight(900);
            primaryStage.setMaxHeight(1080);
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
        }}
        catch(Exception e ) {
            UserError.displayError(ERROR_HEADER, "Wrong username or password");
        }
    }

    public void handleBtnExit (ActionEvent actionEvent){
        Platform.exit();
        System.exit(0);
    }
}
