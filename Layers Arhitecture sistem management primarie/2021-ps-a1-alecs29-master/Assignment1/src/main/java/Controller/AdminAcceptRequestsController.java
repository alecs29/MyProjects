package Controller;

import Dto.RequestDto;
import Service.AddressService;
import Service.DocumentService;
import Service.RequestService;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminAcceptRequestsController {

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
    private CheckBox pendingCheckBox;

    @FXML
    private TextField searchTxtField;

    @FXML
    void keyTypedSearch(KeyEvent event) {
        searchRefreshTable();
    }

    @FXML
    void aaceptRequestButtonPush(ActionEvent event) {
        RequestService requestService = new RequestService();
        requestService.updateRequestApproved(requestsTableView.getSelectionModel().getSelectedItem());
        searchRefreshTable();

    }
    @FXML
    void pendingMouseClicked(MouseEvent event) {

            searchRefreshTable();
    }

    User currentUser = new User();
    ObservableList<RequestDto> list = FXCollections.observableArrayList();


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

    void initCurrentUser(User currentUser){
        this.currentUser = currentUser;

        DocumentService documentService = new DocumentService();
        AddressService addressService = new AddressService();

        List<String> documentNames = documentService.getDocumentNames();

        searchRefreshTable();

    }


    void initTablePending(){

        list.clear();
        RequestService requestService = new RequestService();

        List<Request> requests = new ArrayList<Request>();
        requests = requestService.getRequests(); //toate requesturile


        List<RequestDto> requestDtoList = new ArrayList<RequestDto>();
        for(Request r: requests){
            if(r.getApproved().equals("pending"))
                requestDtoList.add(new RequestDto(r.getRequestName(),r.getUser().getName(),r.getAddress().getAddressName(),r.getApproved()));
        }
        list.clear();
        list.addAll(requestDtoList);

        userTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("user"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("address"));
        approvedTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("approved"));
        requestedTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("documentName"));
        requestsTableView.setItems(list);


    }
    void initTable(){

        list.clear();
        RequestService requestService = new RequestService();

        List<Request> requests = new ArrayList<Request>();
        requests = requestService.getRequests(); //toate requesturile

        List<RequestDto> requestDtoList = new ArrayList<RequestDto>();
        for(Request r: requests){
            requestDtoList.add(new RequestDto(r.getRequestName(),r.getUser().getName(),r.getAddress().getAddressName(),r.getApproved()));
        }
        list.clear();
        list.addAll(requestDtoList);

        userTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("user"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("address"));
        approvedTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("approved"));
        requestedTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("documentName"));
        requestsTableView.setItems(list);


    }

    void searchRefreshTable(){

        list.clear();
        RequestService requestService = new RequestService();

        List<Request> requests = new ArrayList<Request>();
        requests = requestService.getRequests(); //toate requesturile

        List<RequestDto> requestDtoList = new ArrayList<RequestDto>();
        for(Request r: requests){
            if(pendingCheckBox.isSelected()) {
                if(r.getApproved().equals("pending"))
                if (r.getAddress().getAddressName().indexOf(searchTxtField.getText()) != -1 ||
                        r.getRequestName().indexOf(searchTxtField.getText()) != -1 ||
                        r.getUser().getName().indexOf(searchTxtField.getText()) != -1)
                    requestDtoList.add(new RequestDto(r.getRequestName(), r.getUser().getName(), r.getAddress().getAddressName(), r.getApproved()));
            }
            else{
                if (r.getAddress().getAddressName().indexOf(searchTxtField.getText()) != -1 ||
                        r.getRequestName().indexOf(searchTxtField.getText()) != -1 ||
                        r.getUser().getName().indexOf(searchTxtField.getText()) != -1)
                    requestDtoList.add(new RequestDto(r.getRequestName(), r.getUser().getName(), r.getAddress().getAddressName(), r.getApproved()));
            }
        }
        list.clear();
        list.addAll(requestDtoList);

        userTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("user"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("address"));
        approvedTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("approved"));
        requestedTableColumn.setCellValueFactory(new PropertyValueFactory<RequestDto,String>("documentName"));
        requestsTableView.setItems(list);


    }


}
