package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("App.fxml"));

            Scene scene = new Scene(root);
            //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            primaryStage.setTitle("Html-Editor");
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }
}
