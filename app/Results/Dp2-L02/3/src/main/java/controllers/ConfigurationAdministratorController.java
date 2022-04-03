package controllers;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ConfigurationService;
import domain.Configuration;
@Controller
@RequestMapping("/configuration/administrator")
public class ConfigurationAdministratorController extends AbstractController{

@Autowired
 private  ConfigurationService configurationService;

// Constructors -----------------------------------------------------------
public ConfigurationAdministratorController() {
    super();
}
@RequestMapping(value = "/edit", method = RequestMethod.GET)
public ModelAndView configurationEdit(){
    ModelAndView result;
    Configuration configuration = this.configurationService.getConfiguration();
    result = new ModelAndView("administrator/editConfiguration");
    result.addObject("configuration", configuration);
    return result;
}


@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
public ModelAndView save(Configuration configuration,BindingResult binding){
    ModelAndView result;
    Configuration configurationR = this.configurationService.reconstruct(configuration, binding);
    if (binding.hasErrors())
        result = this.createEditModelAndView(configuration);
    else
        try {
            this.configurationService.save(configurationR);
            result = new ModelAndView("redirect:list.do");
        } catch (Throwable oops) {
            result = this.createEditModelAndView(configuration, "configuration.commit.error");
        }
    return result;
}


@RequestMapping(value = "/list", method = RequestMethod.GET)
public ModelAndView configurationList(){
    ModelAndView result;
    Configuration configuration;
    configuration = this.configurationService.getConfiguration();
    result = new ModelAndView("administrator/configuration");
    result.addObject("configuration", configuration);
    result.addObject("requestURI", "configuration/administrator/list.do");
    return result;
}


public ModelAndView createEditModelAndView(Configuration configuration,String message){
    ModelAndView result = new ModelAndView("administrator/editConfiguration");
    result.addObject("configuration", configuration);
    result.addObject("message", message);
    return result;
}


}