package com.weixin.service;
 import java.util.Map;
import com.weixin.pojo.WeixinRegister;
public interface IWeixinRegisterService {


public Map<String,Object> queryWeixinRegister(String weixinId)
;

public boolean deleteWeixinRegister(String weixinId)
;

public boolean addWeixinRegister(WeixinRegister weixinRegister)
;

public boolean updateWeixinRegister(WeixinRegister weixinRegister)
;

}