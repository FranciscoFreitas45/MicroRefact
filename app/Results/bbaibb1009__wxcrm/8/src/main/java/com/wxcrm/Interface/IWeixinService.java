package com.wxcrm.Interface;
public interface IWeixinService {

   public LzWeiEnter getWeiEnterByAppId(String appId);
   public String getCurrentAccessTokenStr(Integer wecId);
   public LzWeiEnter getWeiEnterById(Integer weiEnterId);
}