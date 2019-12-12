package controllers.employee_management.attendance_details;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LowestAttendedController implements Initializable {
    private ResourceBundle bundle;
    private Locale locale;

    @FXML
    Label id, name, occupation, mobile_number;
    @FXML
    TableView tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
        } catch (Exception e) {
            new Log("LowestAttendedController - initialize : ", e).error();
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

        tableView.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
    }

}
