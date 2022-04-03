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

  String url = "http://5";


public List<Message> getReceivedMessagesToActor(int idActor){
    return this.messageRepository.getReceivedMessagesToActor(idActor);
}


public List<Message> getSendedMessagesByActor(int actorId){
    return this.messageRepository.getSendedMessagesByActor(actorId);
}


public List<Message> getMessagesByBox(Box b){
    return b.getMessages();
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


}