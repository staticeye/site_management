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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class EmployeeManagementSelectionController implements Initializable {
    @FXML
    private Button btn_attendance, btn_employee, btn_back, btn_payment;
    @FXML
    private AnchorPane content_pane, root_pane;

    private ResourceBundle bundle;
    private Locale locale;
    Stage primaryStage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadUI(AppURL.ADD_ATTENDANCE_PATH);

        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
        } catch (Exception e) {
            new Log("EmployeeManagementSelectionController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    @FXML
    public void didClick_addAttendance() {
        loadUI(AppURL.ADD_ATTENDANCE_PATH);
    }

    @FXML
    public void didClick_modifyEmployees() {
        loadUI(AppURL.MODIFY_EMPLOYEES);
    }

    @FXML
    public void didClick_viewAttendance() {
        loadUI(AppURL.VIEW_ATTENDANCE);
    }

    @FXML
    public void didClick_btn_back(ActionEvent event) {
        navigateToPreviousStage(AppURL.WELCOME_VIEW);
    }

    @FXML
    public void didClick_btn_paymentDetails(){
        loadUI(AppURL.PAYEMENT_DETAILS);
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_attendance.setText(bundle.getString("btn_attendance"));
        btn_employee.setText(bundle.getString("btn_employee"));
        btn_back.setText(bundle.getString("btn_back"));
        btn_payment.setText(bundle.getString("btn_payment"));
    }

    private void navigateToPreviousStage(String scenePath) {
        try {
            primaryStage = (Stage) btn_attendance.getScene().getWindow();
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

    private void loadUI(String UiName) {
        try {
            content_pane = FXMLLoader.load(getClass().getResource(UiName));

            //Content not taking the whole screen size issue solved here,
            Node node = content_pane;
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);

            root_pane.getChildren().setAll(node);
        } catch (Exception e) {
            new Log("EmployeeManagementSelectionController - loadUI : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

    }
}
