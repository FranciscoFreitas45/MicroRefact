package com.softserve.edu.Resources.controller;
 import com.softserve.edu.Resources.dto.UserDTO;
import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.entity.VerificationToken;
import com.softserve.edu.Resources.exception.UserAlreadyExistException;
import com.softserve.edu.Resources.registration.OnRegistrationCompleteEvent;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.validator.FormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation;
import org.springframework.web.context.request.WebRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;
import com.softserve.edu.Resources.DTO.VerificationToken;
@Controller
@Transactional
public class RegistrationUserController {

@Autowired
 private UserService userService;

@Autowired
 private  FormValidator formValidator;

@Autowired
 private  MessageSource messages;

@Autowired
 private  ApplicationEventPublisher eventPublisher;

@Autowired
 private  Environment env;


public User createUserAccount(UserDTO userDTO,BindingResult result){
    User userRegistrated;
    try {
        userRegistrated = userService.registerNewUserAccount(userDTO);
    } catch (UserAlreadyExistException e) {
        return null;
    }
    return userRegistrated;
}


@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
public String confirmRegistration(WebRequest request,Model model,String token,String userId){
    Locale locale = request.getLocale();
    VerificationToken verificationToken = userService.getVerificationToken(token);
    User userById = userService.getUserById(Long.parseLong(userId));
    if (userById.isEnabled()) {
        String message = messages.getMessage("auth.message.userAlreadyActivated", null, locale);
        model.addAttribute("message", message);
        return "badUser";
    }
    if (verificationToken == null) {
        String message = messages.getMessage("auth.message.invalidToken", null, locale);
        model.addAttribute("message", message);
        userService.delete(userById);
        return "badUser";
    }
    User user = verificationToken.getUser();
    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
        String message = messages.getMessage("auth.message.expired", null, locale);
        model.addAttribute("message", message);
        userService.delete(user);
        return "badUser";
    }
    String message = messages.getMessage("message.confirmed", null, locale);
    model.addAttribute("message", message);
    user.setEnabled(true);
    userService.deleteVerificationToken(verificationToken);
    return "registrationConfirm";
}


@RequestMapping(value = "/signup", method = RequestMethod.POST)
public String registerUserAccount(UserDTO userDTO,BindingResult result,WebRequest request,Model model){
    formValidator.validate(userDTO, result);
    User registered = new User();
    if (!result.hasErrors()) {
        registered = createUserAccount(userDTO, result);
    }
    if (registered == null) {
        result.rejectValue("email", "user.is.exist");
        return "signup";
    }
    if (result.hasErrors()) {
        return "signup";
    }
    String message = messages.getMessage("message.regSucc", null, request.getLocale());
    model.addAttribute("message", message);
    try {
        String appUrl = env.getProperty("host.appUrl");
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
    } catch (Exception me) {
        return "signup";
    }
    return "signuprequest";
}


@RequestMapping(value = "/signup", method = RequestMethod.GET)
public String registration(Model model){
    model.addAttribute("newUser", new UserDTO());
    return "signup";
}


@RequestMapping(value = "/checkemail", method = RequestMethod.POST)
@ResponseBody
public boolean checkEmail(String email){
    return userService.emailExist(email);
}


}