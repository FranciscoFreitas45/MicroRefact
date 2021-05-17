public interface EmployeeMasterRepository {

   public EmployeeMaster findByEmpIdAndDelStatus(int empId,int del);
}