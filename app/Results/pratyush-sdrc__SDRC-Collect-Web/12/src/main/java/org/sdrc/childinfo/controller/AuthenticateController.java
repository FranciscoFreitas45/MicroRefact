package org.sdrc.childinfo.controller;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.sdrc.childinfo.model.UserDetailsModel;
import org.sdrc.childinfo.util.Constants;
import org.sdrc.childinfo.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class AuthenticateController implements AuthenticationProvider{

 private  StateManager stateManager;

@Autowired
public AuthenticateController(StateManager stateManager) {
    this.stateManager = stateManager;
}
@Override
public Authentication authenticate(Authentication authentication){
    UserDetailsModel userDetailsModel = new UserDetailsModel();
    if (!authentication.getName().equals("admin") || !authentication.getCredentials().toString().equals("admin"))
        throw new BadCredentialsException("Invalid User!");
    userDetailsModel.setUserName(authentication.getName());
    userDetailsModel.setPassword(authentication.getCredentials().toString());
    stateManager.setValue(Constants.USER_PRINCIPAL, userDetailsModel);
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attr.getRequest();
    return new UsernamePasswordAuthenticationToken(authentication.getName(), (String) authentication.getCredentials(), null);
}


@Override
public boolean supports(Class<?> authentication){
    // TODO Auto-generated method stub
    return false;
}


@RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
public String authorize(HttpServletRequest request,RedirectAttributes redirectAttributes,String userEmail,String password,Model model){
    List<String> errMessage = new ArrayList<String>();
    try {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userEmail, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = this.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (Exception e) {
        e.printStackTrace();
        SecurityContextHolder.getContext().setAuthentication(null);
        errMessage.add("Invalid Credential");
        redirectAttributes.addFlashAttribute("formError", errMessage);
        redirectAttributes.addFlashAttribute("className", "alert alert-danger");
        return "redirect:/";
    }
    return "redirect:/";
}


}