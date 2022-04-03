package es.us.isa.ideas.app.Interface;
public interface CustomMailer {

   public Map<String,String> extractCustomizations(Object researcher);
   public void sendMail(Map<String,Map<String,String>> recipients,TemplateMail template);
}