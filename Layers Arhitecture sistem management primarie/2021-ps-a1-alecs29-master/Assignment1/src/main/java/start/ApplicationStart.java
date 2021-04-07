package start;

import Service.RequestService;
import Service.UserService;
import Utils.AplicationUtils;
import Utils.ControllerUtils;
import entity.Request;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.UserRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApplicationStart extends Application{

    public static void main(String[] args) throws IOException {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/frames/login.fxml"));
        primaryStage.setTitle("Primaria municipiului Cluj-Napoca");
        primaryStage.setScene(new Scene(root, 1250, 850));
        primaryStage.show();
    }

}


