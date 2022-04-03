package com.wxcrm.weixin;
 import com.wxcrm.util.Page;
import com.wxcrm.weixin.pojo.LzWeiMenu;
public interface IWeixinMenuService {


public LzWeiMenu getMenuById(Integer wmuId)
;

public int addMenu(LzWeiMenu menu,String accessToken)
;

public Page queryMenu(LzWeiMenu menu)
;

public LzWeiMenu getMenuByAppId(String appId)
;

}