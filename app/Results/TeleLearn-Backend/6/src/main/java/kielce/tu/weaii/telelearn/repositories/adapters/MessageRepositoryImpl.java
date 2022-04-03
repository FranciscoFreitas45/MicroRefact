package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.Message;
import kielce.tu.weaii.telelearn.repositories.jpa.MessageJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository{

 private  MessageJPARepository messageJPARepository;


@Override
public Message save(Message message){
    return messageJPARepository.saveAndFlush(message);
}


@Override
public List<Message> getConversation(Long user1Id,Long user2Id){
    return messageJPARepository.findConversationMessages(user1Id, user2Id);
}


@Override
public void setConversationAsRead(Long receiverId,Long senderId){
    messageJPARepository.setConversationAsRead(receiverId, senderId);
}


@Override
public List<Message> getUserMessages(Long userId){
    return messageJPARepository.findUserMessages(userId);
}


}