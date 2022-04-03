package DTO;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.BoxRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Box;
import domain.Message;
public class BoxService {

 private  BoxRepository boxRepository;

 private  MessageService messageService;

 private  ActorService actorService;

 private  Validator validator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public Box getRecievedBoxByActor(Actor a){
    return this.boxRepository.getRecievedBoxByActor(a);
}


public List<Integer> getActorBoxesId(){
    this.actorService.loggedAsActor();
    List<Integer> idBoxes = new ArrayList<Integer>();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    for (int i = 0; i < actor.getBoxes().size(); i++) {
        idBoxes.add(actor.getBoxes().get(i).getId());
    }
    return idBoxes;
}


public Box getSpamBoxByActor(Actor a){
    return this.boxRepository.getSpamBoxByActor(a);
}


public List<Box> getSonsBox(Box box){
    return this.boxRepository.getSonsBox(box);
}


public Box getTrashBoxByActor(Actor a){
    return this.boxRepository.getTrashBoxByActor(a);
}


public Box getSentBoxByActor(Actor a){
    return this.boxRepository.getSentBoxByActor(a);
}


public List<Box> getCurrentBoxByMessage(Message m){
    return this.boxRepository.getCurrentBoxByMessage(m);
}


public Box getNotificationBoxByActor(Actor a){
    return this.boxRepository.getNotificationBoxByActor(a);
}


public List<Box> getActorBoxes(){
    this.actorService.loggedAsActor();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    return actor.getBoxes();
}


public Box createSystem(){
    // Crear cajas del sistema
    Box box = new Box();
    List<Message> messages = new ArrayList<Message>();
    box.setName("");
    box.setIsSystem(true);
    box.setMessages(messages);
    box.setFatherBox(null);
    return box;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createSystem"))

;
Box aux = restTemplate.getForObject(builder.toUriString(),Box.class);
return aux;
}


public Box saveSystem(Box box){
    return this.boxRepository.save(box);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveSystem"))

.queryParam("box",box)
;
Box aux = restTemplate.getForObject(builder.toUriString(),Box.class);
return aux;
}


}