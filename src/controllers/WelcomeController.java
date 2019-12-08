package controllers;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

public class WelcomeController implements Initializable {

    @FXML
    private Button btn_site_management, btn_employee_management;
    private Label label;
    private ResourceBundle bundle;
    private Locale locale;
    Preferences pref;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pref = Preferences.userNodeForPackage(Main.class);
        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON);
        }
    }

    @FXML
    public void didClick_btn_sinhala(ActionEvent event) throws Exception {
        pref.putBoolean(StaticAttributes.isSinhalaEnableKey, true);
        loadLang("sinhala");
    }

    @FXML
    public void didClick_btn_english(ActionEvent event) throws Exception {
        pref.putBoolean(StaticAttributes.isSinhalaEnableKey, false);
        loadLang("english");
    }

    @FXML
    public void didClick_btn_SiteManagement(ActionEvent event) {

    }

    @FXML
    public void didClick_btn_EmployeeManagement(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scenes/employee_management_selection.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_site_management.setText(bundle.getString("btn_site_management"));
        btn_employee_management.setText(bundle.getString("btn_employee_management"));
    }


}
