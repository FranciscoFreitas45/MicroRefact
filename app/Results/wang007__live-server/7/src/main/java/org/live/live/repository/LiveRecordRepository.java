package org.live.live.repository;
 import org.live.common.base.BaseRepository;
import org.live.live.entity.LiveRecord;
import org.live.live.entity.LiveRoom;
import org.live.live.vo.LiveRecordVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Map;
public interface LiveRecordRepository extends BaseRepository<LiveRecord, String>{


@Query("select lr from LiveRecord lr where lr.liveRoom=:liveRoom and lr.startTime in " + "(select max(lr0.startTime) from LiveRecord lr0 where lr0.liveRoom=:liveRoom)")
public LiveRecord getCurrentLiveRecordByLiveRoom(LiveRoom liveRoom)
;

@Query("select lr from LiveRecord lr where lr.liveRoom.roomNum=:roomNum and lr.startTime in " + "(select max(lr0.startTime) from LiveRecord lr0 where lr0.liveRoom.roomNum=:roomNum)")
public LiveRecord getCurrentLiveRecordByRoomNum(String roomNum)
;

public Page<LiveRecordVo> find(PageRequest pageRequest,Map<String,Object> filter)
;

}