package com.circle.dao.circle.impl;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.constant.CircleTable;
import com.circle.constant.SystemDict;
import com.circle.dao.circle.ICircleDao;
import com.circle.pojo.circle.Circle;
import com.circle.pojo.circle.CircleMember;
import com.circle.pojo.user.User;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.StringUtil;
import com.Interface.ICommonDao;
import com.DTO.Page;
@Repository
public class CircleDaoImpl implements ICircleDao{

 private  Logger logger;

 public  String ADD_CIRCLE;

 private  String ADD_CIRCLE_MEMBER;

 public  String UPDATA_CIRCLE;

 public  String UPDATA_CIRCLE_MAP;

 public  String UPDATE_CIRCLE_STATUS;

 public  String QUERY_CIRCLE_BY_ID;

 public  String QUERY_CIRCLE_LIST;

 public  String QUERY_JOIN_CIRCLE_LIST;

 public  String QUERY_JOIN_CIRCLE_BY_ID;

 public  String QUERY_CIRCLE_LISTBYPOINT;

 public  String QUERY_CIRCLE_HISTORY_LIST;

 public  String QUERY_CIRCLE_HISTORY_BY_ID;

 public  String UPDATE_CIRCLE_HISTORY_STATUS;

 public  String ADD_USER_TO_CIRCLE;

 public  String ADD_USER_TO_CIRCLE_WITH_EXAMINE;

 public  String QUERY_CIRCLE_BY_CIRCLEID;

 public  String QUERY_INIT_MAP_CIRCLE;

 public  String QUERY_CIRCLEMEMBER_BY_CIRCLEID_AND_USERID;

 public  String QUERY_CIRCLEMEMBERAUDIT_BY_CIRCLEID_AND_USERID;

 private  String QUERY_CIRCLES_BY_USERID;

 public  String QUERY_CIRLCE_BUYNUM;

 private  String QUERY_CIRCLE_USER_BY_CIRCLE_ID;

 private  String MEMBER_EXIT_CIRCLE;

 private  String QUERY_USERS_BY_CIRCLE_ID;

 private  String SAVE_CIRCLE_INFO;

 private  String SAVE_CIRCLE_PUBLISH_INFO;

@Resource
 private ICommonDao commonDao;


@Override
public Integer saveCircleInfo(Map<String,Object> paramMap){
    return commonDao.update(SAVE_CIRCLE_INFO, paramMap);
}


@Override
public int queryCircleBuyNum(String circleId){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("circleId", circleId);
    return commonDao.queryForInt(QUERY_CIRLCE_BUYNUM, paramMap);
}


public Circle queryJoinCircleById(String id,int userId){
    logger.debug("[CircleDaoImpl.queryCircleById] start...");
    /**
     * 查询条件 map
     */
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("userId", userId);
    paramMap.put("circleId", id);
    /**
     * 查询角色实体类
     */
    Circle circle = commonDao.queryForObject(QUERY_JOIN_CIRCLE_BY_ID, paramMap, Circle.class);
    logger.debug("[CircleDaoImpl.queryCircleById] end...");
    return circle;
}


public List<Circle> queryMyCircleList(int userId){
    logger.debug("[CircleDaoImpl.queryMyCircleList] start...");
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    List<Circle> circleList = commonDao.queryForList(QUERY_JOIN_CIRCLE_LIST, paramsMap, Circle.class);
    logger.debug("[CircleDaoImpl.queryCircleList] end...");
    return circleList;
}


@Override
public Circle queryCircleAndUserByCircleId(Map<String,Object> paramMap){
    return commonDao.queryForObject(QUERY_CIRCLE_USER_BY_CIRCLE_ID, paramMap, Circle.class);
}


@Override
public List<User> queryUsersByCircleId(Map<String,Object> paramMap){
    return commonDao.queryForList(QUERY_USERS_BY_CIRCLE_ID, paramMap, User.class);
}


public boolean AddUserToCircle(String userId,String circleId,String type){
    logger.debug("[CircleDaoImpl.AddUserToCircle] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("userId", userId);
    paramMap.put("circleId", circleId);
    paramMap.put("type", type);
    int count = commonDao.update(ADD_USER_TO_CIRCLE, paramMap);
    logger.debug("[CircleDaoImpl.AddUserToCircle] end...");
    return count > 0 ? true : false;
}


public boolean AddUserToCircleAudit(String userId,String circleId,String type,String status){
    logger.debug("[CircleDaoImpl.AddUserToCircleAudit] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("userId", userId);
    paramMap.put("circleId", circleId);
    paramMap.put("type", type);
    paramMap.put("status", status);
    int count = commonDao.update(ADD_USER_TO_CIRCLE_WITH_EXAMINE, paramMap);
    logger.debug("[CircleDaoImpl.AddUserToCircleAudit] end...");
    return count > 0 ? true : false;
}


public int addCicle(Circle circle){
    logger.debug("[CircleDaoImpl.addCicle] start...");
    /**
     * 增加圈子信息返回结果参数默认为 -1 （失败）
     */
    int resultNum = -1;
    /**
     * 增加时各字段对应值 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("name", circle.getName());
    paramMap.put("description", circle.getDescription());
    paramMap.put("headPath", circle.getHeadPath());
    paramMap.put("joinType", circle.getJoinType());
    paramMap.put("issueAddress", circle.getIssueAddress());
    paramMap.put("createUserid", circle.getCreateUser());
    paramMap.put("issueTime", circle.getIssueTime());
    paramMap.put("endTime", circle.getEndTime());
    paramMap.put("address", circle.getAddress());
    paramMap.put("notice", circle.getNotice());
    paramMap.put("province", circle.getProvince());
    paramMap.put("city", circle.getCity());
    paramMap.put("longitude", circle.getLongitude());
    paramMap.put("latitude", circle.getLatitude());
    paramMap.put("postWeek", circle.getPostWeek() + "");
    paramMap.put("postAmPm", circle.getPostAmPm() + "");
    resultNum = commonDao.update(ADD_CIRCLE, paramMap);
    int id = commonDao.getLastId(CircleTable.CIRCLE.getTableName(), "id");
    circle.setId(id + "");
    logger.debug("[CircleDaoImpl.addCicle] end...");
    return resultNum;
}


public Circle queryCircleByCircleId(String circleId){
    logger.debug("[CircleDaoImpl.queryCircleByCircleId] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("circleId", circleId);
    /**
     * 查询角色实体类
     */
    Circle circle = commonDao.queryForObject(QUERY_CIRCLE_BY_CIRCLEID, paramMap, Circle.class);
    logger.debug("[CircleDaoImpl.queryCircleByCircleId] end...");
    return circle;
}


@Override
public List<Circle> queryCircleListCircleId(String circlrIds){
    StringBuilder sb = new StringBuilder(QUERY_CIRCLE_LIST);
    sb.append(" and t1.id in (" + circlrIds + ")");
    return commonDao.queryForList(sb.toString(), Circle.class);
}


public Integer memberExitCircle(Map<String,Object> paramMap){
    return commonDao.update(MEMBER_EXIT_CIRCLE, paramMap);
}


public List<Circle> queryCircleListByPoint(double minX,double minY,double maxX,double maxY){
    logger.debug("[CircleDaoImpl.queryCircleListByPoint] start...");
    StringBuilder sql = new StringBuilder(QUERY_CIRCLE_LISTBYPOINT);
    sql.append(" and t1.longitude BETWEEN " + minX + " and " + maxX + " and t1.latitude BETWEEN " + minY + " and " + maxY);
    List<Circle> circleList = commonDao.queryForList(sql.toString(), Circle.class);
    logger.debug("[CircleDaoImpl.queryCircleListByPoint] end...");
    return circleList;
}


public int updateCircleHistoryStatus(String id,String status,String auditMsg){
    logger.debug("[CircleDaoImpl.updateCircleHistoryStatus] start...");
    /**
     * 修改圈子信息返回结果参数默认为 -1 （失败）
     */
    int resultNum = -1;
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("id", id);
    paramMap.put("status", status);
    paramMap.put("auditMsg", auditMsg);
    resultNum = commonDao.update(UPDATE_CIRCLE_HISTORY_STATUS, paramMap);
    logger.debug("[CircleDaoImpl.updateCircleHistoryStatus] end...");
    return resultNum;
}


public List<Circle> queryCirclesByUserId(Object userId){
    logger.debug("[CircleDaoImpl.queryCircleByUserId] start...");
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("userId", userId);
    List<Circle> list = commonDao.queryForList(QUERY_CIRCLES_BY_USERID, paramMap, Circle.class);
    logger.debug("[CircleDaoImpl.queryCircleByUserId] end...");
    return list;
}


@Override
public List<Circle> queryInitMapCircle(){
    return commonDao.queryForList(QUERY_INIT_MAP_CIRCLE, Circle.class);
}


public List<Map<String,Object>> queryCircleHistoryList(Page page){
    logger.debug("[CircleDaoImpl.queryCircleHistoryList] start...");
    StringBuilder sql = new StringBuilder(QUERY_CIRCLE_HISTORY_LIST);
    if (page != null && page.getSearchParam() != null) {
        if (page.getSearchParam().containsKey("name") && !StringUtil.isEmpty(page.getSearchParam().get("name"))) {
            sql.append(" and t2.name LIKE '%" + page.getSearchParam().get("name") + "%'");
        }
        if (page.getSearchParam().containsKey("username") && !StringUtil.isEmpty(page.getSearchParam().get("username"))) {
            sql.append(" and t3.name LIKE '%" + page.getSearchParam().get("username") + "%'");
        }
        if (page.getSearchParam().containsKey("beginTime") && !StringUtil.isEmpty(page.getSearchParam().get("beginTime"))) {
            sql.append(" and t1.create_time >= :beginTime");
        }
        if (page.getSearchParam().containsKey("endTime") && !StringUtil.isEmpty(page.getSearchParam().get("endTime"))) {
            sql.append(" and t1.create_time <= :endTime");
        }
        if (page.getSearchParam().containsKey("status") && !StringUtil.isEmpty(page.getSearchParam().get("status"))) {
            sql.append(" and t1.status = :status");
        }
    }
    List<Map<String, Object>> circleHistoryList = commonDao.queryForList(sql.toString(), page.getSearchParam(), page);
    logger.debug("[CircleDaoImpl.queryCircleHistoryList] end...");
    return circleHistoryList;
}


public int updateCircleMap(Circle circle){
    logger.debug("[CircleDaoImpl.updateCircleMap] start...");
    /**
     * 修改角色返回结果参数默认为 -1 （失败）
     */
    int resultNum = -1;
    /**
     * 更新时各字段对应值 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("id", circle.getId());
    paramMap.put("longitude", circle.getLongitude());
    paramMap.put("latitude", circle.getLatitude());
    resultNum = commonDao.update(UPDATA_CIRCLE_MAP, paramMap);
    logger.debug("[CircleDaoImpl.updateCircleMap] end...");
    return resultNum;
}


public int queryCircleMemberByCircleIdAndUserId(String circleId,String userId){
    logger.debug("[CircleDaoImpl.queryCircleMemberByCircleIdAndUserId] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("circleId", circleId);
    paramMap.put("userId", userId);
    /**
     * 查询角色实体类
     */
    int num = commonDao.queryForInt(QUERY_CIRCLEMEMBER_BY_CIRCLEID_AND_USERID, paramMap);
    logger.debug("[CircleDaoImpl.queryCircleMemberByCircleIdAndUserId] end...");
    return num;
}


public Integer addCircleMember(CircleMember member){
    logger.debug("[CircleDaoImpl.addCicle] start...");
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("circleId", member.getCircleId());
    paramMap.put("userId", member.getUserId());
    paramMap.put("identityType", member.getIdentityType());
    int res = commonDao.update(ADD_CIRCLE_MEMBER, paramMap);
    logger.debug("[CircleDaoImpl.addCicle] end...");
    return res;
}


public int queryCircleMemberAuditByCircleIdAndUserId(String circleId,String userId){
    logger.debug("[CircleDaoImpl.queryCircleMemberAuditByCircleIdAndUserId] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("circleId", circleId);
    paramMap.put("userId", userId);
    /**
     * 查询角色实体类
     */
    int num = commonDao.queryForInt(QUERY_CIRCLEMEMBERAUDIT_BY_CIRCLEID_AND_USERID, paramMap);
    logger.debug("[CircleDaoImpl.queryCircleMemberAuditByCircleIdAndUserId] end...");
    return num;
}


@Override
public Integer saveCirclePublishInfo(Map<String,Object> paramMap){
    return commonDao.update(SAVE_CIRCLE_PUBLISH_INFO, paramMap);
}


public Circle queryMyCircleById(String id,int userId){
    logger.debug("[CircleDaoImpl.queryCircleById] start...");
    /**
     * 查询条件 map
     */
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("id", id);
    paramMap.put("userId", userId);
    /**
     * 查询角色实体类
     */
    Circle circle = commonDao.queryForObject(QUERY_CIRCLE_BY_ID, paramMap, Circle.class);
    logger.debug("[CircleDaoImpl.queryCircleById] end...");
    return circle;
}


public int updateCircle(Circle circle){
    logger.debug("[CircleDaoImpl.updateCircle] start...");
    /**
     * 修改角色返回结果参数默认为 -1 （失败）
     */
    int resultNum = -1;
    /**
     * 更新时各字段对应值 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("id", circle.getId());
    paramMap.put("name", circle.getName());
    paramMap.put("description", circle.getDescription());
    paramMap.put("headPath", circle.getHeadPath());
    paramMap.put("joinType", circle.getJoinType());
    paramMap.put("issueAddress", circle.getIssueAddress());
    paramMap.put("createUserid", circle.getCreateUser());
    paramMap.put("address", circle.getAddress());
    paramMap.put("province", circle.getProvince());
    paramMap.put("city", circle.getCity());
    paramMap.put("longitude", circle.getLongitude());
    paramMap.put("latitude", circle.getLatitude());
    resultNum = commonDao.update(UPDATA_CIRCLE, paramMap);
    logger.debug("[CircleDaoImpl.updateCircle] end...");
    return resultNum;
}


public Map<String,Object> queryCircleHistoryById(String id){
    logger.debug("[CircleDaoImpl.queryCircleHistoryById] start...");
    /**
     * 查询条件 map
     */
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("id", id);
    /**
     * 查询角色实体类
     */
    Map<String, Object> circleHistory = commonDao.queryForMap(QUERY_CIRCLE_HISTORY_BY_ID, paramMap);
    logger.debug("[CircleDaoImpl.queryCircleHistoryById] end...");
    return circleHistory;
}


}