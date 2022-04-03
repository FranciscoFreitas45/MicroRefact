package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageServiceController {

 private MessageService messageservice;


@GetMapping
("/createNotification")
public Message createNotification(@RequestParam(name = "Subject") String Subject,@RequestParam(name = "body") String body,@RequestParam(name = "priority") String priority,@RequestParam(name = "tags") String tags,@RequestParam(name = "recipient") Actor recipient){
  return messageservice.createNotification(Subject,body,priority,tags,recipient);
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


@GetMapping
("/sendMessageBroadcasted")
public Message sendMessageBroadcasted(@RequestParam(name = "message") Message message){
  return messageservice.sendMessageBroadcasted(message);
}


@GetMapping
("/create")
public Message create(@RequestParam(name = "Subject") String Subject,@RequestParam(name = "body") String body,@RequestParam(name = "priority") String priority,@RequestParam(name = "recipient") Actor recipient){
  return messageservice.create(Subject,body,priority,recipient);
}


@GetMapping
("/findOne")
public Message findOne(@RequestParam(name = "id") int id){
  return messageservice.findOne(id);
}


@PutMapping
("/sendNotificationDropOut")
public void sendNotificationDropOut(@RequestParam(name = "bro") Brotherhood bro){
messageservice.sendNotificationDropOut(bro);
}


@PutMapping
("/sendNotificationBroEnrolMem")
public void sendNotificationBroEnrolMem(@RequestParam(name = "mem") Member mem){
messageservice.sendNotificationBroEnrolMem(mem);
}


}