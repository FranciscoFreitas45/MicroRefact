package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.Message;
import kielce.tu.weaii.telelearn.requests.SendMessageRequest;
import kielce.tu.weaii.telelearn.servicedata.ConversationInfo;
import java.util.List;
public interface MessageService {


public List<ConversationInfo> getConversations(Long userId)
;

public Message sendMessage(SendMessageRequest request)
;

public List<Message> getConversation(Long participant1Id,Long participant2Id)
;

}