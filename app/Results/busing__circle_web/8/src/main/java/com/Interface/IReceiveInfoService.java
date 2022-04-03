package com.Interface;
public interface IReceiveInfoService {

   public boolean isExistsReceiveInfo(int userId);
   public boolean saveDefaultReceiveInfo(User user);
   public List<Map<String,Object>> queryReceiveInfo(int userId);
   public Map<String,Object> queryPayAndShipInfo(int userId);
}