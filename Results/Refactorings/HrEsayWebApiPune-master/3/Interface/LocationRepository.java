public interface LocationRepository {

   public Object saveAndFlush(Object Object);
   public List<Location> findByDelStatus(int i);
   public List<Location> findByDelStatusAndCompIdOrderByLocIdDesc(int i,int companyId);
   public int deleteLocation(int locId);
   public Location findByLocIdAndDelStatus(int locId,int i);
   public List<Location> getLocationsByIds(List<Location> locIds);
}