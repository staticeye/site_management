package controllers.employee_management.employee_details;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import controllers.popups.OccupationView;
import helpers.Component;
import helpers.Log;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Employee;
import models.Occupation;
import models.Person;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

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
    @FXML
    TextField add_employee_id, employee_name, employee_mobile_1, employee_mobile_2, employee_dailysalary, employee_ot_rate, annual_bonus;

    boolean isAddEmployeeCheckSelected = false;

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

    Occupation occupation = new Occupation();
    ObservableList<String> options =
            FXCollections.observableArrayList();
    List<Occupation> occupationList = new ArrayList<Occupation>();
    Employee add_employee;
    private ObservableList<Person> tvObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            init();
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

    private void didSelectAddEmployeeCheckBox(Observable observable) {
        if (!isAddEmployeeCheckSelected) {
            join_date_today_check.setSelected(false);
        }
        isAddEmployeeCheckSelected = false;
    }


    private void didClickAddEmployeeCheckBox(ActionEvent event) {
        if (join_date_today_check.isSelected()) {
            isAddEmployeeCheckSelected = true;
            datepicker.setValue(Component.setDatePickerCurrentDate());
        } else {
            datepicker.setValue(null);
        }
    }
    UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
        String input = change.getText();

        if ((input.matches("[\\d\\.]+")) || change.isDeleted()) {
            return change;
        }
        return null;
    };

    private void init() throws Exception {
        List<TextField> numberTextFieldList = new ArrayList<TextField>();
        numberTextFieldList.add(employee_mobile_1);
        numberTextFieldList.add(employee_mobile_2);
        numberTextFieldList.add(employee_dailysalary);
        numberTextFieldList.add(employee_ot_rate);
        numberTextFieldList.add(annual_bonus);
        add_employee = new Employee();

        setGridPane();
        join_date_today_check.setOnAction(this::didClickAddEmployeeCheckBox);
        datepicker.valueProperty().addListener(this::didSelectAddEmployeeCheckBox);
        employee_name.textProperty().addListener((observable, oldValue, newValue) -> {
            Component.textFieldAcceptOnlyLetters(newValue, employee_name);
        });
        Component.applyTextFieldFormatterOnlyNumbers(numberTextFieldList);

        add_employee_id.setText(add_employee.getId());
        if (occupation.isDatabaseConnected()) {
            ResultSet resultSet = occupation.getAllOccupations();
            while (resultSet.next()) {
                occupationList.add(new Occupation(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(2)));
                options.add(resultSet.getString(2) + " | " + resultSet.getString(3));
            }
            occupations_combo_update.setItems(options);
            occupations_combo_add.setItems(options);

        } else {
            AppDialogs.viewDialog("Error", AppStrings.DATABASE_CONNECTION_ERROR, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
            throw new Exception(AppStrings.DATABASE_CONNECTION_ERROR);
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

    @FXML
    private void did_Click_Add_Employee_Add(){
        SingleSelectionModel selectionModel = occupations_combo_add.getSelectionModel();
        String employeename = employee_name.getText();
        String mobile1 = employee_mobile_1.getText();
        String mobile2 = employee_mobile_2.getText();
        LocalDate joinDate = datepicker.getValue();
        int occupation = selectionModel.getSelectedIndex();
        String dailySalry = employee_dailysalary.getText();
        String otRate = employee_ot_rate.getText();
        String bonus = annual_bonus.getText();

        if (employeename.isEmpty() || joinDate == null || occupation == -1 || dailySalry.isEmpty() || otRate.isEmpty() || bonus.isEmpty()){
            AppDialogs.viewDialog("Error", AppStrings.MANDATORY_FIELDS, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }else{
            add_employee.setName(employeename);
            add_employee.setMobile_number(mobile1);
            add_employee.setMobile_number2(mobile2);
            add_employee.setDate(joinDate);
            add_employee.setOccupation(occupationList.get(occupation));
            add_employee.setDailySalary(dailySalry);
            add_employee.setOtRate(otRate);
            add_employee.setBonus(bonus);

            add_employee.insertEmployeeDB();
            clearFields();
            add_employee = new Employee();
            add_employee_id.setText(add_employee.getId());
        }
    }

    @FXML
    private void did_Click_Edit_Occupation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(AppURL.OCCUPATION_VIEW));
        Parent parent = fxmlLoader.load();
        OccupationView dialogController = fxmlLoader.<OccupationView>getController();
        dialogController.setAppMainObservableList(tvObservableList);

        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void did_Click_Add_Employee_Clear(){
        clearFields();
    }

    private void clearFields(){
        employee_name.setText("");
        employee_mobile_1.setText("");
        employee_mobile_2.setText("");
        employee_dailysalary.setText("");
        employee_ot_rate.setText("");
        annual_bonus.setText("");
        datepicker.setValue(null);
        join_date_today_check.setSelected(false);
        isAddEmployeeCheckSelected = false;
        occupations_combo_add.valueProperty().set(null);
    }


}
