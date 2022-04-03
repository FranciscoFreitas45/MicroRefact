package org.gliderwiki.Interface;
public interface CommonService {

   public WeGroupInfo getWeGroupInfo(Integer groupIdx);
   public List<WeUser> getWeUserList(Integer loginUserIdx,String userNick,String userEmail,String userName);
   public WeUser getUserInfo(Integer weUserIdx);
   public WeProfile getUserProfileInfo(Integer weUserIdx);
}