package com.ushahidi.swiftriver.core.Interface;
public interface CrowdmapIDClient {

   public boolean isRegistered(String email);
   public String register(String email,String password);
}