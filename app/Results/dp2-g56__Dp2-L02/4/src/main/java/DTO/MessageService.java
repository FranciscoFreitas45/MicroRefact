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


public Message createNotification(String Subject,String body,String priority,String tags,Actor recipient){
    Date thisMoment = new Date();
    thisMoment.setTime(thisMoment.getTime() - 1);
    Message message = new Message();
    Actor sender = this.actorService.getActorByUsername("system");
    message.setMoment(thisMoment);
    message.setSubject(Subject);
    message.setBody(body);
    message.setPriority(priority);
    message.setReceiver(recipient);
    message.setTags(tags);
    message.setSender(sender);
    return message;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createNotification"))

.queryParam("Subject",Subject)
.queryParam("body",body)
.queryParam("priority",priority)
.queryParam("tags",tags)
.queryParam("recipient",recipient)
;
Message aux = restTemplate.getForObject(builder.toUriString(),Message.class);
return aux;
}


public Message sendMessageAnotherSender(Message message){
    Actor actorRecieved = message.getReceiver();
    List<String> spam = new ArrayList<String>();
    spam = this.configurationService.getSpamWords();
    Box boxOut = new Box();
    Box boxSpam = new Box();
    Box boxNotification = new Box();
    Message messageSaved = this.messageRepository.save(message);
    Message messageCopy = this.createNotification(messageSaved.getSubject(), messageSaved.getBody(), messageSaved.getPriority(), message.getTags(), messageSaved.getReceiver());
    Message messageCopySaved = this.messageRepository.save(messageCopy);
    boxSpam = this.boxService.getSpamBoxByActor(actorRecieved);
    boxNotification = this.boxService.getNotificationBoxByActor(actorRecieved);
    Admin system = this.adminService.getAdminByUsername("system");
    boxOut = this.boxService.getSentBoxByActor(system);
    if (this.configurationService.isStringSpam(messageSaved.getBody(), spam) || this.configurationService.isStringSpam(messageSaved.getSubject(), spam) || this.configurationService.isStringSpam(messageSaved.getTags(), spam)) {
        boxSpam.getMessages().add(messageSaved);
        this.boxService.saveSystem(boxSpam);
    } else {
        boxNotification.getMessages().add(messageSaved);
        this.boxService.saveSystem(boxNotification);
    }
    this.actorService.save(actorRecieved);
    boxOut.getMessages().add(messageCopySaved);
    this.boxService.saveSystem(boxOut);
    this.actorService.save(system);
    return messageSaved;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendMessageAnotherSender"))

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


}