package controllers.employee_management.attendance_details;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class YearWiseAttendanceController implements Initializable {
    private ResourceBundle bundle;
    private Locale locale;

    @FXML
    Label id, name, occupation, mobile_number, select_year, month_january, month_february, month_march, month_april, month_may, month_june, month_july,
            month_august, month_september, month_october, month_november, month_december;

    @FXML
    CheckBox current_year_check;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
        } catch (Exception e) {
            new Log("YearWiseAttendanceController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        id.setText(bundle.getString("employee_id"));
        name.setText(bundle.getString("employee_name"));
        occupation.setText(bundle.getString("employee_occupation"));
        mobile_number.setText(bundle.getString("employee_mobile_number"));
        current_year_check.setText(bundle.getString("current_year_check"));
        select_year.setText(bundle.getString("select_year"));
        month_january.setText(bundle.getString("month_january"));
        month_february.setText(bundle.getString("month_february"));
        month_march.setText(bundle.getString("month_march"));
        month_april.setText(bundle.getString("month_april"));
        month_may.setText(bundle.getString("month_may"));
        month_june.setText(bundle.getString("month_june"));
        month_july.setText(bundle.getString("month_july"));
        month_august.setText(bundle.getString("month_august"));
        month_september.setText(bundle.getString("month_september"));
        month_october.setText(bundle.getString("month_october"));
        month_november.setText(bundle.getString("month_november"));
        month_december.setText(bundle.getString("month_december"));
    }
}
