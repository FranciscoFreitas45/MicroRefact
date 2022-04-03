package com.gbcom.DTO;
 import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Logger;
import com.gbcom.op.util.xml.XStreamUtil;
public class MailSender {

 private  Logger LOGGER;

 private  MailContext context;

 private  MailSender INSTANCE;

private MailSender() {
    final Class<?>[] classContext = { MailContext.class, MailReceiver.class };
    final URL url = Thread.currentThread().getContextClassLoader().getResource("config/mail/mail.xml");
    context = XStreamUtil.fromXML(MailContext.class, url.getFile(), classContext);
    LOGGER.info("MailSender init success , ----- from file ： config/mail/mail.xml");
}
public MailContext getMailContext(){
    return context;
}


public MailSender getInstance(){
    return INSTANCE;
}


public MailContext getContext(){
    return context;
}


}