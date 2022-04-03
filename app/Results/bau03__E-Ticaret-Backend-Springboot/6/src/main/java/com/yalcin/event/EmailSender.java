package com.yalcin.event;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
@Component
public class EmailSender {

@Autowired
 private  JavaMailSender javaMailSender;


public void sendSimpleMessage(String content,String to,String subject){
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
    helper.setTo(to);
    helper.setText(content, true);
    helper.setSubject(subject);
    javaMailSender.send(message);
}


}