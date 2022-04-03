package DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Actor;
import domain.Admin;
import domain.Box;
import domain.Brotherhood;
import domain.Member;
import domain.Message;
import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import Interface.ConfigurationService;
import Interface.MemberService;
import Interface.AdminService;
import Interface.BrotherhoodService;
public class MessageService {

 private  MessageRepository messageRepository;

 private  ActorService actorService;

 private  BoxService boxService;

 private  ConfigurationService configurationService;

 private  MemberService memberService;

 private  AdminService adminService;

 private  BrotherhoodService brotherhoodService;

 private  Validator validator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public List<Message> getReceivedMessagesToActor(int idActor){
    return this.messageRepository.getReceivedMessagesToActor(idActor);
}


public List<Message> getSendedMessagesByActor(int actorId){
    return this.messageRepository.getSendedMessagesByActor(actorId);
}


public List<Message> getMessagesByBox(Box b){
    return b.getMessages();
}


public Message sendMessageBroadcasted(Message message){
    this.actorService.loggedAsActor();
    Box boxNotification = new Box();
    Box boxSent = new Box();
    Message messageSaved = this.messageRepository.saveAndFlush(message);
    Message messageCopy = this.create(messageSaved.getSubject(), messageSaved.getBody(), messageSaved.getPriority(), messageSaved.getReceiver());
    messageCopy.setTags(messageSaved.getTags());
    Message messageCopySaved = this.messageRepository.save(messageCopy);
    boxSent = this.boxService.getSentBoxByActor(messageSaved.getSender());
    boxNotification = this.boxService.getNotificationBoxByActor(messageSaved.getReceiver());
    // Guardar la box con ese mensaje;
    boxNotification.getMessages().add(messageCopySaved);
    boxSent.getMessages().add(messageSaved);
    // boxRecieved.setMessages(list);
    this.boxService.saveSystem(boxSent);
    this.boxService.saveSystem(boxNotification);
    this.actorService.save(messageSaved.getSender());
    this.actorService.flushSave(messageSaved.getReceiver());
    return messageSaved;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendMessageBroadcasted"))

.queryParam("message",message)
;
Message aux = restTemplate.getForObject(builder.toUriString(),Message.class);
return aux;
}


public void updateSendedMessageByLogguedActor(){
    Actor actor = this.actorService.loggedActor();
    Actor deleted = this.actorService.getActorByUsername("DELETED");
    List<Message> messages = this.messageRepository.getSendedMessagesByActor(actor.getId());
    for (Message m : messages) {
        m.setSender(deleted);
        this.messageRepository.save(m);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateSendedMessageByLogguedActor"))

;
restTemplate.put(builder.toUriString(),null);
}


public void updateReceivedMessageToLogguedActor(){
    Actor actor = this.actorService.loggedActor();
    Actor deleted = this.actorService.getActorByUsername("DELETED");
    List<Message> messages = this.messageRepository.getReceivedMessagesToActor(actor.getId());
    for (Message m : messages) {
        m.setReceiver(deleted);
        this.messageRepository.save(m);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateReceivedMessageToLogguedActor"))

;
restTemplate.put(builder.toUriString(),null);
}


public void deleteAllMessageFromActor(){
    Actor a = this.actorService.loggedActor();
    List<Message> messages = this.messageRepository.getAllMessagesFromActor(a.getId());
    for (Message m : messages) this.messageRepository.delete(m);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllMessageFromActor"))

;
restTemplate.put(builder.toUriString(),null);
}


}