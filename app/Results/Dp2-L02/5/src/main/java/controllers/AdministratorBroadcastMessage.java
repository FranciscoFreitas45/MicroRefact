package controllers;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.AdminService;
import services.ConfigurationService;
import services.MessageService;
import domain.Message;
@Controller
@RequestMapping("/broadcast/administrator")
public class AdministratorBroadcastMessage extends AbstractController{

@Autowired
 private  AdminService adminService;

@Autowired
 private  MessageService messageService;

@Autowired
 private  ConfigurationService configurationService;

public AdministratorBroadcastMessage() {
    super();
}
@RequestMapping(value = "/send", method = RequestMethod.GET)
public ModelAndView create(){
    ModelAndView result;
    Message message;
    String locale = LocaleContextHolder.getLocale().getLanguage();
    List<String> priorityName = new ArrayList<>();
    List<String> priority = this.configurationService.getConfiguration().getPriorityLvl();
    if (locale == "en")
        priorityName = this.configurationService.getConfiguration().getPriorityLvl();
    else if (locale == "es")
        priorityName = this.configurationService.getConfiguration().getPriorityLvlSpa();
    message = this.messageService.create();
    result = new ModelAndView("broadcast/administrator/send");
    result.addObject("messageSend", message);
    result.addObject("priority", priority);
    result.addObject("priorityName", priorityName);
    return result;
}


@RequestMapping(value = "/send", method = RequestMethod.POST, params = "send")
public ModelAndView send(Message message,BindingResult binding){
    ModelAndView result;
    message = this.adminService.reconstruct(message, binding);
    if (binding.hasErrors())
        result = this.createEditModelAndView(message);
    else
        try {
            this.adminService.broadcastMessage(message);
            result = new ModelAndView("redirect:send.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView(message, "message.commit.error");
        }
    return result;
}


public ModelAndView createEditModelAndView(Message message,String messageCode){
    ModelAndView result;
    String locale = LocaleContextHolder.getLocale().getLanguage();
    List<String> priorityName = new ArrayList<>();
    List<String> priority = this.configurationService.getConfiguration().getPriorityLvl();
    if (locale == "en")
        priorityName = this.configurationService.getConfiguration().getPriorityLvl();
    else if (locale == "es")
        priorityName = this.configurationService.getConfiguration().getPriorityLvlSpa();
    result = new ModelAndView("broadcast/administrator/send");
    result.addObject("messageSend", message);
    result.addObject("priority", priority);
    result.addObject("priorityName", priorityName);
    result.addObject("message", messageCode);
    return result;
}


@RequestMapping(value = "/sendSecurityBreach", method = RequestMethod.GET)
public ModelAndView createSecurityBreach(){
    ModelAndView result;
    Message message;
    String locale = LocaleContextHolder.getLocale().getLanguage();
    List<String> priorityName = new ArrayList<>();
    List<String> priority = this.configurationService.getConfiguration().getPriorityLvl();
    if (locale == "en")
        priorityName = this.configurationService.getConfiguration().getPriorityLvl();
    else if (locale == "es")
        priorityName = this.configurationService.getConfiguration().getPriorityLvlSpa();
    message = this.messageService.createSecurityBreach();
    result = new ModelAndView("broadcast/administrator/send");
    result.addObject("messageSend", message);
    result.addObject("priority", priority);
    result.addObject("priorityName", priorityName);
    return result;
}


}