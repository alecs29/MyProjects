package Validator;

import entity.Address;
import entity.Request;

public class RequestValidator {

    public static void validateAddNewRequestFlow(Request request){

        if(request == null){
            throw new IllegalArgumentException("request is empty");
        }


    }
}
