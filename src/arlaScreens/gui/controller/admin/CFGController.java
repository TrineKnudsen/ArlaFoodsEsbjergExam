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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CFGController implements Initializable {

    Department dep;
    AdminModel adminModel;

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
    @FXML
    private Label lblid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setDepartment(Department dep){
        try {
            this.dep = dep;
            adminModel = new AdminModel();
            deplbl.setText(dep.getName());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Department getDepartment(){
        return dep;
    }

    public void handleChoosefile(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src/files/" )); //Sets the directory to the desktop

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files",  "*.png", "*.jpg"));
    File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                chosenFile.setText("\\files\\" + selectedFile.getName());
            }
        lblid.setText(String.valueOf(getDepartment().getId()));
    }

    public void handleSave(ActionEvent event) throws SQLException {
        String selectedFile = chosenFile.getText().trim();
        int row = Integer.parseInt(rowField.getText().trim());
        int column = Integer.parseInt(columnField.getText().trim());
        String url = chosenFile.getText().trim();
        String filetype = fileNameField.getText().trim();

        if (selectedFile != null){
            adminModel.createCFG(dep.getId(), row, column, url, filetype);
        }
    }
}
