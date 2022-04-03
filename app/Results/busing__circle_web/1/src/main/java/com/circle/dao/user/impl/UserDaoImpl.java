package com.circle.dao.user.impl;
 import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.constant.CircleTable;
import com.circle.dao.user.IUserDao;
import com.circle.pojo.user.User;
import com.circle.utils.StringUtils;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.StringUtil;
import com.Interface.ICommonDao;
@Repository
public class UserDaoImpl implements IUserDao{

 private  Logger logger;

 public  String QUERY_USER_LIST;

 public  String QUERY_USER_BY_ID;

 public  String QUERY_CRICLE_USER;

 public  String UPDATA_USER_LASTLOGINTIMER;

 public  String QUERY_USER_BY_MOBILIPHONE_AND_PASS;

 private  String UPDATEUSERINFO;

 private  String UPDATEPASSWORD;

 private  String UPDATEPASSWORDBYMOBILEPHONE;

 private  String UPDATEMobilePhone;

 private  String QUERY_CONUT_BY_MOBILEPHONE;

 private  String ADD_USER;

 private  String ADD_WEIXIN_USER;

 private  String GET_USER_BY_INVITE_CODE;

@Resource
 private ICommonDao commonDao;


public Boolean updatePassword(Map<String,Object> paramMap){
    return commonDao.update(UPDATEPASSWORD, paramMap) > 0;
}


public Boolean updatePasswordByMobilePhone(Map<String,Object> paramMap){
    return commonDao.update(UPDATEPASSWORDBYMOBILEPHONE, paramMap) > 0;
}


public boolean addUser(User user){
    logger.info("[UserDaoImpl.addUser]: start...");
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", user.getMobilePhone());
    paramMap.put("name", user.getName());
    paramMap.put("headImage", user.getHeadImage());
    paramMap.put("password", StringUtils.md5Sum(user.getPassword()));
    paramMap.put("inviteCode", user.getInviteCode());
    paramMap.put("inviteUserId", user.getInviteUserId() + "");
    logger.info("[UserDaoImpl.addUser]: end...");
    boolean flag = commonDao.update(ADD_USER, paramMap) > 0 ? true : false;
    user.setId(commonDao.getLastId(CircleTable.USER.getTableName(), "id"));
    return flag;
}


public Boolean updateMobilePhone(Map<String,Object> paramMap){
    return commonDao.update(UPDATEMobilePhone, paramMap) > 0;
}


public User getUserByInviteCode(String inviteCode){
    try {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("inviteCode", inviteCode);
        return (User) commonDao.queryForObject(GET_USER_BY_INVITE_CODE, paramMap, User.class);
    } catch (SPTException e) {
        e.printStackTrace();
    }
    return null;
}


public User login(String mobilePhone,String password){
    logger.info("[UserDaoImpl.login]: start...");
    // 查询结果用户
    User user = null;
    // 封装SQL参数
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", mobilePhone);
    paramMap.put("password", password);
    logger.info("[LoginDaoImpl.login]: mobilePhone = " + mobilePhone);
    // 查询获取单个用户信息
    user = commonDao.queryForObject(QUERY_USER_BY_MOBILIPHONE_AND_PASS, paramMap, User.class);
    logger.info("[UserDaoImpl.login]: end...");
    return user;
}


public boolean addWeixinUser(User user){
    logger.info("[UserDaoImpl.addWeixinUser]: start...");
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", user.getMobilePhone());
    paramMap.put("name", user.getName());
    paramMap.put("inviteCode", user.getInviteCode());
    paramMap.put("inviteUserId", user.getInviteUserId() + "");
    paramMap.put("weixinName", user.getWeixinName());
    paramMap.put("weixinImage", user.getWeixinImage());
    paramMap.put("weixinOpenid", user.getWeixinOpenid());
    logger.info("[UserDaoImpl.addWeixinUser]: end...");
    boolean flag = commonDao.update(ADD_WEIXIN_USER, paramMap) > 0 ? true : false;
    user.setId(commonDao.getLastId(CircleTable.USER.getTableName(), "id"));
    return flag;
}


public List<User> queryUserList(Page page){
    logger.debug("[UserDaoImpl.queryUserList] start...");
    StringBuilder sql = new StringBuilder(QUERY_USER_LIST);
    if (page != null && page.getSearchParam() != null) {
        if (page.getSearchParam().containsKey("name") && !StringUtil.isEmpty(page.getSearchParam().get("name"))) {
            sql.append(" and t.name LIKE '%" + page.getSearchParam().get("name") + "%'");
        }
        if (page.getSearchParam().containsKey("mobilephone") && !StringUtil.isEmpty(page.getSearchParam().get("mobilephone"))) {
            sql.append(" and t.mobilephone LIKE '%" + page.getSearchParam().get("mobilephone") + "%'");
        }
        if (page.getSearchParam().containsKey("status") && !StringUtil.isEmpty(page.getSearchParam().get("status"))) {
            sql.append(" and t.status = :status");
        }
    }
    List<User> userList = commonDao.queryForList(sql.toString(), page.getSearchParam(), page, User.class);
    logger.debug("[UserDaoImpl.queryUserList] end...");
    return userList;
}


public Boolean updateUserInfo(Map<String,Object> paramMap){
    return commonDao.update(UPDATEUSERINFO, paramMap) > 0;
}


public List<User> queryCircleUserList(String id,Page page){
    logger.debug("[UserDaoImpl.queryCircleUserList] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("id", id);
    page.setSearchParam(paramMap);
    List<User> userList = commonDao.queryForList(QUERY_CRICLE_USER, page.getSearchParam(), page, User.class);
    logger.debug("[UserDaoImpl.queryCircleUserList] end...");
    return userList;
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
    boolean flag = executeSql(UPDATA_USER_LASTLOGINTIMER, paramMap);
    logger.debug("[UserDaoImpl.updateUserLastLoginTimer] end...");
    return flag;
}


public User queryUserById(String id){
    logger.debug("[UserDaoImpl.queryUserById] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("id", id);
    /**
     * 查询角色实体类
     */
    User user = commonDao.queryForObject(QUERY_USER_BY_ID, paramMap, User.class);
    logger.debug("[UserDaoImpl.queryUserById] end...");
    return user;
}


public int checkUserIsExtists(String mobilePhone){
    // 封装SQL参数
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("mobilePhone", mobilePhone);
    return commonDao.queryForInt(QUERY_CONUT_BY_MOBILEPHONE, paramMap);
}


public boolean executeSql(String sql,Map<String,String> paramMap){
    try {
        int count = commonDao.update(sql, paramMap);
        return count > 0 ? true : false;
    } catch (SPTException e) {
        logger.error("执行sql异常");
        return false;
    }
}


}