package repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Actor;
import domain.Message;
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{


@Query("select m from Message m")
public List<Message> findAll2()
;

@Query("select a from Message a join a.receiver b where b.id = ?1")
public List<Message> getReceivedMessagesToActor(int idActor)
;

@Query("select m from Actor a join a.boxes b join b.messages m where a = ?1")
public List<Message> allMessagesOfActor(Actor a)
;

@Query("select a from Message a join a.sender b where b.id = ?1")
public List<Message> getSendedMessagesByActor(int idActor)
;

@Query("select (select a from Message a where a in p) from Actor b join b.boxes c join c.messages p where b.id = ?1")
public List<Message> getAllMessagesFromActor(int idActor)
;

}