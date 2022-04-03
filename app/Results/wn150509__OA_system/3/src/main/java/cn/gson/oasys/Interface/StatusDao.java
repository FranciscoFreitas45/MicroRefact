package cn.gson.oasys.Interface;
public interface StatusDao {

   public String findname(Long id);
   public List<SystemStatusList> findByStatusModel(String statusModel);
   public Object findOne(Object Object);
}