package com.example.steam.utils;
 import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.EmailKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import com.example.steam.Interface.RedisService;
@Service
public class EmailUtil {

 private  String VERIFICATION_CODE;

 private  String FIND_WORD;

 private Logger log;

@Autowired
 private  JavaMailSenderImpl javaMailSender;

@Autowired
 private  RedisService redisService;

@Value("${spring.mail.username}")
 private  String emialSend;


public void sendVerificationCode(String receiveEmail){
    String randomCode = UUIDUtil.randomUUID().substring(0, 5);
    redisService.set(EmailKey.VERIFICATION_CODE, receiveEmail, randomCode);
    sendMessage(receiveEmail, VERIFICATION_CODE + randomCode);
}


public void sendMessage(String receiveEmail,String content){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emialSend);
    message.setTo(receiveEmail);
    message.setSubject("来自steam的邮件");
    message.setText(content);
    javaMailSender.send(message);
    log.info("邮件发送完毕");
}


public void sendFindPassword(String receiveEmail,String newPassword){
    sendMessage(receiveEmail, FIND_WORD + newPassword);
}


}