package cn.com.cnc.fcc.Interface;
public interface QmsProductionInspectionRepository {

   public List<QmsProductionInspection> findByMaterielIdAndSerialNumber(Integer s,String m);
}