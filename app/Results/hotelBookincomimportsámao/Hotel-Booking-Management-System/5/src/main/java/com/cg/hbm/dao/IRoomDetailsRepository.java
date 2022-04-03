package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.hbm.entites.RoomDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface IRoomDetailsRepository extends JpaRepository<RoomDetails, Integer>{


@Modifying
@Query(value = "update room_details u set u.room_id = ?1 where u.room_id = ?2", nativeQuery = true)
public void setRoomdetails(int room_id,RoomDetails roomdetails);

@Query(value = "Select * from room_details b  where b.room_id = ?1", nativeQuery = true)
public RoomDetails getRoomdetails(int room_id);

}