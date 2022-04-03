package com.circle.dao.login;
 import java.security.NoSuchAlgorithmException;
import com.circle.pojo.user.User;
import com.xwtec.xwserver.exception.SPTException;
public interface ILoginDao {


public int checkVCodeIsOutTime(String mobilePhone,String code)
;

public Boolean checkMsgCorrect(String mobilePhone,String code)
;

public boolean updateUserLastLoginTimer(String mobilePhone)
;

public boolean updateUserLastLoginTimerByWeixin(String openid)
;

public boolean msgValidateOver(String mobilePhone,String code)
;

public Boolean deleteMessage(String mobilePhone)
;

public int sendMsg(String mobilePhone,String code)
;

public User login(String openid)
;

}