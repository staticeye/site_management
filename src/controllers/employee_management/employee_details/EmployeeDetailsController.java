package controllers.employee_management;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class EmployeeDetailsController implements Initializable {
    @FXML
    private Tab btn_view_employee, btn_add_employee, btn_update_delete_employee;

    @FXML
    private GridPane sub_gridpane, gridpane;
    private ResourceBundle bundle;
    private Locale locale;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
        } catch (Exception e) {
            new Log("EmployeeDetailsController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_view_employee.setText(bundle.getString("btn_view_employee"));
        btn_add_employee.setText(bundle.getString("btn_add_employee"));
        btn_update_delete_employee.setText(bundle.getString("btn_update_delete_employee"));
    }

}
