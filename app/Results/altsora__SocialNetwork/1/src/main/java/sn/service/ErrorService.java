package sn.service;
 import org.springframework.stereotype.Service;
import sn.api.response.ErrorResponse;
@Service
public class ErrorService {

 private  String BAD_REQUEST;

 private  String INVALID_REQUEST;


public ErrorResponse userNotFoundInDialog(long personId,long dialogId){
    return ErrorResponse.builder().error(BAD_REQUEST).errorDescription(String.format("User (ID = %d) not found in dialog (ID = %d)", personId, dialogId)).build();
}


public ErrorResponse unauthorized(){
    return ErrorResponse.builder().error(INVALID_REQUEST).errorDescription("Person not authorized").build();
}


public ErrorResponse dialogNotFound(long dialogId){
    return ErrorResponse.builder().error(BAD_REQUEST).errorDescription("Dialog with ID = " + dialogId + " not found").build();
}


public ErrorResponse personNotFoundById(long personId){
    return ErrorResponse.builder().error(BAD_REQUEST).errorDescription("Person with ID = " + personId + " not found").build();
}


public ErrorResponse messageNotFound(long messageId){
    return ErrorResponse.builder().error(BAD_REQUEST).errorDescription("Message with ID = " + messageId + " not found").build();
}


public ErrorResponse unknownLikeType(String likeType){
    return ErrorResponse.builder().error(BAD_REQUEST).errorDescription("Unknown like type: " + likeType).build();
}


}