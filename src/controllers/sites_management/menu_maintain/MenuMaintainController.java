package controllers.sites_management.menu_maintain;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuMaintainController implements Initializable {
    //Add menu
    @FXML
    Tab btn_add_menu, btn_view_update_delete_menus;
    @FXML
    Button  btn_save_draft, btn_submit;
    @FXML
    Label menu_id, menu_name;
    @FXML
    TableView add_menu_tableView;

    //View update delete menu
    @FXML
    Label update_menu_id, update_menu_name;
    @FXML
    Button  btn_update, btn_delete;
    @FXML
    TableView update_menu_tableView, update_menu_tableView_2;

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
            new Log("AddMenuController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_add_menu.setText(bundle.getString("btn_add_menu"));
        btn_view_update_delete_menus.setText(bundle.getString("btn_view_update_delete_menus"));
        menu_id.setText(bundle.getString("menu_id"));
        menu_name.setText(bundle.getString("menu_name"));
        btn_save_draft.setText(bundle.getString("btn_save_draft"));
        btn_submit.setText(bundle.getString("btn_submit"));

        update_menu_id.setText(bundle.getString("menu_id"));
        update_menu_name.setText(bundle.getString("menu_name"));
        btn_update.setText(bundle.getString("btn_update"));
        btn_delete.setText(bundle.getString("btn_delete_menu"));

        add_menu_tableView.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
        update_menu_tableView.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
        update_menu_tableView_2.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
    }
}
