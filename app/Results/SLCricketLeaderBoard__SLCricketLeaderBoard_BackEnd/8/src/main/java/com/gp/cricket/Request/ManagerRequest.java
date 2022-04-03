package com.gp.cricket.Request;
import com.gp.cricket.DTO.Manager;
public interface ManagerRequest {

   public Manager getManagerId(Integer managerIdv2);
   public void setManagerId(Manager managerId,Integer managerIdv2);
}