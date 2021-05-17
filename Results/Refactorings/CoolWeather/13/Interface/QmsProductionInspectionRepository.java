public interface QmsProductionInspectionRepository {

   public List<QmsProductionInspection> findByMaterielIdAndSerialNumber(Integer s,String m);
}