package services;
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
@Transactional
@Service
public class BoxService {

@Autowired
 private  BoxRepository boxRepository;

@Autowired
 private  MessageService messageService;

@Autowired
 private  ActorService actorService;

@Autowired
 private  Validator validator;


public Box saveSystem(Box box){
    return this.boxRepository.save(box);
}


public Box getRecievedBoxByActor(Actor a){
    return this.boxRepository.getRecievedBoxByActor(a);
}


public void deleteBoxA(Box box){
    this.actorService.loggedAsActor();
    Assert.isTrue(!box.getIsSystem());
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    Assert.isTrue(actor.getBoxes().contains(box));
    final List<Box> sonBoxes = this.boxRepository.getSonsBox(box);
    if (sonBoxes.size() != 0)
        for (final Box sonBox : sonBoxes) this.deleteBox(sonBox);
    else {
        for (final Message m : box.getMessages()) this.messageService.delete(m);
        box.getMessages().removeAll(box.getMessages());
        actor.getBoxes().remove(box);
        this.boxRepository.delete(box);
        this.actorService.save(actor);
    }
// this.actorService.save(actor);
}


public Box save(Box box){
    Assert.isTrue(!box.getIsSystem());
    this.actorService.loggedAsActor();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    Box fatherBox = box.getFatherBox();
    Assert.isTrue((fatherBox == null) || actor.getBoxes().contains(fatherBox));
    Box savedBox = new Box();
    savedBox = this.boxRepository.save(box);
    actor.getBoxes().add(savedBox);
    this.actorService.save(actor);
    return savedBox;
}


public Box findOne(int id){
    return this.boxRepository.findOne(id);
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


public List<Box> findAll(){
    return this.boxRepository.findAll();
}


public void RecursiveDeleteBox(Box box){
    while (box.getIsSystem() == false) this.deleteBox(box);
}


public void deleteBoxSystem(Box box){
    this.actorService.loggedAsActor();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    List<Box> sonBoxes = this.boxRepository.getSonsBox(box);
    if (sonBoxes.size() == 0) {
        List<Message> messages = box.getMessages();
        box.getMessages().removeAll(messages);
        if (!messages.isEmpty()) {
            this.messageService.delete(messages.get(0));
        }
        this.boxRepository.save(box);
    } else {
        for (Box sonBox : sonBoxes) {
            this.deleteBox(sonBox);
        // this.actorService.save(actor);
        }
    }
}


public Box getSpamBoxByActor(Actor a){
    return this.boxRepository.getSpamBoxByActor(a);
}


public Box updateBox(Box box){
    this.actorService.loggedAsActor();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    Assert.isTrue(!box.getIsSystem());
    Box savedBox = new Box();
    savedBox = this.boxRepository.save(box);
    if (actor.getBoxes().contains(savedBox)) {
        actor.getBoxes().remove(savedBox);
        actor.getBoxes().add(savedBox);
    } else
        actor.getBoxes().add(savedBox);
    this.actorService.save(actor);
    return savedBox;
}


public List<Box> getSonsBox(Box box){
    return this.boxRepository.getSonsBox(box);
}


public void flush(){
    this.boxRepository.flush();
}


public void deleteAllBoxes(){
    // FatherBox a null con query
    Actor actor = this.actorService.loggedActor();
    Integer cont = this.boxRepository.fatherBoxesByActor(actor).size();
    List<Box> boxes = new ArrayList<Box>();
    boxes = this.boxRepository.fatherBoxesByActor(actor);
    // boxes.add(this.getRecievedBoxByActor(actor));
    for (int i = 0; i < cont; i++) this.deleteBoxEvenIsSystem(boxes.get(i));
    List<Box> deletedBoxes = new ArrayList<Box>();
    deletedBoxes = actor.getBoxes();
}


public Box getTrashBoxByActor(Actor a){
    return this.boxRepository.getTrashBoxByActor(a);
}


public Box create(String name,Box fatherBox){
    this.actorService.loggedAsActor();
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    Box box = new Box();
    List<Message> messages = new ArrayList<Message>();
    box.setName(name);
    box.setIsSystem(false);
    box.setMessages(messages);
    box.setFatherBox(fatherBox);
    List<Box> newBoxes = actor.getBoxes();
    newBoxes.add(box);
    actor.setBoxes(newBoxes);
    return box;
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
}


public Box getSentBoxByActor(Actor a){
    return this.boxRepository.getSentBoxByActor(a);
}


public List<Box> getCurrentBoxByMessage(Message m){
    return this.boxRepository.getCurrentBoxByMessage(m);
}


public Box reconstruct(Box box,BindingResult binding){
    Box result;
    List<Message> messages = new ArrayList<Message>();
    Box pururu;
    if (box.getId() == 0) {
        result = box;
        result.setIsSystem(false);
        result.setMessages(messages);
    } else {
        pururu = this.boxRepository.findOne(box.getId());
        result = box;
        result.setIsSystem(pururu.getIsSystem());
        result.setMessages(pururu.getMessages());
    }
    this.validator.validate(result, binding);
    return result;
}


public void deleteBoxEvenIsSystem(Box box){
    this.actorService.loggedAsActor();
    // Assert.isTrue(!box.getIsSystem());
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    Assert.isTrue(actor.getBoxes().contains(box));
    List<Box> sonBoxes = this.boxRepository.getSonsBox(box);
    if (sonBoxes.size() == 0) {
        for (Message m : box.getMessages()) {
            this.messageService.delete(m);
        }
        box.getMessages().removeAll(box.getMessages());
        actor.getBoxes().remove(box);
        this.boxRepository.delete(box);
        this.actorService.save(actor);
    } else {
        for (Box sonBox : sonBoxes) {
            this.deleteBox(sonBox);
        // this.actorService.save(actor);
        }
    }
}


public Box flushSave(Box box){
    return this.boxRepository.saveAndFlush(box);
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


public void deleteBox(Box box){
    this.actorService.loggedAsActor();
    Assert.isTrue(!box.getIsSystem());
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    Actor actor = this.actorService.getActorByUsername(userAccount.getUsername());
    Assert.isTrue(actor.getBoxes().contains(box));
    final List<Box> sonBoxes = this.boxRepository.getSonsBox(box);
    if (sonBoxes.size() == 0 && box.getIsSystem() == false) {
        for (final Message m : box.getMessages()) this.messageService.delete(m);
        actor.getBoxes().remove(box);
        box.setFatherBox(null);
        this.boxRepository.delete(box);
        this.actorService.save(actor);
    } else {
        for (final Box sonBox : sonBoxes) this.deleteBox(sonBox);
        this.deleteBox(box);
    }
}


}