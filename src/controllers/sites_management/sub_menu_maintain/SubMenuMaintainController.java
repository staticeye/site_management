package controllers.sites_management.sub_menu_maintain;

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

public class SubMenuMaintainController implements Initializable {
    //Add menu
    @FXML
    Tab btn_add_sub_menu, btn_view_update_delete_sub_menus;
    @FXML
    Button  btn_save_draft, btn_submit;
    @FXML
    Label menu_id, menu_name, main_menu;
    @FXML
    TableView add_sub_menu_tableView;

    //View update delete menu
    @FXML
    Label update_sub_menu_id, update_sub_menu_name, main_menu_name;
    @FXML
    Button  btn_update, btn_delete;
    @FXML
    TableView update_sub_menu_tableView, update_sub_menu_tableView_2;

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
            new Log("SubMenuMaintainController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_add_sub_menu.setText(bundle.getString("btn_add_sub_menu"));
        btn_view_update_delete_sub_menus.setText(bundle.getString("btn_view_update_delete_sub_menus"));
        menu_id.setText(bundle.getString("update_sub_menu_id"));
        menu_name.setText(bundle.getString("update_sub_menu_name"));
        btn_save_draft.setText(bundle.getString("btn_save_draft"));
        btn_submit.setText(bundle.getString("btn_submit"));

        main_menu_name.setText(bundle.getString("main_menu"));
        update_sub_menu_id.setText(bundle.getString("update_sub_menu_id"));
        update_sub_menu_name.setText(bundle.getString("update_sub_menu_name"));
        main_menu.setText(bundle.getString("main_menu"));
        btn_update.setText(bundle.getString("btn_update"));
        btn_delete.setText(bundle.getString("btn_delete_menu"));

        add_sub_menu_tableView.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
        update_sub_menu_tableView.setPlaceholder(new Label(bundle.getString("no_table_view_content")));
        update_sub_menu_tableView_2.setPlaceholder(new Label(bundle.getString("no_table_view_content")));

    }
}
