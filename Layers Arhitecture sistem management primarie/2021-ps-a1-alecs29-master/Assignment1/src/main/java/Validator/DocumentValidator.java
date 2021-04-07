package Validator;

import Utils.ControllerUtils;
import entity.Address;
import entity.Document;
import entity.User;
import repository.DocumentRepo;
import Exception.*;
import repository.UserRepo;

public class DocumentValidator {


    public static void validateAddNewDocumentFlow(Document document){

        DocumentRepo documentRepo = new DocumentRepo();

        if(document == null){
            throw new IllegalArgumentException("document is empty");
        }
         if(documentRepo.findByName(document.getName()) != null) {
            throw new DocumentDuplicate("document already exists");
         }



    }

    public static void validateDocumentNotDuplicate(Document document){

        DocumentRepo documentRepo = new DocumentRepo();
        if(documentRepo.findByName(document.getName()) != null)
        {
            throw new MyDuplicate(ControllerUtils.DocumentDuplicateError);
        }

    }
}
