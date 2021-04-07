package Service;

import Utils.AplicationUtils;
import Utils.ControllerUtils;
import Validator.DocumentValidator;
import Validator.UserValidator;
import entity.Document;
import entity.User;
import repository.DocumentRepo;
import repository.UserRepo;
import Exception.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class DocumentService {

    public static final String INVALID_ID_MESSAGE = "id is null or empty";

    private DocumentRepo documentRepo;

    public DocumentService() {
        this.documentRepo = new DocumentRepo();
    }

    public Document getDocumentById(String id) {
        Document document = new Document();
        try {
            document = documentRepo.findByID(id);
        } catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("No document with ID: "+ id);
        }

        return document;
    }



    public void addDocument(Document document){
        try {
            DocumentValidator.validateAddNewDocumentFlow(document);//poate fii pusa in controller sa sa o prindem mai repede, up to me
            DocumentValidator.validateDocumentNotDuplicate(document);
            documentRepo.insertNewDocument(document);
            ControllerUtils.alert2(ControllerUtils.DocumentWasSucsessfullyAdded);
            AplicationUtils.DocumentListFlahRefresh = true;
        }
        catch(RuntimeException illegalArgumentException){
            ControllerUtils.alert1(illegalArgumentException.getMessage());
        }
    }

    public List<String> getDocumentNames(){
        List<Document> documents = documentRepo.getDocuments();
        List<String> docs = new ArrayList<String>();
        docs.clear();
        for(Document doc : documents){
            docs.add(doc.getName());
        }
           return docs;
    }

    public void deleteByName(String selectedDocument) {

        Document document = new Document();
        try{
            document = documentRepo.findByName(selectedDocument);
            documentRepo.deleteById(document.getId());
            ControllerUtils.alert2(ControllerUtils.DocumentWasSucsessfullyDeleted);
        }catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("Cannot delete document");
        }
    }
}
