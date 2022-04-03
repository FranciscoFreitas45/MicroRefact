package cn.gson.oasys.Interface;
public interface StatusDao {

   public SystemStatusList findByStatusModelAndStatusName(String statusModel,String statusName);
   public List<SystemStatusList> findByStatusModel(String statusModel);
}