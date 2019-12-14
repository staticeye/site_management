package controllers.sites_management;

import common.AppDialogs;
import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import helpers.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import properties.SiteProperties;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SitesManagementSelectionController implements Initializable {

    @FXML
    Button btn_add_new_site, btn_view_sites, btn_maintain_menu, btn_maintain_sub_menus, btn_back;
    @FXML
    private AnchorPane content_pane, root_pane;

    Stage primaryStage;
    private ResourceBundle bundle;
    private Locale locale;
    String defaultChoice = "";
    String header = "";
    String comboText = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (StaticAttributes.isSinhalaEnable) {
                loadLang("sinhala");
            } else {
                loadLang("english");
            }
        } catch (Exception e) {
            new Log("SiteManagementSelectionController - initialize : ", e).error();
            AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }


    @FXML
    public void didClick_btn_back(ActionEvent event) {
        navigateToPreviousStage(AppURL.WELCOME_VIEW);
    }

    @FXML
    public void didClick_btn_menu_maintain() {
        loadUI(AppURL.MENU_MAINTAIN);
    }

    @FXML
    public void didClick_btn_sub_menu_maintain() {
        loadUI(AppURL.SUB_MENU_MAINTAIN);
    }

    @FXML
    public void didClick_btn_addNewSite() {
        List<String> choices = loadFloorCount();
        AppDialogs.viewChoiceDialog(choices, defaultChoice, AppStrings.CONFIRMATION, header, comboText, floors -> {
            try {
                validateAndProceedToNext(floors);
            } catch (Exception e) {
                new Log("SiteManagementSelectionController - didClick_btn_addNewSite : ", e).error();
                AppDialogs.viewDialog("Error", AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
            }
        });
    }

    private void validateAndProceedToNext(String floorCount) throws IOException {
        locale = new Locale(StaticAttributes.user_language);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        if (floorCount == bundle.getString("defaultChoice")) {
            return;
        }
        SiteProperties.current_add_sites_floor_count = Integer.parseInt(floorCount);
        loadUI(AppURL.MENU_SELECTION);

    }

    private void navigateToPreviousStage(String scenePath) {
        try {
            primaryStage = (Stage) btn_add_new_site.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenePath));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            StaticAttributes.setStage(stage, root1, primaryStage.getWidth(), primaryStage.getHeight());
            primaryStage.close();
        } catch (Exception e) {
            new Log("SiteManagementManagementSelection - navigateToPreviousStage : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }
    }

    private void loadLang(String lang) throws Exception {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("common.lang", locale);

        btn_add_new_site.setText(bundle.getString("btn_add_new_site"));
        btn_view_sites.setText(bundle.getString("btn_view_sites"));
        btn_maintain_menu.setText(bundle.getString("btn_maintain_menu"));
        btn_maintain_sub_menus.setText(bundle.getString("btn_maintain_sub_menus"));
        btn_back.setText(bundle.getString("btn_back"));

        defaultChoice = bundle.getString("defaultChoice");
        header = bundle.getString("header");
        comboText = bundle.getString("comboText");

    }

    private void loadUI(String UiName) {
        try {
            content_pane = FXMLLoader.load(getClass().getResource(UiName));

            //Content not taking the whole screen size issue solved here,
            Node node = content_pane;
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
            root_pane.getChildren().setAll(node);
        } catch (Exception e) {
            new Log("SitesManagementSelectionController - loadUI : ", e).error();
            AppDialogs.viewDialog(AppStrings.ERROR, AppStrings.SOMETHING_WRONG, Alert.AlertType.ERROR, AppURL.ERROR_ALERT_ICON, AppStrings.ALERT_BUTTON);
        }

    }

    private List<String> loadFloorCount() {
        List<String> choices = new ArrayList<>();

        for (int index = 1; index <= 10; index++) {
            choices.add(Integer.toString(index));
        }
        return choices;
    }
}
