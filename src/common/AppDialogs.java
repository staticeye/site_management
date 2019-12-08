package common;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppDialogs {
    public static void viewDialog(String title, String content, Alert.AlertType alertType, String titleIconURL){
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(titleIconURL));
        alert.setContentText(content);
        alert.showAndWait();
    }
}
