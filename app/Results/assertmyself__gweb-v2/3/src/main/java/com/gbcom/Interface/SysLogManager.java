package com.gbcom.Interface;
public interface SysLogManager {

   public void deleteLog(Long userId);
   public List<SysLog> getTodayLoginCount(String time);
}