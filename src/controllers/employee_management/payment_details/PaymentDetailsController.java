package controllers.employee_management.payment_details;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class PaymentDetailsController implements Initializable {
    @FXML
    Tab tab_view_past_transactions, tab_make_transactions, tab_loan_management;

    //View past transactions
    @FXML
    Label select_date, id, name, daily_salary, ot_rate;
    @FXML
    CheckBox today_date_check;
    @FXML
    TableView tableView;

    //View loan management
    @FXML
    Label loan_date, loan_id, loan_name, loan_daily_salary, loan_ot_rate,
            loan_amount, loan_date_range, loan_daily_payment, loan_updated_salary;
    @FXML
    Button loan_save_draft, loan_submit;
    @FXML
    TableView loan_tableView;

    //Make payment
    @FXML
    Label make_id, make_name, make_occupation, make_daily_salary, updated_salary_description, make_payment;
    @FXML
    RadioButton make_full_day_radio, make_half_day_radio, make_full_amount_radio, make_updated_amount_radio;
    @FXML
    Button btn_make_submit, btn_print_slip;
    @FXML
    TableView make_tableView;

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
            new Log("PaymentsDetailsController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }


    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        //Payment details - root
        tab_view_past_transactions.setText(bundle.getString("tab_view_past_transactions"));
        tab_make_transactions.setText(bundle.getString("tab_make_transactions"));
        tab_loan_management.setText(bundle.getString("tab_loan_management"));

        //View past payments
        select_date.setText(bundle.getString("select_date"));
        id.setText(bundle.getString("employee_id"));
        name.setText(bundle.getString("employee_name"));
        daily_salary.setText(bundle.getString("employee_daily_salary"));
        ot_rate.setText(bundle.getString("employee_ot_rate"));
        today_date_check.setText(bundle.getString("join_date_today_check"));

        //Loan management
        loan_date.setText(bundle.getString("date_id"));
        loan_id.setText(bundle.getString("employee_id"));
        loan_name.setText(bundle.getString("employee_name"));
        loan_daily_salary.setText(bundle.getString("employee_daily_salary"));
        loan_ot_rate.setText(bundle.getString("employee_ot_rate"));
        loan_amount.setText(bundle.getString("loan_amount"));
        loan_date_range.setText(bundle.getString("loan_date_range"));
        loan_daily_payment.setText(bundle.getString("loan_daily_payment"));
        loan_updated_salary.setText(bundle.getString("loan_updated_salary"));
        loan_save_draft.setText(bundle.getString("btn_save_draft"));
        loan_submit.setText(bundle.getString("btn_submit"));

        //Make payment
        make_id.setText(bundle.getString("employee_id"));
        make_name.setText(bundle.getString("employee_name"));
        make_occupation.setText(bundle.getString("employee_occupation"));
        make_daily_salary.setText(bundle.getString("employee_daily_salary"));
        make_full_day_radio.setText(bundle.getString("make_full_day_radio"));
        make_half_day_radio.setText(bundle.getString("make_half_day_radio"));
        updated_salary_description.setText(bundle.getString("updated_salary_description"));
        make_full_amount_radio.setText(bundle.getString("make_full_amount_radio"));
        make_updated_amount_radio.setText(bundle.getString("make_updated_amount_radio"));
        make_payment.setText(bundle.getString("make_payment"));
        btn_make_submit.setText(bundle.getString("btn_make_submit"));
        btn_print_slip.setText(bundle.getString("btn_print_slip"));

        tableView.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
        loan_tableView.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
        make_tableView.setPlaceholder(new Label(bundle.getString("no_table_view_content")));

    }
}
