package arlaScreens.gui.controller.admin;

import arlaScreens.be.Admin;
import arlaScreens.be.Department;
import arlaScreens.be.ScreenCFG;
import arlaScreens.bll.util.UserError;
import arlaScreens.gui.model.DepartmentModel;
import arlaScreens.gui.controller.util.DataType;
import arlaScreens.gui.controller.util.IDataType;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private final String ERROR_HEADER = "Error occurred!";
    private DepartmentModel departmentModel;
    private ObservableList<Department> allDep;
    List<ScreenCFG> screenCFGList;
    IDataType iDataType;

    @FXML
    private JFXTextField nameField;
    @FXML
    private AnchorPane anchorCFG;
    @FXML
    private TableView<Department> deplst;
    @FXML
    private TableColumn<Department, String> nameColumn;
    @FXML
    private AnchorPane anchor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void getAdmin(Admin admin) {
        admin.getId();
        try {
            departmentModel = new DepartmentModel();
            allDep = departmentModel.getAllDep();
            deplst.setItems(allDep);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            screenCFGList = new ArrayList<>();
            iDataType = new DataType();

        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Metode til at ??bne en ny sk??rm
     * @param url
     * @param windowName
     */
    private void openScreen(String url, String windowName) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(url));
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle(windowName);
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            UserError.displayError(ERROR_HEADER, "Can't take you to this window");
        }
    }

    /**
     * Metode der viser den valgte sk??rmkonfiguration
     * @param event
     */

    @FXML
    private void getSelectedCFG(MouseEvent event) {
        try {
            screenCFGList.addAll(departmentModel.getScreenCFGS(deplst.getSelectionModel().getSelectedItem().getId()));
            Label typelbl;
            AnchorPane anchorPane = null;
            GridPane gridPane = new GridPane();
            if (!gridPane.getChildren().isEmpty() && !anchorPane.getChildren().isEmpty()) {
                gridPane.getChildren().clear();
                anchorCFG.getChildren().clear();
            } else {
                for (ScreenCFG screenCFG : screenCFGList) {
                    anchorPane = new AnchorPane();
                    typelbl = new Label();
                    String type = screenCFG.getType();
                    typelbl.setText(type);
                    anchorPane.getChildren().add(typelbl);
                    gridPane.getChildren().add(anchorPane);
                    GridPane.setConstraints(anchorPane, screenCFG.getColIndex(), screenCFG.getRowIndex());
                }
                anchorCFG.getChildren().add(gridPane);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            UserError.displayError(ERROR_HEADER, "Couldn't load configuration");
        }
    }

    /**
     * Metode der benyttes ved tryk p?? knappen til at lave en ny afdeling
     * @param actionEvent
     */

    @FXML
    private void handleCreateDep(ActionEvent actionEvent) {
        openScreen("/arlaScreens/gui/view/admin/NewObject.fxml", "Add new department or admin");
    }

    /**
     * Metode der benyttes ved tryk p?? knappen til at opdatere en afdeling
     * @param actionEvent
     */
    @FXML
    private void handleUpdateDepartment(ActionEvent actionEvent) {
        try {
            int chosenDep = deplst.getSelectionModel().getSelectedItem().getId();
            String updatedDep = nameField.getText().trim();

            if (updatedDep != null) {
                departmentModel.updateDep(chosenDep, updatedDep);
            }
        } catch (Exception ex) {
            UserError.displayError(ERROR_HEADER, "Choose department to edit");
        }
    }

    /**
     * Metode der benyttes ved tryk p?? knappen til at slette en department
     * @param actionEvent
     */

    @FXML
    private void handleDeleteDepartment(ActionEvent actionEvent) {
        Department depToDelete = deplst.getSelectionModel().getSelectedItem();
        try {
            if (depToDelete != null) {
                departmentModel.deleteDep(depToDelete);
                deplst.getItems().remove(depToDelete);
            } else {
                String message = "Choose the department you wish to delete";
                UserError.displayError(ERROR_HEADER, message);
            }
        } catch (Exception e) {
            UserError.displayError(ERROR_HEADER, e.getMessage());
        }
    }

    /**
     * Metode der benyttes ved tryk p?? at redigere en sk??rmkonfiguration
     * @param event
     */
    @FXML
    private void handleOpenCFG(ActionEvent event) {
        Department chosenDep = deplst.getSelectionModel().getSelectedItem();
        if (chosenDep != null) {
            openScreen("/arlaScreens/gui/view/admin/EditCFG.fxml", "Screen Configuration for " +chosenDep.getName());
        } else UserError.displayError(ERROR_HEADER, "Choose department to add screen configuration to");
    }

    /**
     * Metode der benyttes ved tryk p?? log ud knapper
     * @param actionEvent
     */
    @FXML
    private void handleLogout(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/arlaScreens/gui/view/Main.fxml"));
            Parent mainWindowParent = loader.load();
            Scene mainScene = new Scene(mainWindowParent);
            Stage window = (Stage) anchor.getScene().getWindow();
            window.setScene(mainScene);
            window.setTitle("Arla Foods-Esbjerg");
            window.show();
        } catch (Exception ex) {
            UserError.displayError(ERROR_HEADER, "Wasn't able to log out");
        }
    }

    /**
     * Metode der benyttes ved tryk p?? exit
     * @param actionEvent
     */
    @FXML
    private void handleExit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}