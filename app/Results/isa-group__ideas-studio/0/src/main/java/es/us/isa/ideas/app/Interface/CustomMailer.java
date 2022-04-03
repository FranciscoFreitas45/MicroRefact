package es.us.isa.ideas.app.Interface;
public interface CustomMailer {

   public void sendMail(Map<String,Map<String,String>> recipients,TemplateMail template);
}