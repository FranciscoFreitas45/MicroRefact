import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.Message;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {


public List<Message> findAllByUserUserIdOrderByDateDesc(Long userId)


public List<Message> findAllByUserUserIdAndReadMsgOrderByDateDesc(Long userId,boolean hasRead)


public List<Message> findAllByUserUserIdAndDateOrderByDateDesc(Long userId,LocalDate date)


public List<Message> findAllByUserUserIdAndDateAndReadMsgOrderByDateDesc(Long userId,LocalDate date,boolean hasRead)


public Message findByMessageId(Long messageId)


public Long countAllByUserUserIdAndReadMsg(Long userId,boolean hasRead)


}