package sn.service;
 import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
@Service
public class MailSenderService {

@Autowired
 private  JavaMailSender javaMailSender;

@Value("${spring.mail.username}")
 private  String username;


@SneakyThrows
public void send(String emailTo,String subject,String message){
    MimeMessage mailMessage = javaMailSender.createMimeMessage();
    boolean multipart = false;
    MimeMessageHelper helper = new MimeMessageHelper(mailMessage, multipart);
    helper.setFrom(username);
    helper.setTo(emailTo);
    helper.setSubject(subject);
    mailMessage.setContent(message, "text/html");
    javaMailSender.send(mailMessage);
}


}