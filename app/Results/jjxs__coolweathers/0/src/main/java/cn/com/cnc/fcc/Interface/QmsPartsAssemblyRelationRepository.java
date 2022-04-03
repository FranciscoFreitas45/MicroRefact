package cn.com.cnc.fcc.Interface;
public interface QmsPartsAssemblyRelationRepository {

   public Object saveAll(Object Object);
   public Object save(Object Object);
   public QmsPartsAssemblyRelation findByIdAndFlagStatus(Long id,String string);
   public List<QmsPartsAssemblyRelation> findAllByFlagStatusAndBomTechnologyId(String FlagStatus,Integer bomTechnologyId);
}