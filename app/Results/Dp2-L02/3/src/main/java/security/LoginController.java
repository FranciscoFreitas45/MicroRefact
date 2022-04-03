package security;
 import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import controllers.AbstractController;
@Controller
@RequestMapping("/security")
public class LoginController extends AbstractController{

@Autowired
 private LoginService service;

// Constructors -----------------------------------------------------------
public LoginController() {
    super();
}
@RequestMapping("/loginFailure")
public ModelAndView failure(){
    ModelAndView result;
    result = new ModelAndView("redirect:login.do?showError=true");
    return result;
}


@RequestMapping("/login")
public ModelAndView login(Credentials credentials,BindingResult bindingResult,boolean showError){
    Assert.notNull(credentials);
    Assert.notNull(bindingResult);
    ModelAndView result;
    result = new ModelAndView("security/login");
    result.addObject("credentials", credentials);
    result.addObject("showError", showError);
    return result;
}


}