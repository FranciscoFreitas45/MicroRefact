package com.gs.Interface;
public interface CheckinService {

   public List<Checkin> queryByPhone(String userPhone);
   public void updateUserIds(String userId,String chIds);
}