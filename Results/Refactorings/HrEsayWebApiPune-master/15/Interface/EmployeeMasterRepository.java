public interface EmployeeMasterRepository {

   public List<EmployeeMaster> findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc(int del,int companyId,int subCompId);
}