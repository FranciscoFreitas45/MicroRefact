package com.circle.dao.commission;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.commission.CommissionHistory;
import com.circle.pojo.commission.ExtractApp;
import com.circle.pojo.commission.UserCommission;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface ICommissionDao {


public boolean addCommissionHistory(CommissionHistory history)
;

public Map<String,Object> queryExtractAppById(int id)
;

public boolean updateExtractAppStatus(int id,int status)
;

public boolean increaseUserCommission(int userId,double commission)
;

public boolean isExitsUserCommission(int userId)
;

public List<Map<String,Object>> queryCommossionHistoryList(Page page,int userId)
;

public Map<String,Object> queryUserCommossion(int userId)
;

public List<Map<String,Object>> queryExtractApplyList(Page page)
;

public List<Map<String,Object>> statisticsCommossionHistory(String startDate,String endDate,int userId,String commissionType)
;

public boolean addUserCommission(UserCommission userCommission)
;

public boolean addExtractApp(ExtractApp extractApp)
;

public boolean reduceUserCommission(int userId,double commission)
;

}