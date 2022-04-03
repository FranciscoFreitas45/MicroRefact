package kielce.tu.weaii.telelearn.views;
 import kielce.tu.weaii.telelearn.models.Message;
import lombok.Value;
import java.time.LocalDateTime;
@Value
public class MessageView {

 private UserView receiver;

 private UserView sender;

 private String content;

 private LocalDateTime sendTime;


public MessageView from(Message model){
    return new MessageView(UserView.from(model.getReceiver(), false), UserView.from(model.getSender(), false), model.getContent(), model.getSendTime());
}


}