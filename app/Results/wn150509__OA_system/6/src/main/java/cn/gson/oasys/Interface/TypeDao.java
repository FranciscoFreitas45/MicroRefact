package cn.gson.oasys.Interface;
public interface TypeDao {

   public List<SystemTypeList> findByTypeModel(String typeModel);
   public Object findOne(Object Object);
}