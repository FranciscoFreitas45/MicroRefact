package com.circle.service.login.impl;
 import java.security.NoSuchAlgorithmException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.circle.dao.login.ILoginDao;
import com.circle.pojo.user.User;
import com.circle.service.login.ILoginService;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.ProUtil;
@Service
public class LoginServiceImpl implements ILoginService{

@Resource
 private  ILoginDao loginDao;


public int checkVCodeIsOutTime(String mobilePhone,String code){
    return loginDao.checkVCodeIsOutTime(mobilePhone, code);
}


public Boolean checkMsgCorrect(String mobilePhone,String code){
    return loginDao.checkMsgCorrect(mobilePhone, code);
}


public boolean updateUserLastLoginTimer(String mobilePhone){
    return loginDao.updateUserLastLoginTimer(mobilePhone);
}


public boolean updateUserLastLoginTimerByWeixin(String openid){
    return loginDao.updateUserLastLoginTimerByWeixin(openid);
}


public boolean msgValidateOver(String mobilePhone,String code){
    return loginDao.msgValidateOver(mobilePhone, code);
}


@Override
public Boolean deleteMessage(String mobilePhone){
    return loginDao.deleteMessage(mobilePhone);
}


public int sendMsg(String mobilePhone,String code){
    return loginDao.sendMsg(mobilePhone, code);
}


public User login(String openid){
    User user = loginDao.login(openid);
    if (user != null) {
        user.setImagePath(user.getHeadImage());
        user.setHeadImage(ProUtil.get(com.circle.constant.ConstantBusiKeys.PropertiesKey.DOMAIN) + user.getHeadImage());
    }
    return user;
}


}