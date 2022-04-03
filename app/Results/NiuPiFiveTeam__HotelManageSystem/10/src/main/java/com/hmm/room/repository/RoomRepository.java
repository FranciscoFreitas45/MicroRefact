package com.hmm.room.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.room.entity.Room;
@Repository
public interface RoomRepository extends PagingAndSortingRepository<Room, Long>{


@Query("from Room r where r.floorNode.floorId = ?1 order by r.roomNo")
public List<Room> findFloorNodes(Long floorId)
;

@Query("from Room r where r.roomNo = ?1 order by r.roomNo")
public Room findRoomByRoomNo(String selectRoomNo)
;

public void setChildNodes(Long floorId,List<Room> childNodes);

public List<Room> getChildNodes(Long floorId);

}