import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.iscte.hospital.controllers.utils.Common;
import pt.iscte.hospital.entities.User;
import pt.iscte.hospital.services.RegistrationService;
import pt.iscte.hospital.services.user.UserService;
@Controller
public class PublicController {

 private  UserService userService;

 private  RegistrationService registrationService;

 private  Common common;


@GetMapping(value = "/public/contacts")
public String ShowContacts(ModelMap modelMap){
    modelMap.addAllAttributes(common.sideNavMap());
    return "public/contacts";
}


@GetMapping(value = { "/public/main", "/", "/public" })
public String showMainPage(ModelMap modelMap){
    User user = userService.currentUser();
    String mainPage = userService.getUserMainPage(user);
    if (user != null) {
        return "redirect:" + mainPage;
    }
    modelMap.addAllAttributes(common.sideNavMap());
    return mainPage;
}


@GetMapping(value = "/public/login")
public String showLoginPage(){
    return "public/login";
}


@GetMapping(value = "/public/termsandconditions")
public String showTermsAndConditions(){
    return "public/termsandconditions";
}


@PostMapping(value = "/public/recover-password")
public String recoverPassword(ModelMap modelMap,String username,String email,String password1,String password2){
    if (userService.validateUserMail(username, email)) {
        if (password1.equals(password2)) {
            User user = userService.findUser(username);
            registrationService.changeEncryptPassword(user, password1);
            userService.addUser(user);
            return "redirect:/public/login";
        } else {
            modelMap.put("errorMessage", "Palavras-passe não coincidem");
        }
    } else {
        modelMap.put("errorMessage", "Username/Email inválido");
    }
    modelMap.put("username", username);
    modelMap.put("email", email);
    return "public/recover-password";
}


@GetMapping(value = "/public/general-information")
public String showGeneralInformation(ModelMap modelMap){
    modelMap.addAllAttributes(common.sideNavMap());
    return "public/general-information";
}


@GetMapping(value = "/public/recover-password")
public String showRecoverPasswordPage(ModelMap modelMap){
    modelMap.put("username", "");
    modelMap.put("email", "");
    return "public/recover-password";
}


}