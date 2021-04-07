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
import javafx.stage.Stage;
import repository.UserRepo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMainMenuController implements Initializable {

    @FXML
    private Label userGrreet;

    @FXML
    void addDeleteAddresses(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/UserAddDeleteAddresses.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        UserAddDeleteAddressesController userAddDeleteAddressesController = loader.getController();
        userAddDeleteAddressesController.initCurrentUser(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    @FXML
    void addDeleteModifyRequests(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/userMakeRequests.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        UserMakeRequestsController userMakeRequestsController = loader.getController();
        userMakeRequestsController.initCurrentUser(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    @FXML
    void BackToLoginButtonPush(ActionEvent event) throws IOException {

        Parent viewParent = FXMLLoader.load(getClass().getResource("/frames/login.fxml"));
        Scene viewScene =  new Scene(viewParent);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    private User currentUser;

    @FXML
    void seeUsersButtonPush(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
