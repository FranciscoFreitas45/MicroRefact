package com.circle.service.commossion.impl;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.circle.constant.ConstantBusiKeys;
import com.circle.constant.SystemDict;
import com.circle.dao.commission.ICommissionDao;
import com.circle.pojo.commission.CommissionHistory;
import com.circle.pojo.commission.ExtractApp;
import com.circle.pojo.commission.UserCommission;
import com.circle.service.commossion.ICommissionService;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.CommonUtil;
import com.xwtec.xwserver.util.ProUtil;
@Transactional
@Service
public class CommissionServiceImpl implements ICommissionService{

 public  Logger logger;

@Resource
 private  ICommissionDao commissionDao;


public boolean dealExtractApp(int id,int status){
    boolean flag = true;
    try {
        // 更新申请状态
        flag = commissionDao.updateExtractAppStatus(id, status);
        if (flag) {
            // 提现成功
            if (status == 2) {
            // flag=commissionDao.updateExtractAppStatus(id, status);
            } else // 提现失败 退回钱
            {
                // 查询提现申请
                Map<String, Object> extractApp = commissionDao.queryExtractAppById(id);
                double extractCommission = Double.parseDouble(extractApp.get("extract_commission").toString());
                int userId = Integer.parseInt(extractApp.get("user_id").toString());
                // 增加佣金（还回对应的金额）
                flag = commissionDao.increaseUserCommission(userId, extractCommission);
            }
        }
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        flag = false;
    }
    return flag;
}


public boolean increaseUserCommission(CommissionHistory commissionHistory){
    try {
        boolean isExists = commissionDao.isExitsUserCommission(commissionHistory.getUserId());
        if (!isExists) {
            UserCommission userCommission = new UserCommission();
            userCommission.setCommission(0);
            userCommission.setUserId(commissionHistory.getUserId());
            userCommission.setLastHistoryId(0);
            commissionDao.addUserCommission(userCommission);
        }
        commissionDao.addCommissionHistory(commissionHistory);
        commissionDao.increaseUserCommission(commissionHistory);
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return false;
    }
    return true;
}


public List<Map<String,Object>> queryCommossionHistoryList(Page page,int userId){
    List<Map<String, Object>> commissionList = commissionDao.queryCommossionHistoryList(page, userId);
    for (Map<String, Object> map : commissionList) {
        map.put("commission_type_str", SystemDict.getDict(SystemDict.COMMISSION_TYPE, map.get("commission_type").toString()).getType_name());
    }
    return commissionList;
}


public Map<String,Object> queryUserCommossion(int userId){
    return commissionDao.queryUserCommossion(userId);
}


public List<Map<String,Object>> queryExtractApplyList(Page page){
    return commissionDao.queryExtractApplyList(page);
}


public List<Map<String,Object>> statisticsCommossionHistory(String startDate,String endDate,int userId,String commissionType){
    List<Map<String, Object>> commissionLIst = commissionDao.statisticsCommossionHistory(startDate, endDate, userId, commissionType);
    for (Map<String, Object> map : commissionLIst) {
        map.put("head_image", ProUtil.get(ConstantBusiKeys.PropertiesKey.DOMAIN) + map.get("head_image"));
    }
    return commissionLIst;
}


public boolean addExtractApp(ExtractApp extractApp){
    boolean flag = false;
    try {
        flag = commissionDao.reduceUserCommission(extractApp.getUserId(), extractApp.getExtractCommission());
        if (flag) {
            flag = commissionDao.addExtractApp(extractApp);
        }
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return flag;
}


public boolean reduceUserCommission(int userId,double commission){
    try {
        boolean isExists = commissionDao.isExitsUserCommission(userId);
        if (isExists) {
            commissionDao.reduceUserCommission(userId, commission);
        }
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    }
    return true;
}


}