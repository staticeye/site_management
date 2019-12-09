package controllers.employee_management;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Main;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class EmployeeManagementSelectionController implements Initializable {
    @FXML
    private Button btn_add_attendance, btn_view_attendance, btn_modify_employees, btn_back;
    @FXML
    private AnchorPane content_pane, root_pane;
    private ResourceBundle bundle;
    private Locale locale;
    Preferences pref;
    Stage primaryStage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pref = Preferences.userNodeForPackage(Main.class);
        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
        } catch (Exception e) {
            new Log("WelcomeController - navigateToNextStage : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    @FXML
    public void didClick_addAttendance(){
        loadUI("/scenes/employee_management/add_attendance.fxml");
    }

    @FXML
    public void didClick_modifyEmployees(){
        loadUI("/scenes/employee_management/modify_employees.fxml");
    }

    @FXML
    public void didClick_viewAttendance(){
        loadUI("/scenes/employee_management/view_attendance.fxml");
    }

    @FXML
    public void didClick_btn_back(ActionEvent event){
        navigateToPreviousStage("/scenes/welcome_view.fxml");
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_add_attendance.setText(bundle.getString("btn_add_attendance"));
        btn_view_attendance.setText(bundle.getString("btn_view_attendance"));
        btn_modify_employees.setText(bundle.getString("btn_modify_employees"));
        btn_back.setText(bundle.getString("btn_back"));
    }

    private void navigateToPreviousStage(String scenePath) {
        try {
            primaryStage = (Stage) btn_add_attendance.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenePath));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            StaticAttributes.setStage(stage, root1, primaryStage.getWidth(), primaryStage.getHeight());
            primaryStage.close();
        } catch (Exception e) {
            new Log("EmployeeManagementSelection - navigateToPreviousStage : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    private void loadUI(String UiName){

        try{
            content_pane = FXMLLoader.load(getClass().getResource(UiName));
            root_pane.getChildren().setAll(content_pane);
        }catch (Exception e){
            new Log("EmployeeManagementSelectionController - loadUI : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

    }
}
