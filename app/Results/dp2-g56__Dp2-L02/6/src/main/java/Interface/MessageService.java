package Interface;
public interface MessageService {

   public Message sendMessageBroadcasted(Message message);
   public Message create(String Subject,String body,String priority,Actor recipient);
   public Message findOne(int id);
   public void updateSendedMessageByLogguedActor();
   public void deleteAllMessageFromActor();
   public void updateReceivedMessageToLogguedActor();
}