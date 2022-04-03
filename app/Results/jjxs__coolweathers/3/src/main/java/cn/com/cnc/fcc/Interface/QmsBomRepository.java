package cn.com.cnc.fcc.Interface;
public interface QmsBomRepository {

   public List<QmsBom> findByVehicleIdAndFlagStatus(Integer vehicleType,String flagStatus);
}