package controllers.sites_management.add_site;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import properties.SiteProperties;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddSiteController implements Initializable {
    @FXML
    TabPane add_sites_tab_pane;
    String floor = "";
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
            createTabs();
        } catch (Exception e) {
            new Log("AddSiteController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }


    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        floor = bundle.getString("floor");

    }

    private void createTabs() {
        Tab tab;
        for (int index = 0; index < SiteProperties.current_add_sites_floor_count; index++) {
            if (floor.equalsIgnoreCase("Floor")) {
                tab = new Tab("Floor " + (index + 1));
            } else {
                tab = new Tab((index + 1) + " මහල");
            }
            add_sites_tab_pane.getTabs().add(tab);
        }
        SiteProperties.current_add_sites_floor_count = 0;
    }
}
