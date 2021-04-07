package Controller;

import Service.AddressService;
import Service.UserService;
import Utils.AplicationUtils;
import entity.Address;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateAccountController {

    @FXML
    private TextField nameTxtField;

    @FXML
    private TextField emailTxtField;

    @FXML
    private TextField passwordTxtField;

    @FXML
    private TextField address1TxtField;


    @FXML
    private RadioButton adminChkBox;

    @FXML
    void createNewAccountButtonPush(ActionEvent event) {

        UserService userService = new UserService();
        AddressService addressService = new AddressService();

        User newUser = new User();
        Address address = new Address();

        newUser.setId(AplicationUtils.generateUUID());
        if(adminChkBox.isSelected())
            newUser.setAdmin_user("admin");
        else  newUser.setAdmin_user("user");
        newUser.setName(nameTxtField.getText().trim());
        newUser.setPassword(passwordTxtField.getText().trim());
        newUser.setEmail(emailTxtField.getText());

        address.setId(AplicationUtils.generateUUID());
        address.setAddressName(address1TxtField.getText().trim());
        address.setNumbersOFRequests(3);
        List<Address> addressList = new ArrayList<>();
        address.setUser(newUser);
        addressList.add(address);

        newUser.setAddresses(addressList);


        userService.addUser(newUser, address);

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

}
