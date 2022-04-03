package com.hmm.logistics.roomClean.repository;
 import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.logistics.roomClean.entity.RoomClean;
// 扫描持久层
@Repository
public interface RoomCleanRepository extends JpaSpecificationExecutor<RoomClean>, PagingAndSortingRepository<RoomClean, Long>{


@Query("FROM RoomClean rc WHERE rc.room.roomId=?1")
public RoomClean findByRoomId(Long roomId)
;

}