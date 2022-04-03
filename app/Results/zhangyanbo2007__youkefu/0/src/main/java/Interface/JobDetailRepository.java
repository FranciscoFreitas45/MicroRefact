package Interface;
public interface JobDetailRepository {

   public Page<JobDetail> findByTaskstatus(String taskstatus,Pageable page);
   public Page<JobDetail> findByPlantaskAndTaskstatusAndNextfiretimeLessThan(boolean plantask,String taskstatus,Date time,Pageable page);
   public Object save(Object Object);
}