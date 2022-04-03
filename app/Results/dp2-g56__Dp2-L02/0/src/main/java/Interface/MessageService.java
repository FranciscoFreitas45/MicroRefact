package Interface;
public interface MessageService {

   public Message createNotification(String Subject,String body,String priority,String tags,Actor recipient);
   public Message sendMessageAnotherSender(Message message);
}