package pl.edu.wat.wcy.pz.restaurantServer.email;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class MailService {

 private  JavaMailSender javaMailSender;


public void sendEmail(String destination,String subject,String content){
    MimeMessage mail = javaMailSender.createMimeMessage();
    try {
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(destination);
        helper.setReplyTo("restaurantprojectPZ@gmail.com");
        helper.setFrom("restaurantprojectPZ@gmail.com");
        helper.setSubject(subject);
        helper.setText(content);
    } catch (MessagingException e) {
        e.printStackTrace();
    }
    javaMailSender.send(mail);
}


}