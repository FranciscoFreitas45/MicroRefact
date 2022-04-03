package Interface;
public interface WebsocketSender {

   public void convertAndSend(String destination,Object event);
}