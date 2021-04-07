package Controller;

import Service.AddressService;
import Utils.AplicationUtils;
import entity.Address;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.UserRepo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserAddDeleteAddressesController implements Initializable {

    @FXML
    private TextField newAdressTxtField;

    @FXML
    private ChoiceBox<String> addressesChoiseBox;

    //AddressService addressService = new AddressService();
    List<String> addressStrings;
    User currentUser;

    @FXML
    void backToUserMainMenuButtonPush(ActionEvent event) throws IOException {
//        Parent viewParent = FXMLLoader.load(getClass().getResource("/frames/UserMainMenu.fxml"));
//        Scene viewScene =  new Scene(viewParent);
//
//        //getting the stage information
//        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        windwow.setScene(viewScene);
//        windwow.show();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/UserMainMenu.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        UserMainMenuController userMainMenuController = loader.getController();
        userMainMenuController.refpreshUserWithDatabase(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    @FXML
    void createNewAdressButtonPush(ActionEvent event) {
        AddressService addressService = new AddressService();

        Address address = new Address();
        address.setId(AplicationUtils.generateUUID());
        address.setAddressName(newAdressTxtField.getText());
        address.setUser(this.currentUser);
        address.setNumbersOFRequests(3);
        addressService.addAddressWithMessage(address);

        addressStrings.add(address.getAddressName());
        refreshAddressesChoiseBox();
    }

    @FXML
    void deleteNewAdressButtonPush(ActionEvent event) {
        AddressService addressService = new AddressService();
        String addressToBeDeleted = addressesChoiseBox.getValue();

        for(Address a : addressService.getAddresses()){
            if(a.getAddressName().equals(addressToBeDeleted)){
                addressService.deleteById(a.getId());
                break;
            }
        }
        refreshCurrentUser();
        setAddressesData();
        refreshAddressesChoiseBox();

    }

    void setAddressesData(){
        AddressService addressService = new AddressService();

        List<String> addressNames = new ArrayList<String>();// addressService.getAddressesNames();

        addressNames = addressService.convertAddressesToString(currentUser.getAddresses());
        this.addressStrings = addressNames;

    }
    void refreshAddressesChoiseBox(){
        addressesChoiseBox.getItems().clear();
        addressesChoiseBox.getItems().addAll(this.addressStrings);
    }
    void refreshCurrentUser(){
        UserRepo userRepo = new UserRepo();
        currentUser = userRepo.findByID(currentUser.getId());
    }

    void initCurrentUser(User currentUser){
        this.currentUser = currentUser;
        setAddressesData();
        addressesChoiseBox.getItems().addAll(this.addressStrings);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
