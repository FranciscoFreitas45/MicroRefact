package br.com.fatecmogidascruzes.controller;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import br.com.fatecmogidascruzes.user.User;
import br.com.fatecmogidascruzes.user.service.UserService;
import br.com.fatecmogidascruzes.Interface.UserService;
public class MVCController extends Controller{

@SuppressWarnings("unused")
 private  HttpSession httpSession;

 private  UserService userService;

@Autowired
public MVCController(HttpSession httpSession, UserService userService) {
    this.httpSession = httpSession;
    this.userService = userService;
}
@ModelAttribute("loggedUser")
public User getLoggedUser(){
    try {
        return userService.getByUsername(((br.com.fatecmogidascruzes.config.UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()).get();
    } catch (Exception error) {
        return null;
    }
}


public String getBaseURI(HttpServletRequest request){
    return // "http" + "://
    request.getScheme() + "://" + // "myhost"
    request.getServerName() + ":" + request.getServerPort();
}


}