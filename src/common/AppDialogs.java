package common;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class AppDialogs {
    public static void viewDialog(String title, String content, Alert.AlertType alertType, String titleIconURL, String buttonText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(titleIconURL));
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(buttonText);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void viewChoiceDialog(List<String> list, String defaultChoice, String title, String header, String comboText, Consumer<String> func) {
        List<String> choices = list;
        ChoiceDialog<String> dialog = new ChoiceDialog<>(defaultChoice, choices);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image(AppURL.ERROR_ALERT_ICON));
        dialog.setContentText(comboText);
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            func.accept(result.get());
        }
    }
}
