package pl.szymanski.sharelibrary.repositories.jpa;
 import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymanski.sharelibrary.entity.ChatRoom;
import java.util.Optional;
public interface ChatRoomJPARepository extends JpaRepository<ChatRoom, Long>{


public Optional<ChatRoom> findBySenderIdAndRecipientId(Long senderId,Long recipientId)
;

}