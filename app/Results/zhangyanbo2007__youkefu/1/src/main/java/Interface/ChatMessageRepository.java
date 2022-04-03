package Interface;
public interface ChatMessageRepository {

   public ChatMessage findById(String id);
   public Object save(Object Object);
   public Page<ChatMessage> findByAgentserviceidAndOrgi(String agentserviceid,String orgi,Pageable page);
}