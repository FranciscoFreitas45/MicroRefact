import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities.Message;
import pt.iscte.hospital.entities.User;
import pt.iscte.hospital.repositories.MessageRepository;
import java.time.LocalDate;
import java.util.List;
@Service
public class MessageServiceImpl implements pt.iscte.hospital.services.MessageService,MessageService{

@Autowired
 private MessageRepository messageRepository;


@Override
public List<Message> findAllByUserUserIdOrderByDateDesc(Long userId){
    return messageRepository.findAllByUserUserIdOrderByDateDesc(userId);
}


@Override
public List<Message> findAllByUserUserIdAndReadMsgOrderByDateDesc(Long userId,boolean hasRead){
    return messageRepository.findAllByUserUserIdAndReadMsgOrderByDateDesc(userId, hasRead);
}


@Override
public List<Message> findAllByUserUserIdAndDateOrderByDateDesc(Long userId,LocalDate date){
    return messageRepository.findAllByUserUserIdAndDateOrderByDateDesc(userId, date);
}


@Override
public boolean hasUnreadMessages(Long userId){
    Long count = messageRepository.countAllByUserUserIdAndReadMsg(userId, false);
    return count != 0;
}


@Override
public void save(Message message){
    messageRepository.save(message);
}


@Override
public List<Message> findAllByUserUserIdAndDateAndReadMsgOrderByDateDesc(Long userId,LocalDate date,boolean hasRead){
    return messageRepository.findAllByUserUserIdAndDateAndReadMsgOrderByDateDesc(userId, date, hasRead);
}


@Override
public void createMessage(String subject,String message,User userReceiver){
    save(new Message(subject, message, userReceiver));
}


@Override
public void deleteMessageById(Long msgId){
    Message msg = messageRepository.findByMessageId(msgId);
    delete(msg);
}


@Override
public void markMessageHasReadById(Long msgId){
    Message msg = messageRepository.findByMessageId(msgId);
    msg.setReadMsg(true);
    save(msg);
}


@Override
public void delete(Message message){
    messageRepository.delete(message);
}


@Override
public Long countAllByUserUserIdAndReadMsg(Long userId,boolean hasRead){
    return messageRepository.countAllByUserUserIdAndReadMsg(userId, hasRead);
}


}