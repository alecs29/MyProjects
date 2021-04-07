package Service;

import Utils.AplicationUtils;
import Utils.ControllerUtils;
import Validator.AddressValidator;
import Validator.UserValidator;
import entity.Address;
import entity.Document;
import entity.Request;
import entity.User;
import Exception.*;
import repository.AddressRepo;
import repository.UserRepo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AddressService {

    AddressRepo addressRepo = new AddressRepo();



    public boolean verify3RequestsPerYearRule(Address address){

        Address refreshAddress = new Address();
        int count = 0;

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = AplicationUtils.convert(utilDate);


        int currentYear = AplicationUtils.convertYearTimeStampToInt(sqlDate);
        int requestsMadeThisYear = 0;

        AddressService addressService = new AddressService();
        RequestService requestService = new RequestService();

        List<Request> requests = requestService.getRequests();


        for(Request r: requests){
            if(r.getAddress().getId().equals(address.getId())) {
                int year = r.getRequestDate();
                if (year == currentYear)
                    requestsMadeThisYear++;
            }
        }

        if(requestsMadeThisYear == 3){
            addressRepo.updateRequestsPerAddress(address,0);
            return false;
        }
        else if(requestsMadeThisYear== 2){
            addressRepo.updateRequestsPerAddress(address,1);
        }
        else if(requestsMadeThisYear == 1){
            addressRepo.updateRequestsPerAddress(address,2);
        }
        else if(requestsMadeThisYear == 0){
            addressRepo.updateRequestsPerAddress(address,3);
        }


        return true;
    }

    public List<String> convertAddressesToString(List<Address> addresses){

        UserRepo userRepo = new UserRepo();
        List<String> names = new ArrayList<String>();
        for(Address a: addresses){
            names.add(a.getAddressName());
        }

        return names;
    }



    public void deleteById(String id){
        Address address = new Address();
        try{
            addressRepo.deleteById(id);
            ControllerUtils.alert2(ControllerUtils.AddressWasSucsessfullyDeleted);
        }catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("Cannot delete address");
        }

    }

    public void addAddressWithMessage(Address address){
        try {
            AddressValidator.validateAddNewAddressFlow(address);
            AddressValidator.validateAddressNotDuplicate(address);
            addressRepo.insertNewAddress(address);
            ControllerUtils.alert2(ControllerUtils.AddressWasSucsessfullyAdded);
        }
        catch(Exception illegalArgumentException){
            ControllerUtils.alert1(illegalArgumentException.getMessage());
        }
    }


    public List<Address> getAddresses(){
        try {
            return addressRepo.getAddresses();
        }
        catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("The addresses could not be found in database");
        }
    }


}
