package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
   public static FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlPath = "/application/MainScene.fxml";
        System.out.println("Loading FXML from: " + getClass().getResource(fxmlPath));
        loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
