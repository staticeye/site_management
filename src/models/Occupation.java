package models;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import dbutil.DbConnection;
import helpers.Log;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Occupation {
    Connection connection;
    int id;
    String occupation_sinhala;
    String occupation_english;

    public Occupation(int id, String occupation_sinhala, String occupation_english) {

        try {
            this.connection = DbConnection.getConnection();
        } catch (Exception e) {
            new Log("Occupation - Occupation : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

        if (this.connection == null) {
            System.exit(1);
        }

        this.id = id;
        this.occupation_sinhala = occupation_sinhala;
        this.occupation_english = occupation_english;
    }

    public Occupation() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (Exception e) {
            new Log("Occupation - Occupation : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOccupation_sinhala() {
        return occupation_sinhala;
    }

    public void setOccupation_sinhala(String occupation_sinhala) {
        this.occupation_sinhala = occupation_sinhala;
    }

    public String getOccupation_english() {
        return occupation_english;
    }

    public void setOccupation_english(String occupation_english) {
        this.occupation_english = occupation_english;
    }

    //----------------------------------------------------------
    public ResultSet getAllOccupations() throws SQLException {
        String sql = "SELECT * FROM occupations";
        return DbConnection.selectDB(this.connection, sql);
    }
}
