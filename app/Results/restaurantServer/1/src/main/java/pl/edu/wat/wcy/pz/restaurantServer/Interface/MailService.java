package pl.edu.wat.wcy.pz.restaurantServer.Interface;
public interface MailService {

   public void sendEmail(String destination,String subject,String content);
}