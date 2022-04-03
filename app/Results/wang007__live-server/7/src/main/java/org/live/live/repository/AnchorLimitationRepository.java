package org.live.live.repository;
 import org.live.common.base.BaseRepository;
import org.live.live.entity.AnchorLimitation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AnchorLimitationRepository extends BaseRepository<AnchorLimitation, String>{


@Query("select distinct new org.live.app.vo.LimitationVo(al.user.id, al.user.account, al.user.nickname, al.liveRoom.id, " + "al.liveRoom.roomNum, al.liveRoom.roomName, al.limitType) from AnchorLimitation al where al.liveRoom.id=:liveRoomId " + "order by al.createTime DESC")
public List<AnchorLimitation> findLimitationsForLiveRoom(String liveRoomId)
;

@Query("select distinct al.limitType from AnchorLimitation al where al.user.id=:userId and al.liveRoom.id=:liveRoomId")
public List<Integer> findAnchorLimitations(String userId,String liveRoomId)
;

public void removeAnchorLimitationByUser_AccountAndLiveRoom_RoomNumAndLimitType(String account,String roomNum,int limitType)
;

}