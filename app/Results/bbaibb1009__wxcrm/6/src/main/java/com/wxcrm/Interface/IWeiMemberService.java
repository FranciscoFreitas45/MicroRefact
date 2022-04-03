package com.wxcrm.Interface;
public interface IWeiMemberService {

   public Page queryWeiMember(LzWeiMember member);
   public void delWeiMember(String[] wmbIds);
}