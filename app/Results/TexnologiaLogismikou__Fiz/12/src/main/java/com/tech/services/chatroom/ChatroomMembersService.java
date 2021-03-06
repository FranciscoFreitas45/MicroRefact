package com.tech.services.chatroom;
 import com.tech.models.entities.chatroom.ChatroomMembers;
import com.tech.repositories.IChatroomMembersRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tech.services.interfaces.IChatroomMembersService;
@Service
public class ChatroomMembersService implements IChatroomMembersService{

@Autowired
 private  IChatroomMembersRepository repository;


@Transactional
@Override
public void add(ChatroomMembers newRecord){
    repository.save(newRecord);
}


@Transactional
@Override
public List<ChatroomMembers> findByRoomMember(Long room_member){
    return repository.findByRoomMember(room_member);
}


@Transactional
@Override
public boolean checkIfMemberExistsInChatroom(Long member_id,Long room_id){
    // kapios ta ebale anapoda :D
    return repository.findByRoomIdAndMember(room_id, member_id) != null;
}


@Transactional
@Override
public List<ChatroomMembers> findByRoomId(Long room_id){
    return repository.findByRoomId(room_id);
}


@Transactional
@Override
public ChatroomMembers findByRoomIdAndMember(Long member_id,Long room_id){
    return repository.findByRoomIdAndMember(room_id, member_id);
}


@Transactional
@Override
public Long countRecordsOfRoom(Long room_id){
    long i = 0;
    for (ChatroomMembers vLookUp : repository.findByRoomId(room_id)) {
        i++;
    }
    return i;
}


@Transactional
@Override
public Long countRecordsOfMember(Long member_id){
    long i = 0;
    for (ChatroomMembers vLookUp : repository.findByRoomMember(member_id)) {
        i++;
    }
    return i;
}


@Transactional
@Override
public boolean delete(ChatroomMembers deleteRecord){
    repository.delete(deleteRecord);
    return true;
}


@Transactional
@Override
public Long countRecords(){
    return repository.count();
}


}