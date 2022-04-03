package Interface;
public interface ChatMessageRepository {

   public Page<ChatMessage> findByContextidAndUseridAndOrgi(String contextid,String userid,String orgi,Pageable page);
   public Page<ChatMessage> findByContextidAndOrgi(String contextid,String orgi,Pageable page);
}