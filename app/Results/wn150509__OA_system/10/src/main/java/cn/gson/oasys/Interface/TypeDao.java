package cn.gson.oasys.Interface;
public interface TypeDao {

   public Object findOne(Object Object);
   public List<SystemTypeList> findByTypeModel(String typeModel);
}