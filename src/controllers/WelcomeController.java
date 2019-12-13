package controllers;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

public class WelcomeController implements Initializable {

    @FXML
    private Button btn_site_management, btn_employee_management;
    private ResourceBundle bundle;
    private Locale locale;
    Preferences pref;
    Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pref = Preferences.userNodeForPackage(Main.class);
        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
                loadAlertLang("sinhala");
                StaticAttributes.user_language = "sinhala";
            } else {
                loadLang("english");
                loadAlertLang("english");
                StaticAttributes.user_language = "english";
            }
        } catch (Exception e) {
            new Log("WelcomeController - navigateToNextStage : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    @FXML
    public void didClick_btn_sinhala(ActionEvent event) throws Exception {
        pref.putBoolean(StaticAttributes.isSinhalaEnableKey, true);
        StaticAttributes.isSinhalaEnable = true;
        loadLang("sinhala");
        loadAlertLang("sinhala");
        StaticAttributes.user_language = "sinhala";
    }

    @FXML
    public void didClick_btn_english(ActionEvent event) throws Exception {
        pref.putBoolean(StaticAttributes.isSinhalaEnableKey, false);
        StaticAttributes.isSinhalaEnable = false;
        loadLang("english");
        loadAlertLang("english");
        StaticAttributes.user_language = "english";
    }

    @FXML
    public void didClick_btn_SiteManagement(ActionEvent event) {
        navigateToNextStage(AppURL.SITES_MANAGEMENT_SELECTION);
    }

    @FXML
    public void didClick_btn_EmployeeManagement(ActionEvent event) {
        navigateToNextStage(AppURL.EMPLOYEE_MANAGEMENT_SELECTION);
    }

    private void navigateToNextStage(String scenePath) {
        try {
            primaryStage = (Stage) btn_site_management.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenePath));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            StaticAttributes.setStage(stage, root1, primaryStage.getWidth(), primaryStage.getHeight());
            primaryStage.close();
        } catch (Exception e) {
            new Log("WelcomeController - navigateToNextStage : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_site_management.setText(bundle.getString("btn_site_management"));
        btn_employee_management.setText(bundle.getString("btn_employee_management"));
    }

    private void loadAlertLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        AppStrings.SOMETHING_WRONG = bundle.getString("SOMETHING_WRONG");
        AppStrings.ERROR = bundle.getString("ERROR");
        AppStrings.ALERT_BUTTON = bundle.getString("ALERT_BUTTON");
        AppStrings.CONFIRMATION = bundle.getString("CONFIRMATION");
    }


}
