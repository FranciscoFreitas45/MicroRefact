package org.live.live.repository;
 import org.live.app.vo.AppLiveRoomVo;
import org.live.common.base.BaseRepository;
import org.live.live.entity.Attention;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AttentionRepository extends BaseRepository<Attention, String>{


public List<Attention> findAttentionsByUser_IdAndLiveRoom_Id(String userId,String liveRoomId)
;

public long countAttentionsByLiveRoom_Id(String liveRoomId)
;

public void removeAttentionByUser_AccountAndLiveRoom_RoomNum(String account,String roomNum)
;

@Query("select new org.live.app.vo.AppLiveRoomVo(a.liveRoom.id, a.liveRoom.anchor.id, a.liveRoom.roomNum, a.liveRoom.coverUrl, a.liveRoom.liveRoomUrl, " + "a.liveRoom.roomName, a.liveRoom.anchor.user.nickname, a.liveRoom.anchor.user.headImgUrl, a.liveRoom.onlineCount, a.liveRoom.liveFlag) from Attention a " + "where a.liveRoom.banLiveFlag=false and a.liveRoom.liveCategory.enabled=true and a.user.id=:userId order by a.liveRoom.liveFlag desc, a.liveRoom.onlineCount desc")
public List<AppLiveRoomVo> findAttentionLiveRoomsForUser(String userId)
;

public void setUser(String id,MobileUser user);

public void setLiveRoom(String id,LiveRoom liveRoom);

public void setCreateTime(String id,Date createTime);

}