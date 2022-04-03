package controllers;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.BoxService;
import services.ConfigurationService;
import services.MessageService;
import domain.Actor;
import domain.Box;
import domain.Message;
@Controller
@RequestMapping("/message/actor")
public class MessageController extends AbstractController{

@Autowired
 private  MessageService messageService;

@Autowired
 private  BoxService boxService;

@Autowired
 private  ActorService actorService;

@Autowired
 private  ConfigurationService configurationService;


@RequestMapping(value = "/createmove", method = RequestMethod.GET)
public ModelAndView createMove(){
    this.actorService.loggedAsActor();
    ModelAndView result;
    Message message;
    message = this.messageService.create();
    result = this.createEditModelAndViewMove(message);
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView edit(int rowId){
    this.actorService.loggedAsActor();
    ModelAndView result;
    Message message;
    message = this.messageService.findOne(rowId);
    Assert.notNull(message);
    result = this.createEditModelAndView(message);
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
public ModelAndView save(domain.Message messageTest,BindingResult binding){
    this.actorService.loggedAsActor();
    ModelAndView result;
    domain.Message savedMessage;
    List<Box> boxes;
    Box box;
    UserAccount userAccount = LoginService.getPrincipal();
    messageTest = this.messageService.reconstruct(messageTest, binding);
    Assert.isTrue(userAccount.getUsername().equals(messageTest.getSender().getUserAccount().getUsername()));
    if (binding.hasErrors())
        result = this.createEditModelAndView(messageTest);
    else
        try {
            savedMessage = this.messageService.sendMessage(messageTest);
            boxes = this.boxService.getCurrentBoxByMessage(savedMessage);
            box = boxes.get(0);
            result = new ModelAndView("redirect:list.do?boxId=" + box.getId());
        } catch (Throwable oops) {
            result = this.createEditModelAndView(messageTest, "message.commit.error");
        }
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView create(){
    this.actorService.loggedAsActor();
    ModelAndView result;
    Message message;
    message = this.messageService.create();
    result = this.createEditModelAndView(message);
    return result;
}


@RequestMapping(value = "/move", method = RequestMethod.GET)
public ModelAndView update(int messageId,int boxId){
    this.actorService.loggedAsActor();
    ModelAndView result;
    Message message;
    Box box;
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    message = this.messageService.findOne(messageId);
    box = this.boxService.findOne(boxId);
    try {
        this.messageService.updateMessage(message, box);
        result = new ModelAndView("redirect:list.do?boxId=" + boxId);
    } catch (Throwable oops) {
        result = this.createEditModelAndViewMove(message, "message.commit.error");
    }
    return result;
}


@RequestMapping(value = "/copy", method = RequestMethod.GET)
public ModelAndView copy(int messageId,int boxId){
    ModelAndView result;
    Message message;
    Box box;
    message = this.messageService.findOne(messageId);
    box = this.boxService.findOne(boxId);
    try {
        this.messageService.copyMessage(message, box);
        result = new ModelAndView("redirect:list.do?boxId=" + boxId);
    } catch (Throwable oops) {
        result = this.createEditModelAndViewMove(message, "message.commit.error");
    }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(int boxId){
    String locale = LocaleContextHolder.getLocale().getLanguage();
    this.actorService.loggedAsActor();
    Box box = new Box();
    box = this.boxService.findOne(boxId);
    UserAccount userAccount = LoginService.getPrincipal();
    Actor a = this.actorService.getActorByUsername(userAccount.getUsername());
    if (!(this.actorService.getlistOfBoxes(a).contains(box))) {
        Box boxReturn = this.actorService.getlistOfBoxes(a).get(0);
        return new ModelAndView("redirect:list.do?boxId=" + boxReturn.getId());
    }
    List<String> priorityName = new ArrayList<>();
    List<String> priority = this.configurationService.getConfiguration().getPriorityLvl();
    if (locale == "en")
        priorityName = this.configurationService.getConfiguration().getPriorityLvl();
    else if (locale == "es")
        priorityName = this.configurationService.getConfiguration().getPriorityLvlSpa();
    ModelAndView result;
    List<Message> messages;
    Actor actor = new Actor();
    List<Box> boxes = new ArrayList<Box>();
    List<Integer> idBoxes = new ArrayList<Integer>();
    idBoxes = this.boxService.getActorBoxesId();
    actor = this.actorService.getActorByUsername(userAccount.getUsername());
    boxes = this.actorService.getlistOfBoxes(actor);
    messages = this.messageService.getMessagesByBox(box);
    result = new ModelAndView("message/actor/list");
    result.addObject("messages", messages);
    result.addObject("boxId", boxId);
    result.addObject("boxes", boxes);
    result.addObject("locale", locale);
    result.addObject("priorityName", priorityName);
    result.addObject("priority", priority);
    result.addObject("requestURI", "message/actor/list.do");
    return result;
}


@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
public ModelAndView delete(Message message,BindingResult binding){
    this.actorService.loggedAsActor();
    UserAccount userAccount = LoginService.getPrincipal();
    ModelAndView result;
    List<Box> boxes;
    Box box;
    boxes = this.boxService.getCurrentBoxByMessage(message);
    box = boxes.get(0);
    message = this.messageService.reconstruct(message, binding);
    if (!(userAccount.getUsername().equals(message.getSender().getUserAccount().getUsername()) || userAccount.getUsername().equals(message.getReceiver().getUserAccount().getUsername())))
        return new ModelAndView("redirect:list.do?boxId=" + box.getId());
    try {
        this.messageService.deleteMessageToTrashBox(message);
        result = new ModelAndView("redirect:list.do?boxId=" + box.getId());
    } catch (Throwable oops) {
        result = this.createEditModelAndView(message, "message.commit.error");
    }
    return result;
}


public ModelAndView createEditModelAndView(Message messageTest,String messageCode){
    ModelAndView result;
    UserAccount userAccount = LoginService.getPrincipal();
    String username = userAccount.getUsername();
    Actor actor = new Actor();
    String locale = LocaleContextHolder.getLocale().getLanguage();
    Actor deleted = this.actorService.getActorByUsername("DELETED");
    Actor system = this.actorService.getActorByUsername("system");
    actor = this.actorService.getActorByUsername(username);
    List<Actor> actors = new ArrayList<Actor>();
    actors = this.actorService.findAll();
    actors.remove(deleted);
    actors.remove(system);
    List<Box> actorBoxes = new ArrayList<Box>();
    actorBoxes = this.actorService.getlistOfBoxes(actor);
    result = new ModelAndView("message/actor/create");
    result.addObject("messageTest", messageTest);
    result.addObject("actors", actors);
    result.addObject("actorBoxes", actorBoxes);
    List<String> priority = this.configurationService.getConfiguration().getPriorityLvl();
    List<String> priorityName = new ArrayList<>();
    if (locale == "en")
        priorityName = this.configurationService.getConfiguration().getPriorityLvl();
    else if (locale == "es")
        priorityName = this.configurationService.getConfiguration().getPriorityLvlSpa();
    result.addObject("message", messageCode);
    result.addObject("priority", priority);
    result.addObject("priorityName", priorityName);
    return result;
}


public ModelAndView createEditModelAndViewMove(Message message,String messageCode){
    ModelAndView result;
    UserAccount userAccount = LoginService.getPrincipal();
    String username = userAccount.getUsername();
    Actor actor = new Actor();
    String locale = LocaleContextHolder.getLocale().getLanguage();
    Actor deleted = this.actorService.getActorByUsername("DELETED");
    Actor system = this.actorService.getActorByUsername("system");
    actor = this.actorService.getActorByUsername(username);
    List<Actor> actors = new ArrayList<Actor>();
    actors = this.actorService.findAll();
    actors.remove(deleted);
    actors.remove(system);
    List<Box> actorBoxes = new ArrayList<Box>();
    actorBoxes = this.actorService.getlistOfBoxes(actor);
    List<String> priorityName = new ArrayList<>();
    List<String> priority = this.configurationService.getConfiguration().getPriorityLvl();
    if (locale == "en")
        priorityName = this.configurationService.getConfiguration().getPriorityLvl();
    else if (locale == "es")
        priorityName = this.configurationService.getConfiguration().getPriorityLvlSpa();
    result = new ModelAndView("message/actor/move");
    result.addObject("messageTest", message);
    result.addObject("actors", actors);
    result.addObject("actorBoxes", actorBoxes);
    result.addObject("priority", priority);
    result.addObject("priority", priorityName);
    result.addObject("message", messageCode);
    return result;
}


}