package arlaScreens.gui.controller.admin;

import arlaScreens.be.Department;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CFGController implements Initializable {

    Department department;

    @FXML
    private TextField columnField;
    @FXML
    private TextField rowField;
    @FXML
    private TextField fileNameField;
    @FXML
    private Label deplbl;
    @FXML
    private Button chosenFile;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void getDepartment(Department dep){
        deplbl.setText(dep.getName());
    }
}
