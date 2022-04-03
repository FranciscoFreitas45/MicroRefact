package com.weixin.service.impl;
 import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.weixin.dao.IWeixinRegisterDao;
import com.weixin.pojo.WeixinRegister;
import com.weixin.service.IWeixinRegisterService;
@Service
public class WeixinRegisterServiceImpl implements IWeixinRegisterService{

@Resource
 private  IWeixinRegisterDao weixinRegisterDao;


@Override
public Map<String,Object> queryWeixinRegister(String weixinId){
    return weixinRegisterDao.queryWeixinRegister(weixinId);
}


@Override
public boolean deleteWeixinRegister(String weixinId){
    return weixinRegisterDao.deleteWeixinRegister(weixinId);
}


@Override
public boolean addWeixinRegister(WeixinRegister weixinRegister){
    return weixinRegisterDao.addWeixinRegister(weixinRegister);
}


@Override
public boolean updateWeixinRegister(WeixinRegister weixinRegister){
    return weixinRegisterDao.updateWeixinRegister(weixinRegister);
}


}