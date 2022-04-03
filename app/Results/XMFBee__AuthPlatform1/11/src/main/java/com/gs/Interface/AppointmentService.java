package com.gs.Interface;
public interface AppointmentService {

   public List<Appointment> queryByPhone(String userPhone);
   public void updateUserIds(String userId,String appIds);
}