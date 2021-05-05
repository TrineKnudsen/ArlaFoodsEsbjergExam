package arlaScreens.gui.Controller;

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
import arlaScreens.gui.Model.LoginModel;

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
            Parent mainWindowParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepAdmin.fxml"));
            Scene mainWindowScene = new Scene(mainWindowParent);
            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            adminStage.setScene(mainWindowScene);
            adminStage.setTitle("Admin controls");
            adminStage.show();
        } else handleDepLogin(event);
        System.out.printf("Dep Login succesfull");
    }


//        String username = txtFieldUsername.getText().trim();
//        String password = txtFieldPassword.getText().trim();
//        boolean validLogin = loginModel.checkAdminLogin(username, password);
//
//        if (validLogin) {
//            adminView(event);
//        } else System.out.println("Username or password incorrect"); }

//    public void adminView(ActionEvent event) {
//        try{
//            Parent MainParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepAdmin.fxml"));
//            Scene MainScene = new Scene(MainParent);
//            Stage addMovieStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            addMovieStage.setScene(MainScene);
//            addMovieStage.show();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

        public void handleDepLogin(ActionEvent event) throws IOException, SQLException {
            String username = txtFieldUsername.getText().trim();
            String password = txtFieldPassword.getText().trim();

            Department loggedInDep = loginModel.depLogin(username, password);

            switch (loggedInDep.getId()) {
                case 2:
                    Parent MainParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepLogistics.fxml"));
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
