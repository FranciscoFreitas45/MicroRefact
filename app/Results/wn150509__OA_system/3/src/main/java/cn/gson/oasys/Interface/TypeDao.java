package cn.gson.oasys.Interface;
public interface TypeDao {

   public String findname(Long id);
   public List<SystemTypeList> findByTypeModel(String typeModel);
   public Object findOne(Object Object);
}