import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
@Service
public class SendMailService {

@Autowired
 private  JavaMailSender javaMailSender;

@Autowired
 private  JavaMailSender mailSender;


public void sendMailSender(String from,String to,String subject,String msg){
    try {
        MimeMessage message = mailSender.createMimeMessage();
        message.setSubject(subject);
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(msg, true);
        mailSender.send(message);
    } catch (MessagingException ex) {
        Logger.getLogger(SendMailService.class.getName()).log(Level.SEVERE, null, ex);
    }
}


public void setMailSender(JavaMailSender mailSender){
    this.mailSender = mailSender;
}


public void sendMail(String from,String to,String subject,String body){
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setFrom(from);
    mailMessage.setTo(to);
    mailMessage.setSubject(subject);
    mailMessage.setText(body);
    javaMailSender.send(mailMessage);
}


}