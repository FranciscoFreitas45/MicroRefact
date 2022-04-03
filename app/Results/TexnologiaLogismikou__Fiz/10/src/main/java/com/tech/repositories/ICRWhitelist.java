package com.tech.repositories;
 import com.tech.models.entities.chatroom.ChatroomWhitelist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICRWhitelist extends JpaRepository<ChatroomWhitelist, Long>{


public ChatroomWhitelist findByRoomIDAndRoomName(Long room_id,Long room_member)
;

public List<ChatroomWhitelist> findByRoomMember(Long room_member)
;

public List<ChatroomWhitelist> findByRoomID(Long room_id)
;

}