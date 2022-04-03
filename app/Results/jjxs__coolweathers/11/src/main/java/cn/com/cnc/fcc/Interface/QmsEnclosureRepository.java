package cn.com.cnc.fcc.Interface;
public interface QmsEnclosureRepository {

   public List<QmsEnclosure> findAllByInspectionInfoIdAndInspectionKbn(Integer inspectionInfoId,String inpectionKbn);
}