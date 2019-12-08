package sample;

import common.AppStrings;
import common.AppURL;
import common.StaticAttributes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import java.util.prefs.Preferences;

public class Main extends Application {
    Preferences pref;

    @Override
    public void start(Stage primaryStage) throws Exception {
        pref = Preferences.userNodeForPackage(Main.class);
        StaticAttributes.isSinhalaEnable = pref.getBoolean(StaticAttributes.isSinhalaEnableKey, false);
        createScene(primaryStage);
    }

    private void createScene(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/welcome_view.fxml"));
        StaticAttributes.setStage(primaryStage,root, -1, -1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
