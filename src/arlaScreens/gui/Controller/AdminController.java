package arlaScreens.gui.Controller;

import arlaScreens.be.Department;
import arlaScreens.gui.Model.Model;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private Model model;
    ObservableList<Department> allDep;

    @FXML
    JFXListView<Department> deplst;
    @FXML
    JFXTextField nameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            model = new Model();

            allDep = model.getAllDep();
            deplst.getItems().addAll(allDep);
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void updateDep(ActionEvent event) throws SQLException {
        allDep.clear();
        Department chosenDep = deplst.getSelectionModel().getSelectedItem();
        String updatedDep = nameField.getText().trim();

        model.updateDep(chosenDep, updatedDep);
        deplst.getItems().addAll(allDep);
        deplst.setItems(allDep);
    }
}
