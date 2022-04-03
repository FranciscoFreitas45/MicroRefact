package com.circle.service.user.impl;
 import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.circle.dao.user.IUserDao;
import com.circle.pojo.user.User;
import com.circle.service.user.IUserService;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.ProUtil;
import com.xwtec.xwserver.util.StringUtil;
@Service
public class UserServiceImpl implements IUserService{

@Resource
 private  IUserDao userDao;


public boolean addWeixinUser(User user){
    return userDao.addWeixinUser(user);
}


public List<User> queryUserList(Page page){
    return userDao.queryUserList(page);
}


public Boolean updatePassword(Map<String,Object> paramMap){
    return userDao.updatePassword(paramMap);
}


public Boolean updatePasswordByMobilePhone(Map<String,Object> paramMap){
    return userDao.updatePasswordByMobilePhone(paramMap);
}


public Boolean updateUserInfo(Map<String,Object> paramMap){
    return userDao.updateUserInfo(paramMap);
}


public boolean addUser(User user){
    return userDao.addUser(user);
}


public List<User> queryCircleUserList(String id,Page page){
    return userDao.queryCircleUserList(id, page);
}


public Boolean updateMobilePhone(Map<String,Object> paramMap){
    return userDao.updateMobilePhone(paramMap);
}


public boolean updateUserLastLoginTimer(String mpbilePhone){
    return userDao.updateUserLastLoginTimer(mpbilePhone);
}


public User queryUserById(String id){
    User user = userDao.queryUserById(id);
    if (user != null) {
        if (StringUtil.isEmpty(user.getHeadImage())) {
            user.setHeadImage("/images/def_adv.png");
        }
        user.setImagePath(user.getHeadImage());
        user.setHeadImage(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + user.getHeadImage());
    }
    return user;
}


public int checkUserIsExtists(String mobilePhone){
    return userDao.checkUserIsExtists(mobilePhone);
}


public User getUserByInviteCode(String inviteCode){
    return userDao.getUserByInviteCode(inviteCode);
}


}