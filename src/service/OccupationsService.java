package service;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import dbutil.DbConnection;
import helpers.Log;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OccupationsService {
    Connection connection;

    public OccupationsService() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (Exception e) {
            new Log("OccupationsService - OccupationsService : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

        if (this.connection == null) {
            System.exit(1);
        }
    }

    public boolean isDatabaseConnected() {
        return this.connection != null;
    }

    //----------------------------------------------------------
    public ResultSet getAllOccupations() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT occupation FROM occupations";
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }


}
