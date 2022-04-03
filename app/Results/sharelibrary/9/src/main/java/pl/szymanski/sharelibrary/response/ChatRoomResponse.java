package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.ChatRoom;
@Data
@AllArgsConstructor
public class ChatRoomResponse {

 private  Long id;

 private  BaseUserResponse sender;

 private  BaseUserResponse recipient;


public ChatRoomResponse of(ChatRoom chatRoom){
    return new ChatRoomResponse(chatRoom.getId(), BaseUserResponse.of(chatRoom.getSender()), BaseUserResponse.of(chatRoom.getRecipient()));
}


}