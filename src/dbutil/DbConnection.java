package dbutil;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import helpers.Log;
import javafx.scene.control.Alert;

import java.sql.*;

public class DbConnection {
    private static final String SQCONN = "jdbc:sqlite:site_management.sqlite";

    public static Connection getConnection ()throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONN);
        }catch (Exception e){
            new Log("DbConnection - getConnection : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
        return null;
    }

    public static void insertDB(Connection connection, String qry){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet selectDB(Connection connection, String qry){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM occupations";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
