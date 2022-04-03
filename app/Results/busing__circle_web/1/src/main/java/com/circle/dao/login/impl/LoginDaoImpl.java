package com.circle.dao.login.impl;
 import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import com.circle.utils.StringUtils;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.dao.login.ILoginDao;
import com.circle.pojo.user.User;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.Interface.ICommonDao;
@Repository
public class LoginDaoImpl implements ILoginDao{

 private  Logger logger;

 public  String UPDATA_USER_LASTLOGINTIMER;

 public  String UPDATA_USER_LASTLOGINTIMER_BY_WEIXIN;

 public  String QUERY_USER_BY_MOBILIPHONE_AND_PASS;

 public  String QUERY_USER_BY_WEIXIN_OPENID;

 public  String SEND_VALIDATE_MSG;

 public  String MSG_VALIDATE_OVER;

 public  String DELETE_MESSAGE;

 public  String MSG_CODE_OUTTIME;

 public  String MSG_CODE_CORRECT;

@Resource
 private ICommonDao commonDao;


public int checkVCodeIsOutTime(String mobilePhone,String code){
    // 封装SQL参数
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", mobilePhone);
    paramMap.put("code", code);
    return commonDao.queryForInt(MSG_CODE_OUTTIME, paramMap);
}


public Boolean checkMsgCorrect(String mobilePhone,String code){
    logger.info("[UserDaoImpl.sendMsg]: start...");
    // 查询结果用户
    int result = 0;
    // 封装SQL参数
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", mobilePhone);
    paramMap.put("code", code);
    // 查询获取单个用户信息
    result = commonDao.queryForInt(MSG_CODE_CORRECT, paramMap);
    logger.info("[UserDaoImpl.sendMsg]: end...");
    return result > 0;
}


public boolean updateUserLastLoginTimer(String mobilePhone){
    logger.debug("[UserDaoImpl.updateUserLastLoginTimer] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", mobilePhone);
    /**
     * 修改登录时间
     */
    int count = commonDao.update(UPDATA_USER_LASTLOGINTIMER, paramMap);
    logger.debug("[UserDaoImpl.updateUserLastLoginTimer] end...");
    return count > 0 ? true : false;
}


public boolean updateUserLastLoginTimerByWeixin(String openid){
    logger.debug("[UserDaoImpl.updateUserLastLoginTimerByWeixin] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("openid", openid);
    /**
     * 修改登录时间
     */
    int count = commonDao.update(UPDATA_USER_LASTLOGINTIMER_BY_WEIXIN, paramMap);
    logger.debug("[UserDaoImpl.updateUserLastLoginTimerByWeixin] end...");
    return count > 0 ? true : false;
}


public boolean msgValidateOver(String mobilePhone,String code){
    logger.info("[UserDaoImpl.msgValidate]: start...");
    // 查询结果用户
    int result = 0;
    // 封装SQL参数
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", mobilePhone);
    paramMap.put("code", code);
    // 查询获取单个用户信息
    result = commonDao.update(MSG_VALIDATE_OVER, paramMap);
    logger.info("[UserDaoImpl.msgValidate]: end...");
    return result > 0;
}


public Boolean deleteMessage(String mobilePhone){
    logger.info("[UserDaoImpl.deleteMessage]: start...");
    // 封装SQL参数
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", mobilePhone);
    // 查询获取单个用户信息
    int result = commonDao.update(DELETE_MESSAGE, paramMap);
    logger.info("[UserDaoImpl.deleteMessage]: end...");
    return result > 0;
}


public int sendMsg(String mobilePhone,String code){
    logger.info("[UserDaoImpl.sendMsg]: start...");
    // 查询结果用户
    int result = 0;
    // 封装SQL参数
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", mobilePhone);
    paramMap.put("code", code);
    // 查询获取单个用户信息
    result = commonDao.update(SEND_VALIDATE_MSG, paramMap);
    logger.info("[UserDaoImpl.sendMsg]: end...");
    return result;
}


public User login(String openid){
    logger.info("[UserDaoImpl.login]: start...");
    // 查询结果用户
    User user = null;
    // 封装SQL参数
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("openid", openid);
    logger.info("[LoginDaoImpl.login]: openid = " + openid);
    // 查询获取单个用户信息
    user = commonDao.queryForObject(QUERY_USER_BY_WEIXIN_OPENID, paramMap, User.class);
    logger.info("[UserDaoImpl.login]: end...");
    return user;
}


}