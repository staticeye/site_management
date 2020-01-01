package helpers;

import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Component {

    public static String generateUUID(String initalValue){
        UUID uuid = UUID.randomUUID();
        return initalValue + "-" +uuid.toString();
    }

    public static final LocalDate setDatePicker (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    public static final LocalDate setDatePickerCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(simpleDateFormat.format(new Date()), formatter);
        return localDate;
    }

    public static void textFieldAcceptOnlyLetters(String value, TextField textField){
            if (!value.matches("\\sa-zA-Z*")) {
                textField.setText(value.replaceAll("[^\\sa-zA-Z]", ""));
            }
    }

    public static void textFieldAcceptOnlyNumbers(String value, TextField textField){
        if (!value.matches("\\d*")) {
            textField.setText(value.replaceAll("[^\\d]", ""));
        }
    }

    public static void applyTextFieldFormatterOnlyLetters(List<TextField> textFields){
        for(TextField textField : textFields){
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                Component.textFieldAcceptOnlyLetters(newValue, textField);
            });

        }
    }

    public static void applyTextFieldFormatterOnlyNumbers(List<TextField> textFields){
        for(TextField textField : textFields){
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                Component.textFieldAcceptOnlyNumbers(newValue, textField);
            });

        }
    }

    public static void clearTextFields(List<TextField> textFields){
        for(TextField textField : textFields){
            textField.setText(null);
        }
    }
}
