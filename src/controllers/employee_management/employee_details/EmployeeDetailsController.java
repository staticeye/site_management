package controllers.employee_management.employee_details;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import service.OccupationsService;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;

public class EmployeeDetailsController implements Initializable {
    //Employee details
    @FXML
    private Tab btn_view_employee, btn_add_employee, btn_update_delete_employee;

    //Add employee details
    @FXML
    private Label employee_id_label, employee_name_label, employee_mobile_label, join_date_label, occupation_label, daily_salary_label, ot_rate_label, annual_bonus_label;
    @FXML
    Button btn_add, btn_clear;
    @FXML
    CheckBox join_date_today_check;
    @FXML
    DatePicker datepicker;
    @FXML
    ComboBox occupations_combo_update, occupations_combo_add;

    //View employee details
    @FXML
    private TableView sub_table;
    @FXML
    private Label view_employee_id_label, view_employee_name_label, view_employee_mobile_label, view_join_date_label, view_occupation_label, view_daily_salary_label, view_ot_rate_label, view_annual_bonus_label;

    //Update/Delete employee details
    @FXML
    private Button btn_update, btn_delete;
    @FXML
    private TableView update_table;
    @FXML
    private GridPane update_sub_gridpane;
    @FXML
    private Label update_employee_id_label, update_employee_name_label, update_employee_mobile_label, update_join_date_label, update_occupation_label, update_daily_salary_label, update_ot_rate_label, update_annual_bonus_label;
    @FXML
    CheckBox update_join_date_today_check;

    @FXML
    private GridPane sub_gridpane;
    private ResourceBundle bundle;
    private Locale locale;

    OccupationsService occupationsService = new OccupationsService();
    ObservableList<String> options =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (occupationsService.isDatabaseConnected()) {
                ResultSet resultSet = occupationsService.getAllOccupations();
                while (resultSet.next()) {
                    options.add(resultSet.getString(1));
                }
                occupations_combo_update.setItems(options);
                occupations_combo_add.setItems(options);

            } else {
                AppDialogs.viewDialog("Error", AppStrings.DATABASE_CONNECTION_ERROR, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
                throw new Exception(AppStrings.DATABASE_CONNECTION_ERROR);
            }
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
            setGridPane();
        } catch (Exception e) {
            new Log("EmployeeDetailsController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    private void setGridPane() {
        GridPane.setColumnSpan(sub_gridpane, 5);
        GridPane.setColumnSpan(sub_table, 3);
        GridPane.setRowSpan(sub_table, 8);
        GridPane.setColumnSpan(update_table, 3);
        GridPane.setRowSpan(update_table, 8);
        GridPane.setColumnSpan(update_sub_gridpane, 5);
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        //Employee details - root
        btn_view_employee.setText(bundle.getString("btn_view_employee"));
        btn_add_employee.setText(bundle.getString("btn_add_employee"));
        btn_update_delete_employee.setText(bundle.getString("btn_update_delete_employee"));

        //Add employee
        employee_id_label.setText(bundle.getString("employee_id"));
        employee_name_label.setText(bundle.getString("employee_name"));
        employee_mobile_label.setText(bundle.getString("employee_mobile_number"));
        join_date_label.setText(bundle.getString("emoployee_join_date"));
        occupation_label.setText(bundle.getString("employee_occupation"));
        daily_salary_label.setText(bundle.getString("employee_daily_salary"));
        ot_rate_label.setText(bundle.getString("employee_ot_rate"));
        annual_bonus_label.setText(bundle.getString("employee_bonus"));
        btn_add.setText(bundle.getString("btn_add"));
        btn_clear.setText(bundle.getString("btn_clear"));
        join_date_today_check.setText(bundle.getString("join_date_today_check"));

        //View employee
        view_employee_id_label.setText(bundle.getString("employee_id"));
        view_employee_name_label.setText(bundle.getString("employee_name"));
        view_employee_mobile_label.setText(bundle.getString("employee_mobile_number"));
        view_join_date_label.setText(bundle.getString("emoployee_join_date"));
        view_occupation_label.setText(bundle.getString("employee_occupation"));
        view_daily_salary_label.setText(bundle.getString("employee_daily_salary"));
        view_ot_rate_label.setText(bundle.getString("employee_ot_rate"));
        view_annual_bonus_label.setText(bundle.getString("employee_bonus"));

        //Update delete employee
        update_employee_id_label.setText(bundle.getString("employee_id"));
        update_employee_name_label.setText(bundle.getString("employee_name"));
        update_employee_mobile_label.setText(bundle.getString("employee_mobile_number"));
        update_join_date_label.setText(bundle.getString("emoployee_join_date"));
        update_occupation_label.setText(bundle.getString("employee_occupation"));
        update_daily_salary_label.setText(bundle.getString("employee_daily_salary"));
        update_ot_rate_label.setText(bundle.getString("employee_ot_rate"));
        update_annual_bonus_label.setText(bundle.getString("employee_bonus"));
        btn_update.setText(bundle.getString("btn_update"));
        btn_delete.setText(bundle.getString("btn_delete"));
        update_join_date_today_check.setText(bundle.getString("join_date_today_check"));

        sub_table.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
        update_table.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
    }

}
