package Controller;

import Dto.RequestDto;
import Service.AddressService;
import Service.DocumentService;
import Service.RequestService;
import Service.UserService;
import Utils.AplicationUtils;
import entity.Address;
import entity.Request;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repository.RequestRepo;
import repository.UserRepo;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserMakeRequestsController implements Initializable {

    @FXML
    private ChoiceBox<String> addressChoiseBox;

    @FXML
    private ChoiceBox<String> typeOfDocumentChoiseBox;

    @FXML
    private TableView<RequestDto> requestsTableView;

    @FXML
    private TableColumn<RequestDto, String> userTableColumn;

    @FXML
    private TableColumn<RequestDto, String> requestedTableColumn;

    @FXML
    private TableColumn<RequestDto, String> addressTableColumn;

    @FXML
    private TableColumn<RequestDto, String> approvedTableColumn;

    @FXML
    private Label numberOfRequestsRemaining;

    private RequestDto dtoForTable;
    private RequestService requestService = new RequestService();
    private User currentUser;
    private List<Address> addresses;

    ObservableList<RequestDto> list = FXCollections.observableArrayList();

    @FXML
    void sendRequestButtonPush(ActionEvent event) {
        UserService userService = new UserService();

        Request request = new Request();
        request.setId(AplicationUtils.generateUUID());
        request.setRequestName(typeOfDocumentChoiseBox.getValue());


        Address selectedAddress = null;

        for(Address a: addresses){
            if(a.getAddressName().equals(addressChoiseBox.getValue())){
                selectedAddress = a;

                break;
            }
        }

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = AplicationUtils.convert(utilDate);

        int year = AplicationUtils.convertYearTimeStampToInt(sqlDate);

        request.setAddress(selectedAddress);
        request.setUser(userService.getUserById(currentUser.getId()));
        request.setApproved("pending");
        request.setRequestDate(year);


        requestService.addVerifiedRequest(request,selectedAddress);

        initTable();
    }



    public static void main (String[] args)
    {

    }

    @FXML
    void verifyNrOfRequestsButtonPush(ActionEvent event) {
        Address selectedAddress = null;

        for(Address a: addresses){
            if(a.getAddressName().equals(addressChoiseBox.getValue())){
                selectedAddress = a;

                break;
            }
        }

        numberOfRequestsRemaining.setText(Integer.toString(selectedAddress.getNumbersOFRequests()));

    }

    @FXML
    void backToUserMainMenuButtonPush(ActionEvent event) throws IOException {


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
    void deletedSelectedRequestButtonPush(ActionEvent event) {
        RequestDto requestDto = requestsTableView.getSelectionModel().getSelectedItem();
        RequestService requestService = new RequestService();


        requestService.deleteByName(requestDto.getDocumentName());
        initTable();
    }

    void initCurrentUser(User currentUser){
        this.currentUser = currentUser;
        //userGrreet.setText("Hello " + this.currentUser.getName());

        DocumentService documentService = new DocumentService();
        AddressService addressService = new AddressService();

        List<String> documentNames = documentService.getDocumentNames();

        this.addresses = currentUser.getAddresses();
        List<String> adressesNames = new ArrayList<String>(addresses.size());

        for(Address t: addresses){
            adressesNames.add(t.getAddressName());
        }

        typeOfDocumentChoiseBox.getItems().clear();
        typeOfDocumentChoiseBox.getItems().addAll(documentNames);

        addressChoiseBox.getItems().clear();
        addressChoiseBox.getItems().addAll(adressesNames);

        //----------------------

        initTable();

    }


    void initTable(){

        list.clear();

        DocumentService documentService = new DocumentService();
        UserService userService = new UserService();

        List<Request> requests = new ArrayList<Request>();
        requests = requestService.getRequests(); //toate requesturile


        List<RequestDto> requestDtoList = new ArrayList<RequestDto>();
        for(Request r: requests){
            requestDtoList.add(new RequestDto(r.getRequestName(),r.getUser().getName(),r.getAddress().getAddressName(),r.getApproved()));
        }
        list.addAll(requestDtoList);

        userTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("user"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("address"));
        approvedTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("approved"));
        requestedTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("documentName"));
        requestsTableView.setItems(list);


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
