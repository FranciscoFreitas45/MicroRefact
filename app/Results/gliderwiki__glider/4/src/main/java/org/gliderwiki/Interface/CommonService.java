package org.gliderwiki.Interface;
public interface CommonService {

   public WeUser getUserInfo(Integer weUserIdx);
   public WeProfile getUserProfileInfo(Integer weUserIdx);
   public int insertWeFile(WeFile weFile);
   public void updateAllRead(int userIdx);
}