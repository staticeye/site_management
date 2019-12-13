package common;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StaticAttributes {
    public final static String isSinhalaEnableKey = "key_isSinhalaEnableKey";
    public static boolean isSinhalaEnable = false;
    public static String user_language = "sinhala";

    public static void setStage(Stage stage, Parent root, double stageWidth, double stageHeight) {

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screen_height = screenBounds.getHeight();
        double screen_Width = screenBounds.getWidth();
        double currentStageWidth = 0;
        double currentStageHeight = 0;
        final double MAX_RESIZED_SCREEN_WIDTH = 1500;
        final double MAX_RESIZED_SCREEN_HEIGHT = 1500;
        
        stage.setTitle(AppStrings.SCENE_TITLE);
        Image image = new Image(AppURL.APPLICATION_ICON);
        stage.setMinWidth(screen_Width * 0.6);
        stage.setMinHeight(screen_height * 0.6);
        stage.getIcons().add(image);
        if ((stageWidth == -1 && stageHeight == -1) || (stageWidth > MAX_RESIZED_SCREEN_WIDTH || stageHeight > MAX_RESIZED_SCREEN_HEIGHT)) {
            stage.setMaximized(true);
        } else {
            currentStageWidth = stageWidth;
            currentStageHeight = stageHeight;
        }
        stage.setScene(new Scene(root, currentStageWidth, currentStageHeight));
        stage.show();
    }
}
