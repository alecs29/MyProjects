package Validator;

import Service.AddressService;
import Utils.ControllerUtils;
import entity.Address;
import entity.User;
import repository.AddressRepo;
import Exception.*;

public class AddressValidator {
    public static void validateAddNewAddressFlow(Address address){

        if(address == null){
            throw new IllegalArgumentException("address is empty");
        }


    }

    public static void validateAddressNotDuplicate(Address address){

        AddressRepo addressRepo = new AddressRepo();
        if(addressRepo.findByName(address.getAddressName()) != null)
        {
            throw new MyDuplicate(ControllerUtils.AddressDuplicateError);

        }

    }
}
