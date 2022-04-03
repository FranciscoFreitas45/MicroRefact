package org.live.live.service.impl;
 import org.live.app.vo.ApplyAnchorVo;
import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.constants.Constants;
import org.live.common.getui.PushInterface;
import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.live.entity.Anchor;
import org.live.live.entity.ApplyAnchor;
import org.live.live.entity.LiveRoom;
import org.live.live.entity.MobileUser;
import org.live.live.repository.AnchorRepository;
import org.live.live.repository.ApplyAnchorRepository;
import org.live.live.repository.LiveRoomRepository;
import org.live.live.repository.MobileUserRepository;
import org.live.live.service.ApplyAnchorService;
import org.live.live.service.MobileUserService;
import org.live.live.vo.ApplyInfoVo;
import org.live.live.vo.ApplyVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.live.Interface.MobileUserRepository;
import org.live.Interface.LiveRoomRepository;
import org.live.Interface.AnchorRepository;
import org.live.DTO.Anchor;
import org.live.DTO.LiveRoom;
@Service("applyAnchorService")
public class ApplyAnchorServiceImpl extends BaseServiceImpl<ApplyAnchor, String>implements ApplyAnchorService{

@Value("${system.rtmpAddrPrefix}")
 private  String rtmpAddrPrefix;

@Resource
 private  ApplyAnchorRepository repository;

@Resource
 private  MobileUserRepository mobileUserRepository;

@Resource
 private  LiveRoomRepository liveRoomRepository;

@Resource
 private  AnchorRepository anchorRepository;


@Override
public Date getLastApplyAnchorDate(String userId){
    return repository.getLastApplyAnchorDate(userId);
}


@Override
public ApplyInfoVo getApplyInfo(String applyId){
    return repository.getApplyInfo(applyId);
}


@Override
public BaseRepository<ApplyAnchor,String> getRepository(){
    return repository;
}


@Override
public void createApplyAnchorForm(ApplyAnchorVo applyAnchorVo){
}


@Transactional
@Override
public void saveApplyPassFlag(String applyId,int passFlag,String reason){
    // 当前申请表
    ApplyAnchor applyAnchor = repository.getOne(applyId);
    if (passFlag == 2) {
        // 审核未通过
        applyAnchor.setPassFlag(ApplyAnchor.ADUIT_NOT_PASS);
        applyAnchor.setNoPassReason(reason);
        repository.save(applyAnchor);
        PushInterface.getInstance().pushToSingle(applyAnchor.getUser().getAccount(), "高校直播", "您的主播申请审核未通过，" + reason);
        return;
    } else if (passFlag == 1) {
        // 审核通过
        applyAnchor.setPassFlag(ApplyAnchor.ADUIT_PASS);
        MobileUser mobileUser = applyAnchor.getUser();
        // 设置移动端用户的主播标志
        mobileUser.setAnchorFlag(true);
        Anchor anchorInDB = anchorRepository.findAnchorByUser(mobileUser);
        if (anchorInDB != null) {
            // 此时该申请表的用户已经是主播
            List<ApplyAnchor> applyAnchors = repository.findApplyAnchorsByUser(mobileUser);
            for (ApplyAnchor apply : applyAnchors) {
                if (apply.getPassFlag() == 0) {
                    apply.setPassFlag(ApplyAnchor.ADUIT_IGNORE);
                    repository.save(apply);
                }
            }
            return;
        }
        // 主播
        Anchor anchor = new Anchor();
        anchor.setUser(mobileUser);
        anchor.setCreateTime(new Date());
        anchor.setRealName(applyAnchor.getRealName());
        anchor.setIdCard(applyAnchor.getIdCard());
        anchor.setDescription("");
        // 直播间
        LiveRoom liveRoom = new LiveRoom();
        // 主播
        liveRoom.setAnchor(anchor);
        // 直播分类
        liveRoom.setLiveCategory(applyAnchor.getCategory());
        // 直播间号等于账号，没毛病
        liveRoom.setRoomNum(mobileUser.getAccount());
        liveRoom.setRoomLabel("");
        liveRoom.setLiveRoomUrl(rtmpAddrPrefix + mobileUser.getAccount());
        liveRoom.setCoverUrl(Constants.DEFAULT_LIVE_ROOM_COVER_URL);
        liveRoom.setRoomName(mobileUser.getNickname() + "的房间");
        repository.save(applyAnchor);
        mobileUserRepository.save(mobileUser);
        anchorRepository.save(anchor);
        liveRoomRepository.save(liveRoom);
        PushInterface.getInstance().pushToSingle(mobileUser.getAccount(), "高校直播", "您的主播申请审核通过，可以进行直播了！");
    }
}


@Override
public DataTableModel findByPage(HttpServletRequest request,boolean auditFlag){
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 分页数据
    Page<ApplyVo> page = repository.findApplys((PageRequest) params.get("pageRequest"), (String) params.get("searchVal"), auditFlag);
    // 总记录数
    Long recordsTotal = repository.count();
    // dataTablel定制model
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


@Override
public boolean judgeUserApplyCount(String userId,int systemMaxCount){
    long userApplyCount = repository.countApplyAnchorByUser_Id(userId);
    if (userApplyCount >= systemMaxCount) {
        return false;
    }
    return true;
}


}