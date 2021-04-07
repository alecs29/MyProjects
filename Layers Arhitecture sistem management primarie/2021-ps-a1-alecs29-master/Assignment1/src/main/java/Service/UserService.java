package Service;

import Dto.UserDto;
import Mappers.UserMapper;
import Utils.AplicationUtils;
import Utils.ControllerUtils;
import Validator.AddressValidator;
import Validator.UserValidator;
import entity.Address;
import entity.Request;
import entity.User;
import repository.AddressRepo;
import repository.UserRepo;
import Exception.*;

import java.util.List;
import java.util.UUID;

public class UserService {

    public static final String INVALID_ID_MESSAGE = "id is null or empty";

    private UserRepo userRepo;

    public UserService() {
        this.userRepo = new UserRepo();
    }

    public User getUserById(String id) {
        User user = new User();
        try {
            user = userRepo.findByID(id);
        } catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("No User with ID: "+ id);
        }

        return user;
    }

    public User getUserByName(String name){
        User user=new User();
        try{
            //user=userRepo.findByName(name);
            List<User> userList = userRepo.getUsers();

            for(int i=0 ; i<userList.size(); i++){
                if(name.equals(userList.get(i).getName())){
                    user =  userRepo.findByID(userList.get(i).getId());
                    break;
                }
            }
        }catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("No User with name: "+ name);
        }

        return user;
    }

    public User getUserByEmail(String email){
        User user = new User();
        try{
            //user=userRepo.findByName(name);
            List<User> userList = userRepo.getUsers();

            for(int i=0 ; i<userList.size(); i++){
                if(email.equals(userList.get(i).getEmail())){
                    user =  userRepo.findByID(userList.get(i).getId());
                    break;
                }
            }
        }catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("No User with email: "+ email);
        }

        return user;
    }

    public void deleteById(String id){
        User user=new User();
        try{
            userRepo.deleteById(id);
        }catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("Cannot delete user with id: " + id);
        }

    }

    public void addUser(User user, Address address){
        AddressService addressService = new AddressService();
        AddressRepo addressRepo = new AddressRepo();
        try {
            UserValidator.validateAddNewUserFlow(user);
            UserValidator.validateUserNotDuplicate(user);

            AddressValidator.validateAddNewAddressFlow(address);
            AddressValidator.validateAddressNotDuplicate(address);

            userRepo.insertNewUser(user);

            addressRepo.insertNewAddress(address);
            //addressService.addAddress(address);

            ControllerUtils.alert2(ControllerUtils.userWasSucsessfullyAdded);
        }
        catch(RuntimeException illegalArgumentException){
            ControllerUtils.alert1(illegalArgumentException.getMessage());
        }


    }

    public List<User> getUsers() {
        List<User> users;
        try {
            users  = userRepo.getUsers();
        } catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("users counld not be found");

        }

        return users;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
