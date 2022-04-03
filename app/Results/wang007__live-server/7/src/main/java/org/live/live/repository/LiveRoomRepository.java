package org.live.live.repository;
 import org.live.app.vo.AppLiveRoomVo;
import org.live.common.base.BaseRepository;
import org.live.live.entity.Anchor;
import org.live.live.entity.LiveRoom;
import org.live.live.entity.MobileUser;
import org.live.live.vo.LiveRoomInfoVo;
import org.live.live.vo.LiveRoomVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
public interface LiveRoomRepository extends BaseRepository<LiveRoom, String>{


public Page<LiveRoomVo> findLiveRooms(PageRequest page,String searchStr)
;

@Query("select new org.live.app.vo.AppLiveRoomVo(lr.id, lr.anchor.id, lr.roomNum, lr.coverUrl, lr.liveRoomUrl, lr.roomName, lr.anchor.user.nickname, lr.anchor.user.headImgUrl, lr.onlineCount, " + "lr.liveFlag) from LiveRoom lr where lr.banLiveFlag=false and lr.liveCategory.enabled=true and lr.liveCategory.id=:categoryId order by lr.liveFlag desc, lr.onlineCount desc")
public List<AppLiveRoomVo> findLiveRoomsForAppByCategory(String categoryId)
;

@Query("select new org.live.app.vo.AppLiveRoomVo(lr.id, lr.anchor.id, lr.roomNum, lr.coverUrl, lr.liveRoomUrl, lr.roomName, lr.anchor.user.nickname, lr.anchor.user.headImgUrl, lr.onlineCount, " + "lr.liveFlag) from LiveRoom lr where lr.banLiveFlag=false and lr.liveCategory.enabled=true order by lr.liveFlag desc, lr.onlineCount desc")
public List<AppLiveRoomVo> findLiveRoomsForApp()
;

@Query("select new org.live.live.vo.LiveRoomInfoVo(lr.liveCategory.categoryName, lr.roomNum, lr.roomName, " + "lr.roomLabel, lr.historyMaxOnlineCount, lr.anchor.description, lr.anchor.user.account, lr.anchor.user.nickname, " + "lr.anchor.user.member.sex, lr.anchor.user.headImgUrl) from LiveRoom lr where lr.id=:liveRoomId")
public LiveRoomInfoVo getLiveRoomInfo(String liveRoomId)
;

@Query("select new org.live.app.vo.AppLiveRoomVo(lr.id, lr.anchor.id, lr.roomNum, lr.coverUrl, lr.liveRoomUrl, lr.roomName, lr.anchor.user.nickname, lr.anchor.user.headImgUrl, lr.onlineCount, " + "lr.liveFlag) from LiveRoom lr where lr.banLiveFlag=false and lr.liveCategory.enabled=true and " + "(lr.roomNum like %:searchStr% or lr.roomName like %:searchStr% or lr.anchor.user.account like %:searchStr% or lr.anchor.user.nickname like %:searchStr%) order by lr.liveFlag desc, lr.onlineCount desc")
public List<AppLiveRoomVo> findLiveRoomsForAppSearch(String searchStr)
;

public LiveRoom getLiveRoomByRoomNum(String roomNum)
;

@Transactional
@Query("update LiveRoom lr set lr.onlineCount=:onlineCount where lr.roomNum=:roomNum and lr.liveFlag=true")
@Modifying
public void setOnlineCountByLiveRoomNum(String roomNum,int onlineCount)
;

@Query("select lr from LiveRoom lr, MobileUser mu, Anchor an where lr.anchor=an and lr.anchor.user=mu and lr.anchor.user=:user")
public LiveRoom findLiveRoomByMobileUser(MobileUser user)
;

@Query("select count(*) from LiveRoom r where r.liveCategory.id=:id")
public long countLiveRoomByLiveCategory(String liveCategoryId)
;

public LiveRoom findLiveRoomByAnchor(Anchor anchor)
;

public LiveRoom getLiveRoom(String idWHGX);

public void setLiveRoom(String idWHGX,LiveRoom liveRoom);

public void setLiveCategory(String id,LiveCategory liveCategory);

public void setRoomNum(String id,String roomNum);

public void setRoomLabel(String id,String roomLabel);

public void setLiveRoomUrl(String id,String liveRoomUrl);

public void setCoverUrl(String id,String coverUrl);

public void setRoomName(String id,String roomName);

public boolean isBanLiveFlag(String id);

}