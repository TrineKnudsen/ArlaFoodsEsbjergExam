package arlaScreens.bll.util;

import javafx.scene.control.Alert;

public class UserError {

    /**
     * Metode der benyttes til fejlmeddelelser i programmet
     * @param header
     * @param error
     */

    public static void displayError (String header, String error) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setTitle(header);
        alert.setContentText(error);
        alert.showAndWait();
    }
}
