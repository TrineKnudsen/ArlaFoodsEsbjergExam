package arlaScreens.gui.controller.admin;

import arlaScreens.be.Department;
import arlaScreens.gui.model.AdminModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CFGController implements Initializable {

    AdminModel adminModel;
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
        try {
            adminModel = new AdminModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public CFGController(Department department){
        this.department = department;
    }

    public Department getDepartment(){
        return department;
    }

    public void handleSave(ActionEvent event) throws SQLException {
        int column = Integer.parseInt(columnField.getText().trim());
        int row = Integer.parseInt(rowField.getText().trim());
        String name = fileNameField.getText().trim();


        adminModel.createCFG(getDepartment().getId(), row, column, name, chosenFile.getText());
    }

    public void handleOpenFile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("pictures/" )); //Sets the directory to the desktop
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Pictures",  "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            chosenFile.setText(String.valueOf(selectedFile));
        }
    }
}
