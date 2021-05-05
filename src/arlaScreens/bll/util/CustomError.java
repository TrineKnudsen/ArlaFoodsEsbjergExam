package arlaScreens.bll.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CustomError {

    public void error(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR, text, ButtonType.OK);
        alert.showAndWait();
    }

    public void info(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, text, ButtonType.OK);
        alert.show();
    }

}
