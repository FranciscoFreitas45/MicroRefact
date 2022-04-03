package cn.gson.oasys.Interface;
public interface StatusDao {

   public List<SystemStatusList> findByStatusModel(String statusModel);
}