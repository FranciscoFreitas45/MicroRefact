package kielce.tu.weaii.telelearn.repositories.jpa;
 import kielce.tu.weaii.telelearn.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface MessageJPARepository extends JpaRepository<Message, Long>{


@Query("SELECT m FROM Message m WHERE (m.receiver.id = ?1 AND m.sender.id = ?2) OR (m.receiver.id = ?2 AND m.sender.id = ?1) ORDER BY m.sendTime ASC")
public List<Message> findConversationMessages(Long user1iId,Long user2iId)
;

@Query("SELECT m FROM Message m WHERE m.receiver.id = ?1 OR m.sender.id = ?1")
public List<Message> findUserMessages(Long userId)
;

@Modifying(flushAutomatically = true)
@Query("UPDATE Message m SET m.read = true WHERE m.receiver.id = ?1 AND m.sender.id = ?2")
public void setConversationAsRead(Long receiverId,Long senderId)
;

}