package org.live.live.service.impl;
 import org.live.app.vo.AppLiveRoomVo;
import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.utils.CreateOrderNoUtils;
import org.live.common.utils.DateUtil;
import org.live.live.entity;
import org.live.live.repository;
import org.live.live.service.LiveRoomService;
import org.live.live.vo.LiveRoomInfoVo;
import org.live.live.vo.LiveRoomVo;
import org.live.websocket.chat.ChatHallManager;
import org.live.websocket.chat.OnChatListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import org.live.Interface.MobileUserRepository;
import org.live.Interface.ReportRepository;
import org.live.DTO.Report;
@Service("liveRoomService")
public class LiveRoomServiceImpl extends BaseServiceImpl<LiveRoom, String>implements LiveRoomService,OnChatListener{

@Resource
 private  LiveRoomRepository repository;

@Resource
 private  MobileUserRepository mobileUserRepository;

@Resource
 private  AnchorLimitationRepository anchorLimitationRepository;

@Resource
 private  AttentionRepository attentionRepository;

@Resource
 private  LiveRecordRepository liveRecordRepository;

@Resource
 private  ReportRepository reportRepository;

 private  double DEFAULT_REPORT_SPAN;


@Transactional
@Override
public void onAnchorDissolveChatRoom(String chatRoomNum){
    LiveRoom liveRoom = repository.getLiveRoomByRoomNum(chatRoomNum);
    if (liveRoom != null) {
        // 主播下线
        liveRoom.setLiveFlag(false);
        // 设置在线人数为0
        liveRoom.setOnlineCount(0);
        repository.save(liveRoom);
        // 修改直播记录的结束时间
        LiveRecord liveRecord = liveRecordRepository.getCurrentLiveRecordByLiveRoom(liveRoom);
        if (liveRecord != null) {
            liveRecord.setEndTime(new Date());
            liveRecordRepository.save(liveRecord);
        }
    }
}


@Override
public BaseRepository<LiveRoom,String> getRepository(){
    return repository;
}


@Override
public LiveRoomInfoVo getLiveRoomInfo(String liveRoomId){
    return repository.getLiveRoomInfo(liveRoomId);
}


@Override
public List<AppLiveRoomVo> findLiveRoomsForAppSearch(String searchStr){
    return repository.findLiveRoomsForAppSearch(searchStr);
}


@Override
public List<AppLiveRoomVo> findAttentionLiveRoomsForUser(String userId){
    return attentionRepository.findAttentionLiveRoomsForUser(userId);
}


@Override
public void onUserAttentionChatRoom(String chatRoomNum,String userAccount){
    // 主播间
    LiveRoom liveRoom = repository.getLiveRoomByRoomNum(chatRoomNum);
    MobileUser mobileUser = mobileUserRepository.findMobileUserByAccount(userAccount);
    Attention attention = new Attention();
    attention.setLiveRoom(liveRoom);
    attention.setUser(mobileUser);
    attention.setCreateTime(new Date());
    attentionRepository.save(attention);
}


@Transactional
@Override
public void onRelieveUserAttentionChatRoom(String chatRoomNum,String userAccount){
    attentionRepository.removeAttentionByUser_AccountAndLiveRoom_RoomNum(userAccount, chatRoomNum);
}


@Override
public void onKickoutUserOnChatRoom(String chatRoomNum,String userAccount){
    // 主播间
    LiveRoom liveRoom = repository.getLiveRoomByRoomNum(chatRoomNum);
    MobileUser mobileUser = mobileUserRepository.findMobileUserByAccount(userAccount);
    AnchorLimitation limitation = new AnchorLimitation();
    limitation.setCreateTime(new Date());
    limitation.setUser(mobileUser);
    limitation.setLiveRoom(liveRoom);
    limitation.setLimitType(AnchorLimitation.LIMIT_TYPE_KICKOUT);
    anchorLimitationRepository.save(limitation);
}


@Transactional
@Override
public void onRelieveKickoutUserOnChatRoom(String chatRoomNum,String userAccount){
    anchorLimitationRepository.removeAnchorLimitationByUser_AccountAndLiveRoom_RoomNumAndLimitType(userAccount, chatRoomNum, AnchorLimitation.LIMIT_TYPE_KICKOUT);
}


@Override
public Page<LiveRoomVo> findLiveRooms(PageRequest page,String searchStr){
    return repository.findLiveRooms(page, searchStr);
}


@Transactional
@Override
public void onAnchorOpenChatRoom(String chatRoomNum){
    LiveRoom liveRoom = repository.getLiveRoomByRoomNum(chatRoomNum);
    // 设置上线
    liveRoom.setLiveFlag(true);
    repository.save(liveRoom);
    // 保存直播记录
    LiveRecord record = new LiveRecord();
    record.setStartTime(new Date());
    record.setLiveRoom(liveRoom);
    record.setRecordNum(CreateOrderNoUtils.getCreateOrderNo());
    liveRecordRepository.save(record);
}


@Transactional
@Override
public void onRelieveShutupUserOnChatRoom(String chatRoomNum,String userAccount){
    anchorLimitationRepository.removeAnchorLimitationByUser_AccountAndLiveRoom_RoomNumAndLimitType(userAccount, chatRoomNum, AnchorLimitation.LIMIT_TYPE_SHUTUP);
}


@Transactional
@Override
public void changeLiveRoomBanFlag(String liveRoomId,boolean liveRoomBanFlag){
    LiveRoom liveRoom = repository.findOne(liveRoomId);
    String account = liveRoom.getAnchor().getUser().getAccount();
    // 禁播，当系统禁播主播时，websocket的关闭由系统完成，所以不会调用ChatListener相关方法。
    if (liveRoomBanFlag) {
        if (liveRoom.isLiveFlag()) {
            // 正在直播, 解散直播间
            ChatHallManager.dissolveChatRoom(liveRoom.getRoomNum());
            // 修改直播间状态
            liveRoom.setLiveFlag(false);
            liveRoom.setOnlineCount(0);
            // 更新直播记录的结束时间。
            LiveRecord liveRecord = liveRecordRepository.getCurrentLiveRecordByLiveRoom(liveRoom);
            if (liveRecord != null) {
                liveRecord.setEndTime(new Date());
                liveRecordRepository.save(liveRecord);
            }
        }
    }
    liveRoom.setBanLiveFlag(liveRoomBanFlag);
    repository.save(liveRoom);
}


@Override
public void onShutupUserOnChatRoom(String chatRoomNum,String userAccount){
    // 主播间
    LiveRoom liveRoom = repository.getLiveRoomByRoomNum(chatRoomNum);
    MobileUser mobileUser = mobileUserRepository.findMobileUserByAccount(userAccount);
    AnchorLimitation limitation = new AnchorLimitation();
    limitation.setCreateTime(new Date());
    limitation.setUser(mobileUser);
    limitation.setLiveRoom(liveRoom);
    limitation.setLimitType(AnchorLimitation.LIMIT_TYPE_SHUTUP);
    anchorLimitationRepository.save(limitation);
}


@Override
public List<AppLiveRoomVo> findLiveRoomsForApp(String categoryId){
    if (categoryId != null && !"".equals(categoryId)) {
        return repository.findLiveRoomsForAppByCategory(categoryId);
    }
    return repository.findLiveRoomsForApp();
}


@Override
public LiveRoom getLiveRoomByRoomNum(String roomNum){
    return repository.getLiveRoomByRoomNum(roomNum);
}


@Override
public void reportLiveRoom(String userId,String liveRoomId){
    Date date = new Date();
    // 获取最近的举报。
    Report reportInDb = reportRepository.getRecentlyReport(userId, liveRoomId);
    if (reportInDb != null) {
        double timeSpan = DateUtil.differenceDay(date, reportInDb.getCreateTime()).doubleValue();
        if (timeSpan < DEFAULT_REPORT_SPAN) {
            // 举报时间未超过默认举报时间差值。
            return;
        }
    }
    MobileUser mobileUser = mobileUserRepository.findOne(userId);
    LiveRoom liveRoom = repository.findOne(liveRoomId);
    if (mobileUser != null && liveRoom != null) {
        Report report = new Report();
        report.setUser(mobileUser);
        report.setLiveRoom(liveRoom);
        report.setReportNum(CreateOrderNoUtils.getCreateOrderNoByTime());
        report.setCreateTime(date);
        // 未处理状态
        report.setHandleType(false);
        reportRepository.save(report);
    }
}


@Override
public LiveRoom findLiveRoomByMobileUser(MobileUser user){
    return repository.findLiveRoomByMobileUser(user);
}


@Override
public LiveRoom findLiveRoomByAnchor(Anchor anchor){
    return repository.findLiveRoomByAnchor(anchor);
}


}