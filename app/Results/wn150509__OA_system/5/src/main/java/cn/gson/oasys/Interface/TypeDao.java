package cn.gson.oasys.Interface;
public interface TypeDao {

   public SystemTypeList findByTypeModelAndTypeName(String typeModel,String typeName);
   public List<SystemTypeList> findByTypeModel(String typeModel);
}