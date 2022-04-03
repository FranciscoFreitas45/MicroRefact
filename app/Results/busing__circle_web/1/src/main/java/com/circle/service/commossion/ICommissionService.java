package com.circle.service.commossion;
 import java.util.List;
import java.util.Map;
import com.circle.pojo.commission.CommissionHistory;
import com.circle.pojo.commission.ExtractApp;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
public interface ICommissionService {


public boolean dealExtractApp(int id,int status)
;

public boolean increaseUserCommission(CommissionHistory commissionHistory)
;

public List<Map<String,Object>> queryCommossionHistoryList(Page page,int userId)
;

public Map<String,Object> queryUserCommossion(int userId)
;

public List<Map<String,Object>> queryExtractApplyList(Page page)
;

public List<Map<String,Object>> statisticsCommossionHistory(String startDate,String endDate,int userId,String commissionType)
;

public boolean addExtractApp(ExtractApp extractApp)
;

public boolean reduceUserCommission(int userId,double commission)
;

}