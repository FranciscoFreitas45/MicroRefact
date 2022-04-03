package pl.szymanski.sharelibrary.utils.generator;
 import pl.szymanski.sharelibrary.entity.ChatMessage;
import pl.szymanski.sharelibrary.entity.ChatRoom;
import pl.szymanski.sharelibrary.entity.User;
import pl.szymanski.sharelibrary.requests.ChatMessageRequest;
import pl.szymanski.sharelibrary.response.BaseUserResponse;
import pl.szymanski.sharelibrary.response.ChatMessageResponse;
import pl.szymanski.sharelibrary.response.ChatRoomResponse;
import pl.szymanski.sharelibrary.utils.constant.ChatConstant;
import java.time.LocalDateTime;
public class ChatGenerator {


public ChatMessageResponse getChatMessageResponse(){
    return new ChatMessageResponse(1L, ChatRoomResponse.of(getChatRoom()), BaseUserResponse.of(UserGenerator.getUser()), BaseUserResponse.of(UserGenerator.getUser()), "Message", LocalDateTime.now());
}


public ChatRoomResponse getChatRoomResponse(){
    return ChatRoomResponse.of(getChatRoom());
}


public ChatMessageRequest getChatMessageRequest(){
    return new ChatMessageRequest(1L, 1L, 2L, ChatConstant.MESSAGE_CONTENT);
}


public ChatMessage getChatMessage(){
    User sender = UserGenerator.getUser();
    User recipient = UserGenerator.getUser();
    recipient.setId(2L);
    ChatMessage message = new ChatMessage();
    message.setChat(getChatRoom());
    message.setTimestamp(LocalDateTime.now());
    message.setContent(ChatConstant.MESSAGE_CONTENT);
    message.setSender(sender);
    message.setRecipient(recipient);
    return message;
}


public ChatRoom getChatRoom(){
    ChatRoom room = new ChatRoom();
    room.setId(1L);
    User sender = UserGenerator.getUser();
    User recipient = UserGenerator.getUser();
    recipient.setId(2L);
    room.setSender(sender);
    room.setRecipient(recipient);
    return room;
}


}