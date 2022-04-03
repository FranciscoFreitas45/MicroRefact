package org.live.live.service.impl;
 import org.live.app.vo.SimpleUserVo;
import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.live.entity.Attention;
import org.live.live.entity.LiveRoom;
import org.live.live.entity.MobileUser;
import org.live.live.repository.AttentionRepository;
import org.live.live.repository.MobileUserRepository;
import org.live.live.service.MobileUserService;
import org.live.live.vo.MobileUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.live.Interface.AttentionRepository;
import org.live.DTO.Attention;
@Service(value = "mobileUserService")
public class MobileUserServiceImpl extends BaseServiceImpl<MobileUser, String>implements MobileUserService{

@Resource
 private  MobileUserRepository mobileUserRepository;

@Resource
 private  AttentionRepository attentionRepository;


@Override
public void attentionLiveRoom(MobileUser user,LiveRoom liveRoom){
    Attention attention = new Attention();
    attention.setUser(user);
    attention.setLiveRoom(liveRoom);
    attention.setCreateTime(new Date());
    attentionRepository.save(attention);
}


@Override
public SimpleUserVo findMobileUserByAccountWithSimple(String account){
    return mobileUserRepository.findMobileUserByAccountWithSimple(account);
}


public void setOutDate(List<String> ids){
    for (String id : ids) {
        MobileUser record = mobileUserRepository.getOne(id);
        record.setOutDateFlag(true);
        mobileUserRepository.save(record);
    }
}


@Override
public MobileUser findMobileUserByAccount(String account){
    return mobileUserRepository.findMobileUserByAccount(account);
}


@Override
public BaseRepository<MobileUser,String> getRepository(){
    return mobileUserRepository;
}


public DataTableModel findPage(HttpServletRequest request){
    // 查询总记录数
    Long recordsTotal = mobileUserRepository.count();
    // 映射请求参数
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 查询分页数据
    Page<MobileUserVo> page = mobileUserRepository.find((PageRequest) params.get("pageRequest"), (Map<String, Object>) params.get("filter"));
    // 映射成定制模型
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


}