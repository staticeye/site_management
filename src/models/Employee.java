package models;

import common.AppConstants;
import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import dbutil.DbConnection;
import helpers.Component;
import helpers.Log;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.time.LocalDate;

public class Employee {
    Connection connection;
    String id;
    String name;
    String mobile_number;
    String mobile_number2;
    LocalDate date;
    Occupation occupation;
    String dailySalary;
    String otRate;
    String bonus;

    public Employee(String name, String mobile_number, String mobile_number2, LocalDate date, Occupation occupation, String dailySalary, String otRate, String bonus) {
        try {
            this.connection = DbConnection.getConnection();
        } catch (Exception e) {
            new Log("Employee - Employee : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

        if (this.connection == null) {
            System.exit(1);
        }
        this.id = Component.generateUUID(AppConstants.EMPLOYEE_NAME_ID);
        this.name = name;
        this.mobile_number = mobile_number;
        this.mobile_number2 = mobile_number2;
        this.date = date;
        this.occupation = occupation;
        this.dailySalary = dailySalary;
        this.otRate = otRate;
        this.bonus = bonus;
    }

    public Employee() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (Exception e) {
            new Log("Employee - Employee : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

        if (this.connection == null) {
            System.exit(1);
        }
        this.id = Component.generateUUID(AppConstants.EMPLOYEE_NAME_ID);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(String dailySalary) {
        this.dailySalary = dailySalary;
    }

    public String getOtRate() {
        return otRate;
    }

    public void setOtRate(String otRate) {
        this.otRate = otRate;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getMobile_number2() {
        return mobile_number2;
    }

    public void setMobile_number2(String mobile_number2) {
        this.mobile_number2 = mobile_number2;
    }

    //----------------------------------------------------------------
    public void insertEmployeeDB() {
        String qry = "INSERT INTO Employee (id, name, mobile1, mobile2, joindate, dailysalary, otrate, bonus, occupation_id) values ('"+this.id+"', '"+this.name+"', '"+this.mobile_number+"', '"+this.mobile_number2+"', '"+this.date+"', '"+this.dailySalary+"', '"+this.otRate+"', '"+this.bonus+"', '"+this.occupation.getId()+"');";
        DbConnection.insertDB(this.connection, qry);
    }
}
