package com.softserve.edu.Resources.registration.listener;
 import com.softserve.edu.Resources.entity.User;
import com.softserve.edu.Resources.registration.OnRegistrationCompleteEvent;
import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.service.impl.VelocityMailService;
import com.softserve.edu.Resources.util.RegistrationConfirmMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.UUID;
import com.softserve.edu.Resources.Interface.UserService;
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{

@Autowired
 private  UserService userService;

@Autowired
 private VelocityMailService velocityMailService;

@Autowired
 private  Environment env;


public void confirmRegistration(OnRegistrationCompleteEvent event){
    final User user = event.getUser();
    final String userId = "" + user.getId();
    final String token = UUID.randomUUID().toString();
    userService.createVerificationTokenForUser(user, token);
    velocityMailService.sendConfirmationMail(constructRegistrationConfirmMail(user, token, userId));
}


@Override
public void onApplicationEvent(OnRegistrationCompleteEvent event){
    this.confirmRegistration(event);
}


public RegistrationConfirmMail constructRegistrationConfirmMail(User user,String token,String userId){
    RegistrationConfirmMail mail = new RegistrationConfirmMail(user.getUsername());
    mail.setFrom(env.getProperty("mail.username"));
    mail.setHost(env.getProperty("host.appUrl"));
    mail.setToken(token);
    mail.setUserId(userId);
    return mail;
}


}