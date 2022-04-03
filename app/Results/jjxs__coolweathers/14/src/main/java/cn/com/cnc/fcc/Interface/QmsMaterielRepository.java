package cn.com.cnc.fcc.Interface;
public interface QmsMaterielRepository {

   public List<QmsMateriel> findByUseUnitId(Integer s);
   public List<QmsMateriel> findByPackgeUnitId(Integer s);
}