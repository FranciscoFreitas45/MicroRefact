import pt.iscte.hospital.entities.Message;
import pt.iscte.hospital.entities.User;
import java.time.LocalDate;
import java.util.List;
public interface MessageService {


public List<Message> findAllByUserUserIdOrderByDateDesc(Long userId)


public List<Message> findAllByUserUserIdAndReadMsgOrderByDateDesc(Long userId,boolean hasRead)


public List<Message> findAllByUserUserIdAndDateOrderByDateDesc(Long userId,LocalDate date)


public boolean hasUnreadMessages(Long userId)


public void save(Message message)


public List<Message> findAllByUserUserIdAndDateAndReadMsgOrderByDateDesc(Long userId,LocalDate date,boolean hasRead)


public void createMessage(String subject,String message,User userReceiver)


public void deleteMessageById(Long msgId)


public void markMessageHasReadById(Long msgId)


public void delete(Message message)


public Long countAllByUserUserIdAndReadMsg(Long userId,boolean hasRead)


}