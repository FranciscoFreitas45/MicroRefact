package org.gliderwiki.web.user.service;
 import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.gliderwiki.web.common.service.EntityService;
import org.gliderwiki.web.domain.WeAddFriend;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.user.dao.UserConnectionDao;
import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.gliderwiki.Interface.EntityService;
import org.gliderwiki.Interface.CommonService;
import org.gliderwiki.DTO.WeUser;
import org.gliderwiki.DTO.MemberSessionVo;
@Service("userConnectionService")
public class UserConnectionServiceImpl implements UserConnectionService{

 private Logger logger;

@Autowired
 private  UserConnectionDao userConnectionDao;

@Autowired
 private  EntityService entityService;

@Resource(name = "commonService")
 private  CommonService commonService;


@Override
public List<AddFriendVo> getMyConnection(MemberSessionVo memberSessionVo){
    return userConnectionDao.getMyConnection(memberSessionVo);
}


@Override
public int addWeFriends(String arrayCheckId,Integer weUserIdx){
    String[] arrayUser = arrayCheckId.split(",");
    int size = arrayUser.length;
    int result = 0;
    WeAddFriend domain = null;
    for (int i = 0; i < size; i++) {
        domain = new WeAddFriend();
        domain.setWe_user_idx(weUserIdx);
        domain.setWe_use_yn("Y");
        domain.setWe_target_user_idx(Integer.parseInt(arrayUser[i].trim()));
        try {
            WeAddFriend addFriend = (WeAddFriend) entityService.getRowEntity(domain);
            if (addFriend == null) {
                domain.setWe_add_date(new Date());
                result = entityService.insertEntity(domain);
                // 알림추가
                WeUser weUser = commonService.getUserInfo(weUserIdx);
                commonService.requestAlarmInfo(weUserIdx, weUser.getWe_user_nick(), SystemConst.WIKI_ADD_FRIEND, Integer.parseInt(arrayUser[i].trim()), null, null);
            } else {
                return -2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return result;
}


@Override
public List<AddFriendVo> getConnectionToMe(Integer weUserIdx){
    MemberSessionVo userVo = new MemberSessionVo();
    userVo.setWeUserIdx(weUserIdx);
    return this.getConnectionToMe(userVo);
}


}