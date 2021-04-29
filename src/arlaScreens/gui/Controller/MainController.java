package arlaScreens.gui.Controller;

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
import arlaScreens.gui.Model.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    LoginModel loginModel;

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField userField1;
    @FXML
    private PasswordField passField1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loginModel = new LoginModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    public void handleLogin(ActionEvent event) throws SQLException {
        String username = userField.getText().trim();
        String password = passField.getText().trim();
        boolean validLogin = loginModel.checkAdminLogin(username, password);

        if (validLogin == true) {
            adminView(event);
        } else System.out.println("Not valid login");
    }

    public void adminView(ActionEvent event) {
        try {
            Parent MainParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepAdmin.fxml"));
            Scene MainScene = new Scene(MainParent);
            Stage addMovieStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addMovieStage.setScene(MainScene);
            addMovieStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handleDepLogin(ActionEvent event) throws IOException, SQLException {
        String username = userField1.getText().trim();
        String password = passField1.getText().trim();

        int depId = loginModel.depLogin(username, password).getDepId();

        switch (depId) {
            case 2:
                Parent MainParent = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepLogistics.fxml"));
                Scene MainScene = new Scene(MainParent);
                Stage logStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                logStage.setScene(MainScene);
                logStage.show();
                break;
            case 3:
                Parent MainParent2 = FXMLLoader.load(getClass().getResource("/arlaScreens/gui/View/DepSales.fxml"));
                Scene MainScene2 = new Scene(MainParent2);
                Stage salesStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                salesStage.setScene(MainScene2);
                salesStage.show();
                break;
        }
    }

}