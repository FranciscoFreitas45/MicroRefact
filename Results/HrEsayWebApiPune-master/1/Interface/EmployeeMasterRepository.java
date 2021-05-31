public interface EmployeeMasterRepository {

   public List<EmployeeMaster> getemplistwhichisnotyearend();
   public List<EmployeeMaster> getemplistwhichisnotyearendByEmpId(int locId);
}