package kielce.tu.weaii.telelearn.repositories.ports;
 import kielce.tu.weaii.telelearn.models.Message;
import java.util.List;
public interface MessageRepository {


public Message save(Message message)
;

public List<Message> getConversation(Long user1Id,Long user2Id)
;

public void setConversationAsRead(Long receiverId,Long senderId)
;

public List<Message> getUserMessages(Long userId)
;

}