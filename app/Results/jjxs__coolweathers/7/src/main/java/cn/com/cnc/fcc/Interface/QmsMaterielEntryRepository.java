package cn.com.cnc.fcc.Interface;
public interface QmsMaterielEntryRepository {

   public List<QmsMaterielEntry> findByMaterielIdAndFlagStatus(Integer materielId,String flagStatus);
}