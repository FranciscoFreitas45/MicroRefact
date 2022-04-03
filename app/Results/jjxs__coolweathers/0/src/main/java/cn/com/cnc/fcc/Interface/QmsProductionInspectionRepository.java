package cn.com.cnc.fcc.Interface;
public interface QmsProductionInspectionRepository {

   public List<QmsProductionInspection> findByBomTechnologyIdAndFlagStatus(Integer BomTechnologyId,String FlagStatus);
}