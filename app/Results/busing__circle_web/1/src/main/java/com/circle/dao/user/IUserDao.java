package com.circle.dao.user;
 import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import com.circle.pojo.user.User;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface IUserDao {


public Boolean updatePassword(Map<String,Object> paramMap)
;

public Boolean updatePasswordByMobilePhone(Map<String,Object> paramMap)
;

public boolean addUser(User user)
;

public Boolean updateMobilePhone(Map<String,Object> paramMap)
;

public User getUserByInviteCode(String inviteCode)
;

public User login(String mobilePhone,String password)
;

public boolean addWeixinUser(User user)
;

public List<User> queryUserList(Page page)
;

public Boolean updateUserInfo(Map<String,Object> paramMap)
;

public List<User> queryCircleUserList(String id,Page page)
;

public boolean updateUserLastLoginTimer(String mpbilePhone)
;

public User queryUserById(String id)
;

public int checkUserIsExtists(String mobilePhone)
;

}