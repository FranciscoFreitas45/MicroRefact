package controllers;
 import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ConfigurationService;
import domain.Configuration;
@Controller
@RequestMapping("/priority/administrator")
public class PriorityConfigurationController extends AbstractController{

@Autowired
 private  ConfigurationService configurationService;

// Constructors -----------------------------------------------------------
public PriorityConfigurationController() {
    super();
}
@RequestMapping(value = "/create", method = RequestMethod.GET)
public ModelAndView edit(){
    ModelAndView result;
    result = new ModelAndView("priority/administrator/create");
    return result;
}


@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
public ModelAndView save(String priorityName,String spaPriorityName){
    ModelAndView result;
    Configuration configuration = this.configurationService.getConfiguration();
    List<String> priority = configuration.getPriorityLvl();
    List<String> prioirtySpa = configuration.getPriorityLvlSpa();
    result = new ModelAndView("priority/administrator/create");
    if (priority.contains(priorityName) || prioirtySpa.contains(spaPriorityName) || spaPriorityName == "" || priorityName == "") {
        String locale = LocaleContextHolder.getLocale().getLanguage();
        result = new ModelAndView("priority/administrator/create");
        result.addObject("error", true);
        result.addObject("locale", locale);
        result.addObject("messageErrorEng", "The Priority has already been created and can't be blank");
        result.addObject("messageErrorSpa", "La prioridad ya ha sido creada y no puede estar en blanco");
    } else
        try {
            priority.add(priorityName);
            prioirtySpa.add(spaPriorityName);
            configuration.setPriorityLvl(priority);
            configuration.setPriorityLvlSpa(prioirtySpa);
            this.configurationService.save(configuration);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = new ModelAndView("priority/administrator/create");
        }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView list(){
    ModelAndView result;
    Configuration configuration = this.configurationService.getConfiguration();
    List<String> priority = configuration.getPriorityLvl();
    List<String> prioirtySpa = configuration.getPriorityLvlSpa();
    result = new ModelAndView("priority/administrator");
    result.addObject("priority", priority);
    result.addObject("prioritySpa", prioirtySpa);
    return result;
}


}