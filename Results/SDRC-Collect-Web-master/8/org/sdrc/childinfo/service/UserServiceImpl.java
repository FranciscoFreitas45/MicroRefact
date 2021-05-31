import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.sdrc.childinfo.model.Mail;
import org.sdrc.childinfo.repository.springdatajpa.SpringDataUserRepository;
import org.sdrc.childinfo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService,org.sdrc.childinfo.service.UserService{

 private  ResourceBundleMessageSource messageSource;

 private  SpringDataUserRepository userRepository;


@Override
public String sendMail(String fromUserName,String toUserName,String toEmailId,StringBuffer subject,StringBuffer msg){
    try {
        Properties props = new Properties();
        props.put(messageSource.getMessage(Constants.SMTP_HOST_KEY, null, null), messageSource.getMessage(Constants.SMTP_HOST, null, null));
        props.put(messageSource.getMessage(Constants.SOCKETFACTORY_PORT_KEY, null, null), messageSource.getMessage(Constants.SOCKETFACTORY_PORT, null, null));
        props.put(messageSource.getMessage(Constants.SOCKETFACTORY_CLASS_KEY, null, null), messageSource.getMessage(Constants.SOCKETFACTORY_CLASS, null, null));
        props.put(messageSource.getMessage(Constants.SMTP_AUTH_KEY, null, null), messageSource.getMessage(Constants.SMTP_AUTH, null, null));
        props.put(messageSource.getMessage(Constants.SMTP_PORT_KEY, null, null), messageSource.getMessage(Constants.SMTP_PORT, null, null));
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(messageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null), messageSource.getMessage(Constants.AUTHENTICATION_PASSWORD, null, null));
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(messageSource.getMessage(Constants.MESSAGE_SETFORM, null, null)));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailId));
        message.setSubject(subject.toString());
        message.setText("Dear User" + "\n\n" + "NOTIFICATION DETAILS:" + "\n" + "Message : " + msg + "\n\n" + "Regards," + "\n" + fromUserName);
        Transport.send(message);
        System.out.println("Done");
    } catch (Exception e) {
        System.out.println(e);
        return null;
    }
    return "Done";
}


public PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication(messageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null), messageSource.getMessage(Constants.AUTHENTICATION_PASSWORD, null, null));
}


@Override
public String sendAdaptionMail(Mail mail){
    try {
        System.out.println("**************************Mail Method is called**************************");
        System.out.println("**************************Mail Method is called************" + mail.getToEmailId() + mail.getToUserName());
        Properties props = new Properties();
        props.put(messageSource.getMessage(Constants.SMTP_HOST_KEY, null, null), messageSource.getMessage(Constants.SMTP_HOST, null, null));
        props.put(messageSource.getMessage(Constants.SOCKETFACTORY_PORT_KEY, null, null), messageSource.getMessage(Constants.SOCKETFACTORY_PORT, null, null));
        props.put(messageSource.getMessage(Constants.SOCKETFACTORY_CLASS_KEY, null, null), messageSource.getMessage(Constants.SOCKETFACTORY_CLASS, null, null));
        props.put(messageSource.getMessage(Constants.SMTP_AUTH_KEY, null, null), messageSource.getMessage(Constants.SMTP_AUTH, null, null));
        props.put(messageSource.getMessage(Constants.SMTP_PORT_KEY, null, null), messageSource.getMessage(Constants.SMTP_PORT, null, null));
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(messageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null), messageSource.getMessage(Constants.AUTHENTICATION_PASSWORD, null, null));
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(messageSource.getMessage(Constants.MESSAGE_SETFORM, null, null)));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getToEmailId()));
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(messageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null)));
        message.setSubject("Offline Adaptation CD");
        message.setText("Dear " + mail.getFromUserName() + "\n\n" + "You have requested for DevInfo offline adaptation CD. We will send you the CD by post on the below mentioned address.\n\n" + "Address: \n" + mail.getSubject() + "\n\n" + "Regards," + "\n" + "Administrator");
        Transport.send(message);
        System.out.println("Done");
    } catch (Exception e) {
        System.out.println(e);
        return null;
    }
    return "Done";
}


}