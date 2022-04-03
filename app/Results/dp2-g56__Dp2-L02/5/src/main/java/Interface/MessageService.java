package Interface;
public interface MessageService {

   public void updateReceivedMessageToLogguedActor();
   public void updateSendedMessageByLogguedActor();
   public void sendNotificationDropOut(Brotherhood bro);
   public void sendNotificationBroEnrolMem(Member mem);
}