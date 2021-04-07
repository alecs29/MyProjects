package Validator;

import Utils.ControllerUtils;
import entity.User;
import repository.UserRepo;
import Exception.*;

public class UserValidator {

    public static void validateAddNewUserFlow(User user){

        UserRepo userRepo = new UserRepo();

        if(user == null){
            throw new IllegalArgumentException("User is empty");
        }

        if(user.getName().length() < 2 || user.getPassword().length() < 2){
            throw new IllegalArgumentException(ControllerUtils.LengthAccountError);
        }


    }
    public static void validateUserNotDuplicate(User user){

        UserRepo userRepo = new UserRepo();
        if(userRepo.findByEmail(user.getEmail()) != null)
        {
            throw new MyDuplicate(ControllerUtils.userDuplicateError);
        }

    }
}
