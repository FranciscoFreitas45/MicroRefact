package com.tech.repositories;
 import com.tech.models.entities.chatroom.ChatroomMembers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IChatroomMembersRepository extends JpaRepository<ChatroomMembers, Long>{


public List<ChatroomMembers> findByRoomMember(Long room_member)
;

public List<ChatroomMembers> findByRoomId(Long room_id)
;

public ChatroomMembers findByRoomIdAndMember(Long room_id,Long room_member)
;

}