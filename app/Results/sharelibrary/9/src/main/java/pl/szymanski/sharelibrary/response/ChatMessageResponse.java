package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.ChatMessage;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ChatMessageResponse {

 private  Long id;

 private  ChatRoomResponse room;

 private  BaseUserResponse sender;

 private  BaseUserResponse recipient;

 private  String content;

 private  LocalDateTime timestamp;


public ChatMessageResponse of(ChatMessage message){
    return new ChatMessageResponse(message.getId(), ChatRoomResponse.of(message.getChat()), BaseUserResponse.of(message.getSender()), BaseUserResponse.of(message.getRecipient()), message.getContent(), message.getTimestamp());
}


}