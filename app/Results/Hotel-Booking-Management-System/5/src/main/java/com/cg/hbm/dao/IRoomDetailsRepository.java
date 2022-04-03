package com.cg.hbm.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.hbm.entites.RoomDetails;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface IRoomDetailsRepository extends JpaRepository<RoomDetails, Integer>{


@Transactional
@Modifying
@Query(value = "update room_details r set r.room_id = ?1 where r.room_id = ?1", nativeQuery = true)
public void setRoomdetails(int room_id,RoomDetails roomdetails);

@Query(value = "Select * from room_details r  where r.room_id = ?1", nativeQuery = true)
public RoomDetails getRoomdetails(int room_id);

}