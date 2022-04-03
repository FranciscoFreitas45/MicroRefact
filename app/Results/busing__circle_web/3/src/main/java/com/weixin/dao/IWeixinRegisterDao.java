package com.weixin.dao;
 import java.util.Map;
import com.weixin.pojo.WeixinRegister;
public interface IWeixinRegisterDao {


public Map<String,Object> queryWeixinRegister(String weixinId)
;

public boolean deleteWeixinRegister(String weixinId)
;

public boolean addWeixinRegister(WeixinRegister weixinRegister)
;

public boolean updateWeixinRegister(WeixinRegister weixinRegister)
;

}