package kielce.tu.weaii.telelearn.servicedata;
 import kielce.tu.weaii.telelearn.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ConversationInfo {

 private  User participant;

 private  long messageCount;

 private  long unreadMessageCount;

 private  LocalDateTime lastMessageTime;


}