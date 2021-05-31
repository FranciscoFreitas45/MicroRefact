public interface EmpSalaryInfoRepo {

   public EmpSalaryInfo findByEmpIdAndDelStatus(int empId,int del);
}