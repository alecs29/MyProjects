package Service;

import Dto.RequestDto;
import Utils.ControllerUtils;
import Validator.RequestValidator;
import entity.Address;
import entity.Request;
import entity.User;
import repository.RequestRepo;
import java.util.List;
import Exception.*;

public class RequestService {

    RequestRepo requestRepo = new RequestRepo();


    public void addVerifiedRequest(Request request, Address address){
        AddressService addressService = new AddressService();
        RequestService requestService = new RequestService();
        List<Request> requests =  requestService.getRequests();
        boolean bool = true;
        for(Request r : requests){
            if(request.getRequestName().equals(r.getRequestName()) && request.getAddress().getId().equals(r.getAddress().getId())){
                bool = false;
            }
        }

        try {
            if(bool) {
                if (addressService.verify3RequestsPerYearRule(address)) {

                    RequestValidator.validateAddNewRequestFlow(request);//poate fii pusa in controller sa sa o prindem mai repede, up to me
                    requestRepo.insertNewRequest(request);
                } else ControllerUtils.alert1(ControllerUtils.ThreeRequestsFlag);
            } else ControllerUtils.alert1(ControllerUtils.RequestSameAdressError);

        }
        catch(Exception illegalArgumentException){
            ControllerUtils.alert1(illegalArgumentException.getMessage());
            illegalArgumentException.printStackTrace();
        }


    }

    public List<Request> getRequests() {
        List<Request> requests;
        try {
            requests  = requestRepo.getRequests();
        } catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("requests counld not be found");

        }

        return requests;
    }

    public void deleteById(String id){
        Request request = new Request();
        try{
            requestRepo.deleteById(id);
        }catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("Cannot delete request");
        }

    }

    public void updateRequestApproved(RequestDto requestDto){

        RequestRepo requestRepo = new RequestRepo();
        Request request = new Request();
        List<Request> requests = requestRepo.getRequests();

        for(Request r:requests){
            if(requestDto.getAddress().equals(r.getAddress().getAddressName()) && requestDto.getDocumentName().equals(r.getRequestName())){
                request = r;
            }
        }

        try{
            requestRepo.updateRequestApproved(request,"approved");
        }catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("Cannot delete request");
        }

    }

    public void deleteByName(String name){
        Request request = new Request();

        try{
            request = requestRepo.findByName(name);
            requestRepo.deleteById(request.getId());
        }catch (NullPointerException nullPointerException){
            throw new EntityNotExistException("Cannot delete request");
        }

    }

}
