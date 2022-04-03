package run.halo.app.mail;
 import java.util.Map;
public interface MailService {


public void testConnection()
;

public void sendTemplateMail(String to,String subject,Map<String,Object> content,String templateName)
;

public void sendTextMail(String to,String subject,String content)
;

public void sendAttachMail(String to,String subject,Map<String,Object> content,String templateName,String attachFilePath)
;

}