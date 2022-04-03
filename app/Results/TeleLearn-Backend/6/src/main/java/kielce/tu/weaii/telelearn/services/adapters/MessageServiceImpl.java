package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.exceptions.InvalidSenderException;
import kielce.tu.weaii.telelearn.models.Message;
import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.repositories.ports.MessageRepository;
import kielce.tu.weaii.telelearn.requests.SendMessageRequest;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.servicedata.ConversationInfo;
import kielce.tu.weaii.telelearn.services.ports.MessageService;
import kielce.tu.weaii.telelearn.services.ports.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

 private  MessageRepository messageRepository;

 private  UserService userService;

 private  UserServiceDetailsImpl userDetailsService;


public Map<User,List<Message>> getConversationsMap(Long userId){
    User user = userService.getById(userId);
    List<Message> userMessage = messageRepository.getUserMessages(userId);
    Map<User, List<Message>> formUserMessages = userMessage.stream().collect(Collectors.groupingBy(Message::getSender));
    formUserMessages.remove(user);
    Map<User, List<Message>> toUserMessage = userMessage.stream().collect(Collectors.groupingBy(Message::getReceiver));
    toUserMessage.remove(user);
    return Stream.concat(formUserMessages.entrySet().stream(), toUserMessage.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (left, right) -> {
        left.addAll(right);
        return left;
    }));
}


public List<ConversationInfo> prepareConversationInfo(Long userId){
    List<ConversationInfo> conversationInfoList = new ArrayList<>();
    for (Map.Entry<User, List<Message>> entry : getConversationsMap(userId).entrySet()) {
        long unreadMessages = entry.getValue().stream().filter(m -> m.getReceiver().getId().equals(userId)).filter(m -> !m.isRead()).count();
        LocalDateTime lastMessageTime = entry.getValue().stream().map(Message::getSendTime).max(LocalDateTime::compareTo).orElse(LocalDateTime.MIN);
        conversationInfoList.add(new ConversationInfo(entry.getKey(), entry.getValue().size(), unreadMessages, lastMessageTime));
    }
    return conversationInfoList;
}


@Override
public List<ConversationInfo> getConversations(Long userId){
    if (!userDetailsService.getCurrentUser().getId().equals(userId)) {
        throw new AuthorizationException("konwersacje", userDetailsService.getCurrentUser().getId(), userId);
    }
    return prepareConversationInfo(userId);
}


@Override
@Transactional
public Message sendMessage(SendMessageRequest request){
    if (!userDetailsService.getCurrentUser().getId().equals(request.getSenderId())) {
        throw new InvalidSenderException();
    }
    return messageRepository.save(prepareMessage(request));
}


@Override
@Transactional
public List<Message> getConversation(Long participant1Id,Long participant2Id){
    User currentUser = userDetailsService.getCurrentUser();
    if (!currentUser.getId().equals(participant1Id) && !currentUser.getId().equals(participant2Id)) {
        throw new AuthorizationException("konwersacja", participant1Id, participant2Id);
    }
    messageRepository.setConversationAsRead(participant1Id, participant2Id);
    return messageRepository.getConversation(participant1Id, participant2Id);
}


public Message prepareMessage(SendMessageRequest request){
    Message message = new Message();
    BeanUtils.copyProperties(request, message);
    message.setSender(userService.getById(request.getSenderId()));
    message.setReceiver(userService.getById(request.getReceiverId()));
    message.setSendTime(LocalDateTime.now());
    return message;
}


}