public interface EmployeeMasterRepository {

   public List<EmployeeMaster> findByLocationIdAndDelStatus(int locId,int i);
}