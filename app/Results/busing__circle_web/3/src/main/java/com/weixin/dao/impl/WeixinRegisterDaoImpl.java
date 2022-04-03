package com.weixin.dao.impl;
 import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.weixin.dao.IWeixinRegisterDao;
import com.weixin.pojo.WeixinRegister;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.Interface.ICommonDao;
@Repository
public class WeixinRegisterDaoImpl implements IWeixinRegisterDao{

 private  Logger logger;

 public  String ADD_SQL;

 public  String UPDATE_SQL;

 public  String QUERY_SQL;

 public  String DELETE_SQL;

@Resource
 private ICommonDao commonDao;


public Map<String,Object> queryWeixinRegister(String weixinId){
    try {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("weixinId", weixinId);
        return commonDao.queryForMap(QUERY_SQL, params);
    } catch (SPTException e) {
        e.printStackTrace();
    }
    return null;
}


public boolean deleteWeixinRegister(String weixinId){
    try {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("weixinId", weixinId);
        return commonDao.update(DELETE_SQL, params) > 0 ? true : false;
    } catch (SPTException e) {
        e.printStackTrace();
    }
    return false;
}


public boolean addWeixinRegister(WeixinRegister weixinRegister){
    try {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("weixinId", weixinRegister.getWeixin_id());
        params.put("mobilePhone", weixinRegister.getMobile_phone());
        params.put("step", weixinRegister.getStep());
        return commonDao.update(ADD_SQL, params) > 0 ? true : false;
    } catch (SPTException e) {
        e.printStackTrace();
    }
    return false;
}


public boolean updateWeixinRegister(WeixinRegister weixinRegister){
    try {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("weixinId", weixinRegister.getWeixin_id());
        params.put("mobilePhone", weixinRegister.getMobile_phone());
        params.put("step", weixinRegister.getStep());
        return commonDao.update(UPDATE_SQL, params) > 0 ? true : false;
    } catch (SPTException e) {
        e.printStackTrace();
    }
    return false;
}


}