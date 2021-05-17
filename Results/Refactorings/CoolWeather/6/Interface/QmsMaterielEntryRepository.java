public interface QmsMaterielEntryRepository {

   public List<QmsMaterielEntry> findByMaterielIdAndFlagStatus(Integer materielId,String flagStatus);
}