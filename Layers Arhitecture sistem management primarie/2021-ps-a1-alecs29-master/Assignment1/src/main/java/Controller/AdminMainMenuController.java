package Controller;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.UserRepo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainMenuController implements Initializable {

    @FXML
    private VBox vboxPaneToLoad;

    @FXML
    private Label userGrreet;

    @FXML
    void acceptRequestsButtonPush(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/adminAcceptRequests.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        AdminAcceptRequestsController adminAcceptRequestsController = loader.getController();
        adminAcceptRequestsController.initCurrentUser(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    private User currentUser;

    @FXML
    void addDocumentTypeButtonPush(ActionEvent event) throws IOException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/adaugareDocumente.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        AdminAddDocumentsController adminAddDocumentsController = loader.getController();
        adminAddDocumentsController.initCurrentUser(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();


    }

    @FXML
    void backToLoginButtonPush(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("/frames/login.fxml"));
        Scene viewScene =  new Scene(viewParent);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }
    

    @FXML
    void seeMadeRequestsButtonPush(ActionEvent event) {

    }

    @FXML
    void seeUsersButtonPush(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/adminSeeAllUsers.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        adminSeeAllUsersController adminSeeAllUsersController = loader.getController();
        adminSeeAllUsersController.initCurrentUser(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void initCurrentUser(User currentUser){
        this.currentUser = currentUser;
        userGrreet.setText("Hello, " + this.currentUser.getName());

    }
    void refpreshUserWithDatabase(User currentUser){
        this.currentUser = currentUser;
        UserRepo userRepo = new UserRepo();
        this.currentUser = userRepo.findByID(currentUser.getId()); //refresh user fromm db after possible changes
    }

}
