package cn.com.cnc.fcc.Interface;
public interface QmsMaterielRepository {

   public List<QmsMateriel> findByIdAndFlagStatus(Long valueOf,String string);
   public List<QmsMateriel> findByMaterielCdAndFlagStatus(String materielCd,String flagStatus);
}