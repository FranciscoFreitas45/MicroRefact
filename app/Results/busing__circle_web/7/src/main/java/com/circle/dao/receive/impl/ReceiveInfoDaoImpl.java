package com.circle.dao.receive.impl;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.dao.receive.IReceiveInfoDao;
import com.circle.pojo.payship.PayAndShip;
import com.circle.pojo.receive.ReceiveInfo;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.CommonUtil;
import com.Interface.ICommonDao;
@Repository
public class ReceiveInfoDaoImpl implements IReceiveInfoDao{

 private  Logger logger;

@Resource
 private ICommonDao commonDao;

 public  String ADD_RECEIVE_INFO;

 public  String UPDATE_RECEIVE_INFO;

 public  String DELETE_RECEIVE_INFO;

 public  String CHECK_RECEIVE_INFO;

 public  String REMOVE_DEFALUT_RECEIVE_INFO;

 public  String SET_DEFALUT_RECEIVE_INFO;

 public  String ADD_PAY_SHIP_TYPE;

 public  String UPDATE_PAY_SHIP_TYPE;

 public  String CHECK_PAY_SHIP_TYPE;

 public  String QUERY_RECEIVE_INFO;

 public  String QUERY_PAY_SHIP_TYPE_INFO;


public boolean addReceiveInfo(ReceiveInfo receiveInfo){
    Map<String, Object> paramsMap = getReceiveParams(receiveInfo);
    return commonDao.update(ADD_RECEIVE_INFO, paramsMap) > 0 ? true : false;
}


public boolean updateReceiveInfo(ReceiveInfo receiveInfo){
    Map<String, Object> paramsMap = getReceiveParams(receiveInfo);
    return commonDao.update(UPDATE_RECEIVE_INFO, paramsMap) > 0 ? true : false;
}


public boolean deleteReceiveInfo(int id){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("id", id);
    return commonDao.update(DELETE_RECEIVE_INFO, paramsMap) > 0 ? true : false;
}


public Map<String,Object> getPayAndShipInfoParams(PayAndShip payAndShip){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("payType", payAndShip.getPayType());
    paramsMap.put("shipType", payAndShip.getShipType());
    paramsMap.put("userId", payAndShip.getUserId());
    return paramsMap;
}


public List<Map<String,Object>> queryReceiveInfo(int userId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    return commonDao.queryForList(QUERY_RECEIVE_INFO, paramsMap);
}


public Map<String,Object> getReceiveParams(ReceiveInfo receiveInfo){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("receiveAddress", receiveInfo.getReceiveAddress());
    paramsMap.put("id", receiveInfo.getId());
    paramsMap.put("receiveName", receiveInfo.getReceiveName());
    paramsMap.put("receivePhone", receiveInfo.getReceivePhone());
    paramsMap.put("userId", receiveInfo.getUserId());
    paramsMap.put("isDefault", receiveInfo.getIsDefault());
    return paramsMap;
}


public boolean defaultReceiveInfo(int id,int userId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    paramsMap.put("id", id);
    commonDao.update(REMOVE_DEFALUT_RECEIVE_INFO, paramsMap);
    return commonDao.update(SET_DEFALUT_RECEIVE_INFO, paramsMap) > 0 ? true : false;
}


public boolean isExistsPayAndShipInfo(int userId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    return commonDao.queryForInt(CHECK_PAY_SHIP_TYPE, paramsMap) > 0 ? true : false;
}


public Map<String,Object> queryPayAndShipInfo(int userId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    List<Map<String, Object>> data = commonDao.queryForList(QUERY_PAY_SHIP_TYPE_INFO, paramsMap);
    if (data != null && data.size() > 0) {
        return data.get(0);
    } else {
        return null;
    }
}


public boolean addPayAndShipInfo(PayAndShip payAndShip){
    Map<String, Object> paramsMap = getPayAndShipInfoParams(payAndShip);
    return commonDao.update(ADD_PAY_SHIP_TYPE, paramsMap) > 0 ? true : false;
}


public boolean updatePayAndShipInfo(PayAndShip payAndShip){
    Map<String, Object> paramsMap = getPayAndShipInfoParams(payAndShip);
    return commonDao.update(UPDATE_PAY_SHIP_TYPE, paramsMap) > 0 ? true : false;
}


public boolean isExistsReceiveInfo(int userId){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("userId", userId);
    return commonDao.queryForInt(CHECK_RECEIVE_INFO, paramsMap) > 0 ? true : false;
}


}