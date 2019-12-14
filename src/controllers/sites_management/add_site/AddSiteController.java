package controllers.sites_management.add_site;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import properties.SiteProperties;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSiteController implements Initializable {
    @FXML
    TabPane add_sites_tab_pane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createTabs();

    }

    private void createTabs(){
        for (int index = 0; index < SiteProperties.current_add_sites_floor_count; index++){
            add_sites_tab_pane.getTabs().add(new Tab("Floor " + (index + 1)));
        }
        SiteProperties.current_add_sites_floor_count = 0;
    }
}
