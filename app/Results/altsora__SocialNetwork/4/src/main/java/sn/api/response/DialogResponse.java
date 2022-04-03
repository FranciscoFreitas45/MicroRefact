package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import sn.model.Message;
import sn.model.enums.MessageStatus;
import java.util.List;
import java.util.Set;
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DialogResponse extends AbstractResponse{

@JsonValue
 private  List<DialogData> dialogDataSet;

@JsonProperty("id")
 private  long dialogId;

@JsonProperty("count")
 private  long unreadedMessagesCount;

@JsonProperty("user_ids")
 private  List<Long> userIds;

@JsonProperty("link")
 private  String inviteLink;

@JsonValue
 private  Set<Message> dialogMessages;

@JsonProperty("id")
 private  long dialogId;

@JsonProperty("unread_count")
 private  int unreadCount;

@JsonProperty("last_message")
 private  LastMessage message;

 private  long id;

 private  long time;

@JsonProperty("author_id")
 private  long authorId;

@JsonProperty("recipient_id")
 private  long recipientId;

@JsonProperty("message_text")
 private  String messageText;

@JsonProperty("read_status")
 private  MessageStatus messageStatus;


}