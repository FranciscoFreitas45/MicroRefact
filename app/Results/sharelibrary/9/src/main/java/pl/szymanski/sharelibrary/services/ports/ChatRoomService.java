package pl.szymanski.sharelibrary.services.ports;
 import pl.szymanski.sharelibrary.entity.ChatMessage;
import pl.szymanski.sharelibrary.entity.ChatRoom;
import java.util.List;
public interface ChatRoomService {


public List<ChatMessage> getMessageFromRoom(Long roomId)
;

public ChatRoom getRoomBySenderIdAndRecipientId(Long senderId,Long recipientId)
;

public ChatRoom createRoom(Long senderId,Long recipientId)
;

public List<ChatRoom> getRoomByUserId(Long userId)
;

}