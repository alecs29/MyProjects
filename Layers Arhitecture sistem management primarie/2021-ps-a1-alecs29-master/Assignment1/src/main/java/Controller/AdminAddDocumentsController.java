package Controller;

import Service.DocumentService;
import Utils.AplicationUtils;
import entity.Document;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminAddDocumentsController implements Initializable {

    @FXML
    private TextField titleTxtField;

    @FXML
    private TextField subjectTxtField;

    User currentUser;
    DocumentService documentService = new DocumentService();
    List<String> docStrings;


    @FXML
    private ListView<String> documentListView;

    @FXML
    void addDocumentButtonPush(ActionEvent event) {
        DocumentService documentService = new DocumentService();
        Document document = new Document();

        document.setId(AplicationUtils.generateUUID());
        document.setName(titleTxtField.getText());
        document.setUser(currentUser);
        document.setSubject(subjectTxtField.getText());

        documentService.addDocument(document);

        if(AplicationUtils.DocumentListFlahRefresh) {
            docStrings.add(document.getName());
            documentListView.getItems().clear();
            documentListView.getItems().addAll(docStrings);
            documentListView.setVisible(true);
        }

        AplicationUtils.DocumentListFlahRefresh = false;

    }

    @FXML
    void backToAdminMainMenuButtonPush(ActionEvent event) throws IOException {
        Parent viewParent = FXMLLoader.load(getClass().getResource("/frames/AdminMainMenu.fxml"));
        Scene viewScene =  new Scene(viewParent);

        //getting the stage information
        Stage windwow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        windwow.setScene(viewScene);
        windwow.show();
    }

    @FXML
    void deleteSelectedRowButtonPush(ActionEvent event) {
       String selectedDocument = documentListView.getSelectionModel().getSelectedItem();
       documentService.deleteByName(selectedDocument);

       docStrings.remove(documentListView.getSelectionModel().getSelectedIndex());

       documentListView.getItems().clear();
       documentListView.getItems().addAll(docStrings);
       documentListView.setVisible(true);

    }

    void initCurrentUser(User currentUser){
        this.currentUser = currentUser;
        //userGrreet.setText("Hello " + this.currentUser.getName());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> documentNames = documentService.getDocumentNames();
        this.docStrings = documentNames;
        documentListView.getItems().addAll(docStrings);
    }
}
