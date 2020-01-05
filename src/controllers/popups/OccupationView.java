package controllers.popups;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Occupation;

import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class OccupationView implements Initializable {

    @FXML
    private Label occupation_id_label, english_name_label, sinhala_name_label;
    @FXML
    private Button insert_button, update_button, delete_button, clear_button;
    @FXML
    private TextField occupation_id_text, occupation_sinhala_text, occupation_english_text;

    private ResourceBundle bundle;
    private Locale locale;
    Occupation occupation = new Occupation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
            init();
        } catch (Exception e) {
            new Log("OccupationView - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }


    private void closeStage(ActionEvent event) {
        Node  source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        occupation_id_label.setText(bundle.getString("occupation_id"));
        english_name_label.setText(bundle.getString("english_name"));
        sinhala_name_label.setText(bundle.getString("sinhala_name"));
        insert_button.setText(bundle.getString("insert"));
        update_button.setText(bundle.getString("update"));
        delete_button.setText(bundle.getString("delete"));
        clear_button.setText(bundle.getString("btn_clear"));
    }

    public void init() throws SQLException {
        occupation.generateId();
        occupation_id_text.setText(Integer.toString(occupation.getId()));
        occupation_english_text.setText("");
        occupation_sinhala_text.setText("");
    }

    public void did_click_add_occupation() throws SQLException {
        String englishName = occupation_english_text.getText();
        String sinhalaName = occupation_sinhala_text.getText();
        if (englishName.equals("") && sinhalaName.equals("")){
            AppDialogs.viewDialog("Error", AppStrings.EMPTY_FIELDS_OCCUPATION, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }else{
            occupation.setOccupation_english(englishName);
            occupation.setOccupation_sinhala(sinhalaName);
            occupation.insertOccupation();

            AppDialogs.viewDialog(AppStrings.SUCCESS, AppStrings.DATA_INSERT_SUCCESS, Alert.AlertType.INFORMATION, AppURL.SUCCESS_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
        init();
    }


}