public interface QmsVehicleTypeInfoRepository {

   public List<QmsVehicleTypeInfo> findByFlagStatusAndVehicleType(String string,String string2);
   public QmsVehicleTypeInfo findByVehicleTypeAndFlagStatus(String vehicleType,String flagStatus);
}