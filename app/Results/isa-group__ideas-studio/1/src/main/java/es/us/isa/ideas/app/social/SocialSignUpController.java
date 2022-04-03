package es.us.isa.ideas.app.social;
 import es.us.isa.ideas.app.security.UserAccount;
import es.us.isa.ideas.app.security.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping(value = "/social")
public class SocialSignUpController {

@Autowired
 private  UserAccountService userAccountService;

@Autowired
 private  SignInUtils signInUtils;

@Autowired
 private  ConnectionRepository connectionRepository;


public ModelAndView createAccountAndSignIn(Connection<?> connection,WebRequest request){
    ModelAndView result = null;
    UserAccount userAccount = userAccountService.create(connection);
    signInUtils.signin(userAccount.getUsername(), connection.getKey().getProviderId());
    connectionRepository.addConnection(connection);
    result = new ModelAndView("redirect:/connect/" + connection.getKey().getProviderId());
    result.addObject("message", "You have succesfully signed up with your " + connection.getKey().getProviderId() + " account, " + "additionally we have created a user account for you. You will receive an e-mail" + " with more information soon.");
    return result;
}


@RequestMapping(value = "/signup")
public ModelAndView signupForm(WebRequest request){
    ModelAndView result = null;
    Connection<?> connection = ProviderSignInUtils.getConnection(request);
    if (connection != null) {
        result = createAccountAndSignIn(connection, request);
    } else {
        result = new ModelAndView("redirect:/researcher/create");
        result.addObject("message", "Unable to connect using social account. Try creating a regular account.");
    }
    return result;
}


}