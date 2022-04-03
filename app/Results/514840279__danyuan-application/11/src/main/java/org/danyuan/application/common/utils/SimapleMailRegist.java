package org.danyuan.application.common.utils;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class SimapleMailRegist {

@Autowired
 private  JavaMailSender mailSender;

@Value(value = "${spring.mail.from}")
 private  String fromMail;


public void SendMailToCustom(MailVo vo){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(vo.getFromMail() == null ? fromMail : vo.getFromMail());
    message.setTo(vo.getMail());
    message.setSubject(vo.getTitle());
    message.setText(vo.getMessage());
    mailSender.send(message);
}


}