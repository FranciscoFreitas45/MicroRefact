package com.wxcrm.Interface;
public interface IWeixinService {

   public LzWeiEnter getWeiEnterByAppId(String appId);
   public LzWeiJsapiTicket getCurrentTikcet(Integer wecId);
}