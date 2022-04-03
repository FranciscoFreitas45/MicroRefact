package com.yalcin.event;
 import com.yalcin.entity.User;
import com.yalcin.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.yalcin.Interface.JwtProvider;
@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegistrationSuccessEvent>{

@Autowired
 private EmailSender emailSender;

@Autowired
 private JwtProvider jwtProvider;

@Autowired
 private  SpringTemplateEngine templateEngine;


public void confirmRegistration(OnRegistrationSuccessEvent event){
    User user = event.getUser();
    String token = jwtProvider.generateJwtTokenForVerification(user);
    String recipient = user.getEmail();
    String url = "http://localhost:8080/" + event.getAppUrl() + "/confirmRegistration?token=" + token;
    Map model = new HashMap();
    model.put("name", user.getUsername());
    model.put("url", url);
    model.put("signature", "Mail aktivasyon kodu !");
    Context context = new Context();
    context.setVariables(model);
    String content = templateEngine.process("db-verification_email", context);
    emailSender.sendSimpleMessage(content, recipient, "Registration Confirmation");
}


@Override
public void onApplicationEvent(OnRegistrationSuccessEvent event){
    try {
        this.confirmRegistration(event);
    } catch (MessagingException | IOException e) {
        e.printStackTrace();
    }
}


}