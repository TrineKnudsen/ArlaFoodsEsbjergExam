package arlaScreens.gui.controller.admin;

import arlaScreens.bll.ArlaFoodsManager;
import arlaScreens.bll.IArlaFoodsLogicFacade;
import arlaScreens.bll.util.CustomError;
import arlaScreens.dal.dao.AdminDAO;
import arlaScreens.dal.dao.LoginDAO;
import arlaScreens.gui.model.AdminModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class NewAdminController {
    public TextField txtFieldUsername;
    public TextField txtFieldPass1;
    public TextField txtFieldPass2;
    private AdminModel adminModel;
    private CustomError error = new CustomError();

    public NewAdminController() throws IOException {
        adminModel = new AdminModel();
    }



    public void handleAddNewAdmin(ActionEvent actionEvent) throws SQLException {
        if(txtFieldPass1.getText().trim().equalsIgnoreCase(txtFieldPass2.getText().trim())) {
            adminModel.createAdmin(txtFieldUsername.getText(), txtFieldPass1.getText());
            error.info("Admin successfully added to database");
        }
        else {error.error("Passwords don't match");
        }
    }

    public void handleBack(ActionEvent actionEvent) {
    }
}
