package pl.szymanski.sharelibrary.repositories.ports;
 import pl.szymanski.sharelibrary.entity.ChatRoom;
import java.util.List;
import java.util.Optional;
public interface ChatRoomRepository {


public Optional<ChatRoom> findByRoomId(Long roomId)
;

public Optional<ChatRoom> findBySenderIdAndRecipientId(Long senderId,Long recipientId)
;

public ChatRoom createRoom(ChatRoom chatRoom)
;

public List<ChatRoom> findAll()
;

}