package kielce.tu.weaii.telelearn.views;
 import kielce.tu.weaii.telelearn.servicedata.ConversationInfo;
import lombok.Value;
import java.time.LocalDateTime;
@Value
public class ConversationInfoView {

 private UserView participant;

 private long messageCount;

 private long unreadMessageCount;

 private LocalDateTime lastMessageTime;


public ConversationInfoView from(ConversationInfo conversationInfo){
    return new ConversationInfoView(UserView.from(conversationInfo.getParticipant(), false), conversationInfo.getMessageCount(), conversationInfo.getUnreadMessageCount(), conversationInfo.getLastMessageTime());
}


}