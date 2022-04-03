package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageServiceController {

 private MessageService messageservice;


@PutMapping
("/sendNotificationDropOut")
public void sendNotificationDropOut(@RequestParam(name = "bro") Brotherhood bro){
messageservice.sendNotificationDropOut(bro);
}


@GetMapping
("/sendMessageAnotherSender")
public Message sendMessageAnotherSender(@RequestParam(name = "message") Message message){
  return messageservice.sendMessageAnotherSender(message);
}


@PutMapping
("/updateSendedMessageByLogguedActor")
public void updateSendedMessageByLogguedActor(){
messageservice.updateSendedMessageByLogguedActor();
}


@PutMapping
("/updateReceivedMessageToLogguedActor")
public void updateReceivedMessageToLogguedActor(){
messageservice.updateReceivedMessageToLogguedActor();
}


@PutMapping
("/deleteAllMessageFromActor")
public void deleteAllMessageFromActor(){
messageservice.deleteAllMessageFromActor();
}


@PutMapping
("/sendNotificationBroEnrolMem")
public void sendNotificationBroEnrolMem(@RequestParam(name = "mem") Member mem){
messageservice.sendNotificationBroEnrolMem(mem);
}


}