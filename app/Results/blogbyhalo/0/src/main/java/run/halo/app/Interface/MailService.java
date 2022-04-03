package run.halo.app.Interface;
public interface MailService {

   public void sendTemplateMail(String to,String subject,Map<String,Object> content,String templateName);
}