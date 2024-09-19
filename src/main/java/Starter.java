import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Time;
import java.time.LocalTime;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/oder_from.fxml"))));
        stage.show();


    }

}
