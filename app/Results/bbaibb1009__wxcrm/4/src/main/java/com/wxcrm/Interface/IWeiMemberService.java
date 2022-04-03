package com.wxcrm.Interface;
public interface IWeiMemberService {

   public boolean checkOpenIdExsit(String openId,Integer wecId);
   public void addWeiMember(LzWeiMember member);
}