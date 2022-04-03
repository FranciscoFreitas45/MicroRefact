package com.yalcin.event;
 import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import com.yalcin.security.jwt.JwtProvider;
import org.thymeleaf.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;
import com.yalcin.Interface.JwtProvider;
@Component
public class ForgotPasswordEmailListener implements ApplicationListener<OnPasswordForgotEvent>{

@Autowired
 private JwtProvider jwtProvider;

@Autowired
 private EmailSender emailSender;

@Autowired
 private  SpringTemplateEngine templateEngine;


public void forgotPassword(OnPasswordForgotEvent event){
    String email = event.getEmail();
    String token = jwtProvider.generateJwtTokenForPassword(email);
    String url = "http://localhost:4200" + "/resetpassword?token=" + token;
    Map model = new HashMap();
    model.put("name", "Beloved user");
    model.put("url", url);
    model.put("signature", "Sifre Sıfırlama");
    Context context = new Context();
    context.setVariables(model);
    String content = templateEngine.process("db-forgotpassword_email", context);
    emailSender.sendSimpleMessage(content, email, "Forgot password");
}


@Override
public void onApplicationEvent(OnPasswordForgotEvent event){
    try {
        this.forgotPassword(event);
    } catch (MessagingException | IOException e) {
        e.printStackTrace();
    }
}


}