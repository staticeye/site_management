package controllers.employee_management.attendance_details;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AttendanceDetailsController implements Initializable {
    @FXML
    private Tab btn_add_attendance, btn_view_attendance, btn_update_delete_attendance;
    public AnchorPane content_pane, root_pane;
    private ResourceBundle bundle;
    private Locale locale;

    //View attendance
    @FXML
    private Label view_filter;
    @FXML
    private Button view_day_wise, view_month_wise, view_year_wise, view_highest_attended, view_lowest_attended, view_highest_non_attended, view_lowest_non_attended;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI(AppURL.DAY_WISE_ATTENDANCE_MENU);
        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
        } catch (Exception e) {
            new Log("AttendanceDetailsController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    @FXML
    public void didClick_dayWiseAttendance() throws Exception {
        loadUI(AppURL.DAY_WISE_ATTENDANCE_MENU);
    }

    @FXML
    public void didClick_monthWiseAttendance() throws Exception {
        loadUI(AppURL.MONTH_WISE_ATTENDANCE_MENU);
    }

    @FXML
    public void didClick_yearWiseAttendance() throws Exception {
        loadUI(AppURL.YEAR_WISE_ATTENDANCE_MENU);
    }

    @FXML
    public void didClick_highestAttended() throws Exception {
        loadUI(AppURL.HIGHEST_ATTENDED_MENU);
    }

    @FXML
    public void didClick_highestNonAttended() throws Exception {
        loadUI(AppURL.HIGHEST_NONATTENDED_MENU);
    }

    @FXML
    public void didClick_lowestAttended() throws Exception {
        loadUI(AppURL.LOWEST_ATTENDED_MENU);
    }

    @FXML
    public void didClick_lowestNonAttended() throws Exception {
        loadUI(AppURL.LOWEST_NONATTENDED_MENU);
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_add_attendance.setText(bundle.getString("btn_add_attendance"));
        btn_view_attendance.setText(bundle.getString("btn_view_attendance"));
        btn_update_delete_attendance.setText(bundle.getString("btn_update_delete_attendance"));

        view_filter.setText(bundle.getString("view_filter"));
        view_day_wise.setText(bundle.getString("view_day_wise"));
        view_month_wise.setText(bundle.getString("view_month_wise"));
        view_year_wise.setText(bundle.getString("view_year_wise"));
        view_highest_attended.setText(bundle.getString("view_highest_attended"));
        view_lowest_attended.setText(bundle.getString("view_lowest_attended"));
        view_highest_non_attended.setText(bundle.getString("view_highest_non_attended"));
        view_lowest_non_attended.setText(bundle.getString("view_lowest_non_attended"));
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
            new Log("AttendacneDetailsController - loadUI : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

    }

}
