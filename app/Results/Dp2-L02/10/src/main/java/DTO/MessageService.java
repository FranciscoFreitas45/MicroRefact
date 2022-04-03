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
import DTO.BrotherhoodService;
import DTO.Brotherhood;
import DTO.ConfigurationService;
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


public void sendNotificationDropOut(Brotherhood bro){
    Member loggedMember = this.memberService.loggedMember();
    Admin admin = this.adminService.getSystem();
    Box sentAdmin = this.boxService.getSentBoxByActor(admin);
    Box notMem = this.boxService.getNotificationBoxByActor(loggedMember);
    Box notBro = this.boxService.getNotificationBoxByActor(bro);
    Message messageBro = new Message();
    Message messageMem = new Message();
    messageBro = this.createNotification("Drop out notification / Notificacion de salida", "The user " + loggedMember.getUserAccount().getUsername() + " has dropped out the brotherhood. / El usuario " + loggedMember.getUserAccount().getUsername() + " ha dejado la hermandad.", "HIGH", "DROP OUT", bro);
    messageMem = this.createNotification("Drop out notification / Notificacion de salida", "You have dropped out the brotherhood " + bro.getTitle() + ". / Has dejado la hermandad " + bro.getTitle() + ".", "HIGH", "DROP OUT", loggedMember);
    /*
		 * Message copyBro = new Message();
		 * Message copyMem = new Message();
		 * 
		 * copyBro = this.createNotification(messageBro.getSubject(), messageBro.getBody(), messageBro.getPriority(), messageBro.getTags(), messageBro.getSender());
		 * copyMem = this.createNotification(messageMem.getSubject(), messageMem.getBody(), messageMem.getPriority(), messageMem.getTags(), messageMem.getSender());
		 * 
		 * copyBro.setReceiver(bro);
		 * copyMem.setReceiver(loggedMember);
		 * 
		 * Message saveBro = this.messageRepository.save(copyBro);
		 * Message saveMem = this.messageRepository.save(copyMem);
		 */
    /*
		 * List<Message> messAdmin = sentAdmin.getMessages();
		 * List<Message> messMem = notMem.getMessages();
		 * List<Message> messBro = notBro.getMessages();
		 * 
		 * messAdmin.add(saveBro);
		 * messAdmin.add(saveMem);
		 * 
		 * messMem.add(saveMem);
		 * messBro.add(saveBro);
		 * 
		 * sentAdmin.setMessages(messAdmin);
		 * notMem.setMessages(messMem);
		 * notBro.setMessages(messBro);
		 * this.boxService.flushSave(sentAdmin);
		 * this.boxService.flushSave(notMem);
		 * this.boxService.flushSave(notBro);
		 * 
		 * this.actorService.save(messageBro.getSender());
		 * this.actorService.save(messageMem.getSender());
		 * this.actorService.save(messageBro.getReceiver());
		 * this.actorService.save(messageMem.getReceiver());
		 */
    this.sendMessageAnotherSender(messageBro);
    this.sendMessageAnotherSender(messageMem);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendNotificationDropOut"))

.queryParam("bro",bro)
;
restTemplate.put(builder.toUriString(),null);
}


public void sendNotificationBroEnrolMem(Member mem){
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Admin admin = this.adminService.getSystem();
    Box sentAdmin = this.boxService.getSentBoxByActor(admin);
    Box notMem = this.boxService.getNotificationBoxByActor(mem);
    Box notBro = this.boxService.getNotificationBoxByActor(loggedBrotherhood);
    Message messageBro = null;
    Message messageMem = null;
    messageBro = this.createNotification("Enrol notification / Notificacion de inscripcion", "You have accepted the user " + mem.getUserAccount().getUsername() + " to the brotherhood. / Has aceptado al usuario " + mem.getUserAccount().getUsername() + " a la hermandad.", "HIGH", "ENROLMENT", loggedBrotherhood);
    messageMem = this.createNotification("Enrol notification / Notificacion de inscripcion", "You have been accepted into the brotherhood " + loggedBrotherhood.getTitle() + ". / Has sido aceptado en la hermandad " + loggedBrotherhood.getTitle() + ".", "HIGH", "ENROLMENT", mem);
    /*
		 * this.messageRepository.save(messageBro);
		 * this.messageRepository.save(messageMem);
		 * Message copyBro = new Message();
		 * Message copyMem = new Message();
		 * copyBro = this.createNotification(messageBro.getSubject(), messageBro.getBody(), messageBro.getPriority(), messageBro.getTags(), messageBro.getSender());
		 * copyMem = this.createNotification(messageMem.getSubject(), messageMem.getBody(), messageMem.getPriority(), messageMem.getTags(), messageMem.getSender());
		 * copyBro.setReceiver(loggedBrotherhood);
		 * copyMem.setReceiver(mem);
		 * Message saveBro = this.messageRepository.save(copyBro);
		 * Message saveMem = this.messageRepository.save(copyMem);
		 * 
		 * List<Message> messAdmin = sentAdmin.getMessages();
		 * List<Message> messMem = notMem.getMessages();
		 * List<Message> messBro = notBro.getMessages();
		 * messAdmin.add(saveBro);
		 * messAdmin.add(saveMem);
		 * messMem.add(saveMem);
		 * messBro.add(saveBro);
		 * sentAdmin.setMessages(messAdmin);
		 * notMem.setMessages(messMem);
		 * notBro.setMessages(messBro);
		 * this.boxService.flushSave(sentAdmin);
		 * this.boxService.flushSave(notMem);
		 * this.boxService.flushSave(notBro);
		 * 
		 * this.actorService.save(messageBro.getSender());
		 * this.actorService.save(messageMem.getSender());
		 * this.actorService.save(messageBro.getReceiver());
		 * this.actorService.save(messageMem.getReceiver());
		 */
    this.sendMessageAnotherSender(messageBro);
    this.sendMessageAnotherSender(messageMem);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendNotificationBroEnrolMem"))

.queryParam("mem",mem)
;
restTemplate.put(builder.toUriString(),null);
}


}