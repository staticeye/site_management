package common;

import controllers.popups.OccupationView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AppComponents {

    public void openEditOccupation(Class viewClass, String stageName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(viewClass.getResource(AppURL.OCCUPATION_VIEW));
        Parent parent = fxmlLoader.load();
        OccupationView dialogController = fxmlLoader.<OccupationView>getController();
        Scene scene = new Scene(parent, 1000, 450);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(AppURL.APPLICATION_ICON));
        stage.setTitle(stageName);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
