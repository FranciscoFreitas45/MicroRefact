package org.live.live.service;
 import org.live.app.vo.AppLiveRoomVo;
import org.live.common.base.BaseService;
import org.live.live.entity.Anchor;
import org.live.live.entity.LiveRoom;
import org.live.live.entity.MobileUser;
import org.live.live.vo.LiveRoomInfoVo;
import org.live.live.vo.LiveRoomVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface LiveRoomService extends BaseService<LiveRoom, String>{


public Page<LiveRoomVo> findLiveRooms(PageRequest page,String searchStr)
;

public void changeLiveRoomBanFlag(String liveRoomId,boolean liveRoomBanFlag)
;

public List<AppLiveRoomVo> findLiveRoomsForApp(String categoryId)
;

public LiveRoomInfoVo getLiveRoomInfo(String liveRoomId)
;

public List<AppLiveRoomVo> findLiveRoomsForAppSearch(String searchStr)
;

public LiveRoom getLiveRoomByRoomNum(String roomNum)
;

public List<AppLiveRoomVo> findAttentionLiveRoomsForUser(String userId)
;

public void reportLiveRoom(String userId,String liveRoomId)
;

public LiveRoom findLiveRoomByMobileUser(MobileUser user)
;

public LiveRoom findLiveRoomByAnchor(Anchor anchor)
;

}