package Controller;

import Dto.RequestDto;
import Dto.UserInfoDto;
import Service.AddressService;
import Service.DocumentService;
import Service.UserService;
import entity.Address;
import entity.Request;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import repository.UserRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class adminSeeAllUsersController {
    @FXML
    private TableView<UserInfoDto> usersTableView;

    @FXML
    private TableColumn<UserInfoDto, String> userTableColumn;

    @FXML
    private TableColumn<UserInfoDto, String> typeOfUserTableColumn;

    @FXML
    private TableColumn<UserInfoDto, String> emailTableColumn;

    @FXML
    private TableColumn<UserInfoDto, String> addressesTableColumn;

    @FXML
    private ListView<String> listViewAddresses;

    private User currentUser;
    private ObservableList<UserInfoDto> list = FXCollections.observableArrayList();
    private ObservableList<String> listView = FXCollections.observableArrayList();


    @FXML
    void backToUserMainMenuButtonPush(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/AdminMainMenu.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene =  new Scene(viewParent);

        AdminMainMenuController adminMainMenuController = loader.getController();
        adminMainMenuController.refpreshUserWithDatabase(currentUser);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    @FXML
    void initListViewOnMouseClickedOnTable(MouseEvent event) {
        UserService userService = new UserService();
        String userName = usersTableView.getSelectionModel().getSelectedItem().getName();
        User selectedUser = userService.getUserByName(userName);

        listView.clear();
        List<Address> addresses = selectedUser.getAddresses();
        for(Address a: addresses){
            listView.add(a.getAddressName());
        }
        listViewAddresses.getItems().clear();
        listViewAddresses.getItems().addAll(listView);
        //listViewAddresses.getItems().addAll(addressesNames);

    }



    void initCurrentUser(User currentUser){
        this.currentUser = currentUser;

        //----------------------
        initTable();

    }


    void initTable(){

        list.clear();

        UserService userService = new UserService();

        List<User> users = new ArrayList<User>();
        users = userService.getUsers();

        List<UserInfoDto> userInfoDtoList = new ArrayList<UserInfoDto>();
        for(User u: users){
            userInfoDtoList.add(new UserInfoDto(u.getName(),u.getAdmin_user(),u.getEmail(), "click pentru a vedea" ));
        }

        list.addAll(userInfoDtoList);

        userTableColumn.setCellValueFactory(new PropertyValueFactory<UserInfoDto,String>("name"));
        typeOfUserTableColumn.setCellValueFactory(new PropertyValueFactory<UserInfoDto,String>("type"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<UserInfoDto,String>("email"));
        addressesTableColumn.setCellValueFactory(new PropertyValueFactory<UserInfoDto,String>("addresses"));
        usersTableView.setItems(list);
    }




}
