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
    final double SCREEN_SAFE_VALUE = 100;
    Preferences pref;

    @Override
    public void start(Stage primaryStage) throws Exception {
        pref = Preferences.userNodeForPackage(Main.class);
        StaticAttributes.isSinhalaEnable = pref.getBoolean(StaticAttributes.isSinhalaEnableKey, false);
        createScene(primaryStage);
    }

    private void createScene(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/welcome_view.fxml"));
        primaryStage.setTitle(AppStrings.SCENE_TITLE);
        Image image = new Image(AppURL.APPLICATION_ICON);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screen_height = screenBounds.getHeight();
        double screen_Width = screenBounds.getWidth();

        primaryStage.setMinWidth(screen_Width * 0.5);
        primaryStage.setMinHeight(screen_height * 0.5);

        primaryStage.getIcons().add(image);
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root, screen_Width * 0.5, screen_height * 0.5));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
