package Controller;
import Service.UserService;
import Utils.ControllerUtils;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {



    @FXML
    private TextField emailTxtField;

    @FXML
    private TextField passwordTxtField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button createAccountBtn;


    UserService userService = new UserService();

    private static User currentUser = new User();

    @FXML
    void createAccountPush(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("/frames/createAccount.fxml"));
        Scene viewScene =  new Scene(viewParent);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();

    }

    @FXML
    void loginButtonPush(ActionEvent event) {

        User foudUser = new User();

        try {

            foudUser = userService.getUserByEmail(emailTxtField.getText().trim());

            currentUser = foudUser;

            if(foudUser.getPassword().equals(passwordTxtField.getText())) {
                if(foudUser.getAdmin_user().equals("admin")){
                    loadMainMenuAdmin(event);
                }else {
                    loadMainMenuUser(event);
                }
            }

            else {
                ControllerUtils.alert1(ControllerUtils.WrongPasswordContentError);}
        }
        catch(Exception NullPointerException){
            ControllerUtils.alert1(ControllerUtils.WrongUsernameContentError);

        }
    }

    private void loadMainMenuUser(ActionEvent event) throws IOException {
//        Parent viewParent = FXMLLoader.load(getClass().getResource("/frames/userMainMenu.fxml"));
//        Scene viewScene =  new Scene(viewParent);
//
//        //getting the stage information
//        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        windwow.setScene(viewScene);
//        windwow.show();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/userMainMenu.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        UserMainMenuController userMainMenuController = loader.getController();
        userMainMenuController.initCurrentUser(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    private void loadMainMenuAdmin(ActionEvent event) throws IOException{
//        Parent viewParent = FXMLLoader.load(getClass().getResource("/frames/adminMainMenu.fxml"));
//        Scene viewScene =  new Scene(viewParent);
//
//        //getting the stage information
//        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        windwow.setScene(viewScene);
//        windwow.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/adminMainMenu.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        AdminMainMenuController adminMainMenuController = loader.getController();
        adminMainMenuController.initCurrentUser(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }



}
