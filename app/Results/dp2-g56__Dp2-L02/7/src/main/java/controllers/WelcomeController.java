package controllers;
 import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.LoginService;
import security.UserAccount;
import services.ConfigurationService;
@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController{

@Autowired
 private  ConfigurationService configurationService;

// Constructors -----------------------------------------------------------
public WelcomeController() {
    super();
}
@RequestMapping(value = "/index")
public ModelAndView index(String name,HttpServletRequest request){
    ModelAndView result;
    SimpleDateFormat formatter;
    String moment;
    formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    moment = formatter.format(new Date());
    String welcomeMessage;
    String systemName = this.configurationService.getConfiguration().getSystemName();
    UserAccount userAccount;
    String username;
    try {
        userAccount = LoginService.getPrincipal();
        username = userAccount.getUsername();
    } catch (Exception oops) {
        username = "";
    }
    String imageURL = this.configurationService.getConfiguration().getImageURL();
    request.getSession().setAttribute("imageURL", imageURL);
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    if (locale.equals("EN"))
        welcomeMessage = this.configurationService.getConfiguration().getWelcomeMessageEnglish();
    else
        welcomeMessage = this.configurationService.getConfiguration().getWelcomeMessageSpanish();
    result = new ModelAndView("welcome/index");
    result.addObject("name", name);
    result.addObject("username", username);
    result.addObject("moment", moment);
    result.addObject("welcomeMessage", welcomeMessage);
    result.addObject("systemName", systemName);
    return result;
}


}