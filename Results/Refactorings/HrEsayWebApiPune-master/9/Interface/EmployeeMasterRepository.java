public interface EmployeeMasterRepository {

   public int getEmpInfoByDesigId(int desigId,int companyId);
   public int getEmpInfoByContractId(int contractId,int companyId);
   public int getEmpInfoByDepartment(int deptId,int companyId);
}