package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.ChatRoom;
import pl.szymanski.sharelibrary.repositories.jpa.ChatRoomJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.ChatRoomRepository;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomRepository{

 private  ChatRoomJPARepository chatRoomJPARepository;


@Override
public Optional<ChatRoom> findByRoomId(Long roomId){
    return chatRoomJPARepository.findById(roomId);
}


@Override
public Optional<ChatRoom> findBySenderIdAndRecipientId(Long senderId,Long recipientId){
    return chatRoomJPARepository.findBySenderIdAndRecipientId(senderId, recipientId);
}


@Override
public ChatRoom createRoom(ChatRoom chatRoom){
    return chatRoomJPARepository.saveAndFlush(chatRoom);
}


@Override
public List<ChatRoom> findAll(){
    return chatRoomJPARepository.findAll();
}


}