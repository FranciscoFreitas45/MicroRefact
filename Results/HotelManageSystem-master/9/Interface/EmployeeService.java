public interface EmployeeService {

   public Employee findByEmpNameAndEmpNo(String empName,String empNo);
   public Employee findByEmpName(String empName);
   public Employee findByUserName(String userName);
}