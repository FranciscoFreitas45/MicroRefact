public interface QmsBomRepository {

   public List<QmsBom> findByVehicleIdAndFlagStatus(Integer vehicleType,String flagStatus);
}