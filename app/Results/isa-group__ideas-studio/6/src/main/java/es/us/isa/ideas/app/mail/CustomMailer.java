package es.us.isa.ideas.app.mail;
 import java.util.Collection;
import java.util.Map;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
public class CustomMailer {

 private  MailSender mailSender;

 private  CustomizationsExtractor customizationExtrator;

 private  String from;

 private  String[] bcc;

 private  String[] cc;

public CustomMailer() {
    super();
}
public void setCustomizationExtrator(CustomizationsExtractor customizationExtrator){
    this.customizationExtrator = customizationExtrator;
}


public CustomizationsExtractor getCustomizationExtrator(){
    return customizationExtrator;
}


public void setBcc(String[] bcc){
    this.bcc = bcc;
}


public String[] getBcc(){
    return bcc;
}


public Map<String,String> extractCustomizations(Object researcher){
    return getCustomizationExtrator().extract(researcher);
}


public void setFrom(String from){
    this.from = from;
}


public void setCc(String[] cc){
    this.cc = cc;
}


public MailSender getMailSender(){
    return mailSender;
}


public void setMailSender(MailSender mailSender){
    this.mailSender = mailSender;
}


public void sendMail(Map<String,Map<String,String>> recipients,TemplateMail template){
    Map<String, String> customizations;
    for (String to : recipients.keySet()) {
        customizations = recipients.get(to);
        sendMail(to, customizations, template);
    }
}


public String[] getCc(){
    return cc;
}


public String getFrom(){
    return from;
}


}