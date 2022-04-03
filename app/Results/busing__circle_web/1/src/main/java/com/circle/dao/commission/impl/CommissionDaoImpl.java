package com.circle.dao.commission.impl;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.dao.commission.ICommissionDao;
import com.circle.pojo.commission.CommissionHistory;
import com.circle.pojo.commission.ExtractApp;
import com.circle.pojo.commission.UserCommission;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.StringUtil;
import com.Interface.ICommonDao;
@Repository
public class CommissionDaoImpl implements ICommissionDao{

 private  Logger logger;

 public  String ADD_USERCOMMISSION_SQL;

 public  String INCREASE_USERCOMMISSION_SQL;

 public  String INCREASE_USERCOMMISSION_NOHISTORY_SQL;

 public  String REDUCE_USERCOMMISSION_SQL;

 public  String ISEXITS_USERCOMMISSION_SQL;

 public  String ADD_COMMISSIONHISTORY_SQL;

 public  String QUERY_COMMOSSIONHISTORYLIST_SQL;

 public  String STATISTICS_COMMOSSIONHISTORY_SQL;

 public  String QUERY_USERCOMMOSSION_SQL;

 public  String ADD_EXTRACT_APPLY_SQL;

 public  String UPDATE_EXTRACT_APPLY_STATUS_SQL;

 public  String QUERY_EXTRACT_APPLY_BYID_SQL;

 public  String QUERY_EXTRACT_APPLY_SQL;

@Resource
 private ICommonDao commonDao;


public boolean addCommissionHistory(CommissionHistory history){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", history.getUserId());
    params.put("fromUserId", history.getFromUserId());
    params.put("commission", history.getCommission());
    params.put("commissionType", history.getCommissionType());
    params.put("circleId", history.getCircleId());
    params.put("sourceId", history.getSourceId());
    boolean flag = commonDao.update(ADD_COMMISSIONHISTORY_SQL, params) > 0 ? true : false;
    if (flag) {
        int id = commonDao.getLastId("t_commission_history", "id");
        history.setId(id);
    }
    return flag;
}


public Map<String,Object> queryExtractAppById(int id){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("id", id);
    return commonDao.queryForMap(QUERY_EXTRACT_APPLY_BYID_SQL, paramsMap);
}


public boolean updateExtractAppStatus(int id,int status){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("id", id);
    paramsMap.put("status", status);
    return commonDao.update(UPDATE_EXTRACT_APPLY_STATUS_SQL, paramsMap) > 0 ? true : false;
}


public boolean increaseUserCommission(int userId,double commission){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    params.put("commission", commission);
    return commonDao.update(INCREASE_USERCOMMISSION_NOHISTORY_SQL, params) > 0 ? true : false;
}


public boolean isExitsUserCommission(int userId){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    return commonDao.queryForInt(ISEXITS_USERCOMMISSION_SQL, params) > 0 ? true : false;
}


public List<Map<String,Object>> queryCommossionHistoryList(Page page,int userId){
    StringBuilder sb = new StringBuilder(QUERY_COMMOSSIONHISTORYLIST_SQL);
    if (page.getSearchParam() == null) {
        page.setSearchParam(new HashMap<String, String>());
    }
    page.getSearchParam().put("userId", userId + "");
    if (page != null && page.getSearchParam() != null) {
        if (page.getSearchParam().containsKey("commission_type") && !StringUtil.isEmpty(page.getSearchParam().get("commission_type"))) {
            sb.append(" and h.commission_type in (" + page.getSearchParam().get("commission_type") + " )");
        }
        if (page.getSearchParam().containsKey("startDate") && !StringUtil.isEmpty(page.getSearchParam().get("startDate")) && page.getSearchParam().containsKey("endDate") && !StringUtil.isEmpty(page.getSearchParam().get("endDate"))) {
            page.getSearchParam().put("startTime", page.getSearchParam().get("startDate") + " 00:00:00");
            page.getSearchParam().put("endTime", page.getSearchParam().get("endDate") + " 23:59:59");
            sb.append(" and h.create_time between date_format(:startTime,'%Y-%m-%d %H:%i:%s') and date_format(:endTime,'%Y-%m-%d %H:%i:%s')");
        }
    }
    sb.append(" order by h.create_time desc ");
    return commonDao.queryForList(sb.toString(), page.getSearchParam(), page);
}


public Map<String,Object> queryUserCommossion(int userId){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    return commonDao.queryForMap(QUERY_USERCOMMOSSION_SQL, params);
}


public List<Map<String,Object>> queryExtractApplyList(Page page){
    if (page.getSearchParam() == null) {
        page.setSearchParam(new HashMap<String, String>());
    }
    return commonDao.queryForList(QUERY_EXTRACT_APPLY_SQL, page.getSearchParam(), page);
}


public List<Map<String,Object>> statisticsCommossionHistory(String startDate,String endDate,int userId,String commissionType){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    StringBuilder sb = new StringBuilder(STATISTICS_COMMOSSIONHISTORY_SQL);
    if (!StringUtil.isEmpty(startDate) && !StringUtil.isEmpty(endDate)) {
        startDate += " 00:00:00";
        endDate += " 23:59:59";
        sb.append(" and h.create_time between date_format(:startDate,'%Y-%m-%d %H:%i:%s') and ate_format(:endDate,'%Y-%m-%d %H:%i:%s')");
    }
    if (userId != 0) {
        sb.append(" and user_id=:userId");
        paramsMap.put("userId", userId);
    }
    if (!StringUtil.isEmpty(commissionType)) {
        sb.append(" and commission_type in (" + commissionType + ")");
    }
    if (userId == 0) {
        sb.append(" group by h.user_id ");
    }
    // if(StringUtil.isEmpty(commissionType))
    // {
    // sb.append(" group by h.commission_type ");
    // }
    sb.append(" order by commission desc  limit 10");
    return commonDao.queryForList(sb.toString(), paramsMap);
}


public boolean addUserCommission(UserCommission userCommission){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userCommission.getUserId());
    params.put("commission", userCommission.getCommission());
    params.put("lastHistoryId", userCommission.getLastHistoryId());
    boolean flag = commonDao.update(ADD_USERCOMMISSION_SQL, params) > 0 ? true : false;
    if (flag) {
        int id = commonDao.getLastId("t_user_commission", "id");
        userCommission.setId(id);
    }
    return flag;
}


public boolean addExtractApp(ExtractApp extractApp){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", extractApp.getUserId());
    paramsMap.put("status", extractApp.getStatus());
    paramsMap.put("extractCommission", extractApp.getExtractCommission());
    paramsMap.put("commissionId", extractApp.getCommissionId());
    paramsMap.put("alipayName", extractApp.getAlipayName());
    paramsMap.put("alipayAccount", extractApp.getAlipayAccount());
    return commonDao.update(ADD_EXTRACT_APPLY_SQL, paramsMap) > 0 ? true : false;
}


public boolean reduceUserCommission(int userId,double commission){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("userId", userId);
    params.put("commission", commission);
    return commonDao.update(REDUCE_USERCOMMISSION_SQL, params) > 0 ? true : false;
}


}