package sn.controller;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import sn.api.requests.MessageSendRequest;
import sn.api.response.AbstractResponse;
import sn.api.response.DialogResponse;
import sn.api.response.ServiceResponse;
import sn.service.DialogService;
import java.util.List;
@RestController
@RequestMapping("/dialogs")
@RequiredArgsConstructor
public class DialogController {

@Autowired
 private  DialogService dialogService;

@JsonProperty("user_ids")
 private List<Long> userIds;


@DeleteMapping("/{id}")
public ResponseEntity<ServiceResponse<DialogResponse>> deleteDialogById(long dialogId){
    return dialogService.deleteDialog(dialogId);
}


@DeleteMapping("/{id}/users")
public ResponseEntity<ServiceResponse<DialogResponse>> deleteUsers(long dialogId,UserIdsRequest request){
    return dialogService.deleteUsersFromDialog(dialogId, request);
}


@PutMapping("/{dialog_id}/messages/{message_id}/read")
public ResponseEntity<ServiceResponse<AbstractResponse>> readMessage(long dialogId,long messageId){
    return dialogService.readMessage(dialogId, messageId);
}


@GetMapping("/{id}/activity/{user_id}")
public ResponseEntity<ServiceResponse<AbstractResponse>> getLastActivity(long dialogId,long personId){
    return dialogService.getLastActivity(dialogId, personId);
}


@PostMapping("/{id}/messages")
public ResponseEntity<ServiceResponse<AbstractResponse>> sendMessage(long dialogId,MessageSendRequest sendRequest){
    return dialogService.sendMessage(dialogId, sendRequest);
}


@PostMapping
public ResponseEntity<ServiceResponse<DialogResponse>> createDialog(UserIdsRequest request){
    return dialogService.createDialog(request);
}


@PostMapping("/{id}/activity/{user_id}")
public ResponseEntity<ServiceResponse<AbstractResponse>> changeTypingStatus(long dialogId,long personId){
    return dialogService.changeTypingStatus(dialogId, personId);
}


@GetMapping("/{id}/messages")
public ResponseEntity<ServiceResponse<DialogResponse>> getMessages(long dialogId,String query,int offset,int itemPerPage){
    return dialogService.getDialogMessages(dialogId, query, offset, itemPerPage);
}


@PutMapping("/{id}/users")
public ResponseEntity<ServiceResponse<DialogResponse>> addUsers(long dialogId,UserIdsRequest request){
    return dialogService.addUsersToDialog(dialogId, request);
}


@DeleteMapping("/{dialog_id}/messages/{message_id}")
public ResponseEntity<ServiceResponse<AbstractResponse>> removeMessage(long dialogId,long messageId){
    return dialogService.removeMessage(dialogId, messageId);
}


@GetMapping("/unreaded")
public ResponseEntity<ServiceResponse<DialogResponse>> getUnreadedMessagesCount(){
    return dialogService.getUnreadMessagesCount();
}


@PutMapping("/{dialog_id}/messages/{message_id}")
public ResponseEntity<ServiceResponse<AbstractResponse>> editMessage(long dialogId,long messageId,MessageSendRequest messageSendRequest){
    return dialogService.editMessage(dialogId, messageId, messageSendRequest);
}


@PutMapping("/{dialog_id}/messages/{message_id}/recover")
public ResponseEntity<ServiceResponse<AbstractResponse>> recoverMessage(long dialogId,long messageId){
    return dialogService.recoverMessage(dialogId, messageId);
}


@GetMapping
public ResponseEntity<ServiceResponse<DialogResponse>> getUserDialogList(String query,int offset,int itemPerPage){
    return dialogService.findPersonDialogsWithQuery(query, offset, itemPerPage);
}


@PutMapping("/{id}/users/join")
public ResponseEntity<ServiceResponse<DialogResponse>> joinToDialog(long dialogId,String link){
    return dialogService.joinUserToDialog(dialogId, link);
}


@GetMapping("/{id}/users/invite")
public ResponseEntity<ServiceResponse<DialogResponse>> invite(long dialogId){
    return dialogService.createInviteLink(dialogId);
}


}