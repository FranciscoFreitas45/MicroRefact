package com.fosun.fc.projects.creepers.Request;
import com.fosun.fc.projects.creepers.DTO.TCreepersCcDetail;
public interface TCreepersCcDetailRequest {

   public void setTCreepersCcDetails(List<TCreepersCcDetail> TCreepersCcDetails,Long id);
   public TCreepersCcDetail removeTCreepersCcDetail(TCreepersCcDetail TCreepersCcDetail,Long id);
   public List<TCreepersCcDetail> getTCreepersCcDetails(Long id);
   public TCreepersCcDetail addTCreepersCcDetail(TCreepersCcDetail TCreepersCcDetail,Long id);
}