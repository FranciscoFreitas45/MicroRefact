package cn.com.cnc.fcc.Interface;
public interface QmsMaterielRepository {

   public Optional<QmsMateriel> findQmsMaterielByMaterielCdAndFlagStatus(String materielCd,String flagStatus);
}