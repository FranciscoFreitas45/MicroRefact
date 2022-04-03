package sn.service;
 import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import sn.api.response.MessageFullResponse;
import sn.model.Message;
import sn.model.Person;
import sn.model.enums.MessageStatus;
import sn.repositories.MessageRepository;
import sn.utils.TimeUtil;
import java.time.LocalDateTime;
@Service
public class MessageService {

 private  DialogService dialogService;

 private  MessageRepository messageRepository;

public MessageService(@Lazy DialogService dialogService, MessageRepository messageRepository) {
    this.dialogService = dialogService;
    this.messageRepository = messageRepository;
}
public MessageFullResponse editMessage(long messageId,String messageText){
    Message message = findById(messageId);
    message.setMessageText(messageText);
    message = messageRepository.saveAndFlush(message);
    return getMessageFullResponse(message);
}


public MessageFullResponse getMessageFullResponse(Message message){
    return MessageFullResponse.builder().id(message.getId()).time(TimeUtil.getTimestampFromLocalDateTime(message.getTime())).authorId(message.getAuthor().getId()).recipientId(message.getRecipient() != null ? message.getRecipient().getId() : null).messageText(message.getMessageText()).readStatus(message.getStatus().name()).build();
}


public MessageFullResponse recoverMessage(long messageId){
    Message message = findById(messageId);
    message.setDeleted(false);
    return getMessageFullResponse(messageRepository.saveAndFlush(message));
}


public Message findById(long messageId){
    return messageRepository.findById(messageId).orElse(null);
}


public boolean exists(long messageId){
    return messageRepository.existsById(messageId);
}


public void readMessage(long messageId){
    Message message = findById(messageId);
    message.setStatus(MessageStatus.READ);
    messageRepository.saveAndFlush(message);
}


public MessageFullResponse sendMessage(Person author,long dialogId,String messageText){
    Message message = new Message();
    message.setTime(TimeUtil.now());
    message.setAuthor(author);
    // TODO: пока не ясно, кто получатель
    message.setRecipient(null);
    message.setMessageText(messageText);
    message.setStatus(MessageStatus.SENT);
    message.setDialog(dialogService.findById(dialogId));
    message.setDeleted(false);
    message = messageRepository.saveAndFlush(message);
    return getMessageFullResponse(message);
}


public long removeMessage(long messageId){
    Message message = findById(messageId);
    message.setDeleted(true);
    return messageRepository.saveAndFlush(message).getId();
}


}