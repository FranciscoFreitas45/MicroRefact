package com.circle.service.receive.impl;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.circle.constant.SystemDict;
import com.circle.dao.receive.IReceiveInfoDao;
import com.circle.pojo.dict.DictBean;
import com.circle.pojo.payship.PayAndShip;
import com.circle.pojo.receive.ReceiveInfo;
import com.circle.pojo.user.User;
import com.circle.service.receive.IReceiveInfoService;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.CommonUtil;
import com.DTO.User;
import com.DTO.DictBean;
@Service
@Transactional
public class ReceiveInfoServiceImpl implements IReceiveInfoService{

 private  Logger logger;

@Resource
 private IReceiveInfoDao receiveInfoDao;


public boolean deleteReceiveInfo(int id){
    try {
        boolean flag = receiveInfoDao.deleteReceiveInfo(id);
        return flag;
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return false;
    }
}


public List<Map<String,Object>> queryReceiveInfo(int userId){
    try {
        return receiveInfoDao.queryReceiveInfo(userId);
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return null;
    }
}


public boolean defaultReceiveInfo(int id,int userId){
    try {
        boolean flag = receiveInfoDao.defaultReceiveInfo(id, userId);
        return flag;
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return false;
    }
}


@Override
public boolean saveDefaultReceiveInfo(User user){
    ReceiveInfo receiveInfo = new ReceiveInfo();
    receiveInfo.setIsDefault(1);
    receiveInfo.setReceiveName(user.getName());
    receiveInfo.setReceivePhone(user.getMobilePhone());
    receiveInfo.setUserId(user.getId());
    return saveReceiveInfo(receiveInfo);
}


public boolean savePayAndShipInfo(PayAndShip payAndShip){
    try {
        boolean flag = false;
        boolean isExists = receiveInfoDao.isExistsPayAndShipInfo(payAndShip.getUserId());
        if (isExists) {
            flag = receiveInfoDao.updatePayAndShipInfo(payAndShip);
        } else {
            flag = receiveInfoDao.addPayAndShipInfo(payAndShip);
        }
        return flag;
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return false;
    }
}


public Map<String,Object> queryPayAndShipInfo(int userId){
    try {
        Map<String, Object> pay_ship_type = receiveInfoDao.queryPayAndShipInfo(userId);
        if (pay_ship_type != null) {
            DictBean pay_dict = SystemDict.getDict(SystemDict.PAY_TYPE, pay_ship_type.get("pay_type").toString());
            DictBean ship_dict = SystemDict.getDict(SystemDict.SHIPPING_TYPE, pay_ship_type.get("ship_type").toString());
            pay_ship_type.put("pay_info", pay_dict.getType_name());
            pay_ship_type.put("pay_info_remark", pay_dict.getRemark());
            pay_ship_type.put("ship_info", ship_dict.getType_name());
            pay_ship_type.put("ship_info_remark", ship_dict.getRemark());
        }
        return pay_ship_type;
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return null;
    }
}


public boolean saveReceiveInfo(ReceiveInfo receiveInfo){
    try {
        boolean flag = false;
        if (receiveInfo.getId() == 0) {
            if (receiveInfoDao.isExistsReceiveInfo(receiveInfo.getUserId())) {
                receiveInfo.setIsDefault(0);
            } else {
                receiveInfo.setIsDefault(1);
            }
            flag = receiveInfoDao.addReceiveInfo(receiveInfo);
        } else {
            flag = receiveInfoDao.updateReceiveInfo(receiveInfo);
        }
        return flag;
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return false;
    }
}


@Override
public boolean isExistsReceiveInfo(int userId){
    try {
        return receiveInfoDao.isExistsReceiveInfo(userId);
    } catch (SPTException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        return false;
    }
}


}