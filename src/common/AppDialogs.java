package common;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppDialogs {
    public static void viewDialog(String title, String content, Alert.AlertType alertType, String titleIconURL, String buttonText) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/assets/icons/error.png"));
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(buttonText);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void viewChoiceDialog(List<String> list, String defaultChoice, String header, String comboText) {
        List<String> choices = list;
        ChoiceDialog<String> dialog = new ChoiceDialog<>(defaultChoice, choices);
        dialog.setHeaderText(header);
        ((Stage) dialog.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/assets/icons/icon.png"));
        dialog.setContentText(comboText);
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
//            System.out.println("Your choice: " + result.get());
        }
//        result.ifPresent(letter -> System.out.println("Your choice: " + letter));
    }
}
