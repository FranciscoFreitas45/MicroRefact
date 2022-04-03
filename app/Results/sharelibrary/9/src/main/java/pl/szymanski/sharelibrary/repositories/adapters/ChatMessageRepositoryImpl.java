package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.ChatMessage;
import pl.szymanski.sharelibrary.repositories.jpa.ChatMessageJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.ChatMessageRepository;
@Repository
@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMessageRepository{

 private  ChatMessageJPARepository chatMessageJPARepository;


@Override
public ChatMessage save(ChatMessage chatMessage){
    return chatMessageJPARepository.save(chatMessage);
}


}