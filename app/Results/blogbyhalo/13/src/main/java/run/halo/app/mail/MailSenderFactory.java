package run.halo.app.mail;
 import java.util.Properties;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
public class MailSenderFactory {


@NonNull
public JavaMailSender getMailSender(MailProperties mailProperties){
    Assert.notNull(mailProperties, "Mail properties must not be null");
    // create mail sender
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    // set properties
    setProperties(mailSender, mailProperties);
    return mailSender;
}


public void setProperties(JavaMailSenderImpl mailSender,MailProperties mailProperties){
    mailSender.setHost(mailProperties.getHost());
    mailSender.setPort(mailProperties.getPort());
    mailSender.setUsername(mailProperties.getUsername());
    mailSender.setPassword(mailProperties.getPassword());
    mailSender.setProtocol(mailProperties.getProtocol());
    mailSender.setDefaultEncoding(mailProperties.getDefaultEncoding().name());
    if (!CollectionUtils.isEmpty(mailProperties.getProperties())) {
        Properties properties = new Properties();
        properties.putAll(mailProperties.getProperties());
        mailSender.setJavaMailProperties(properties);
    }
}


}