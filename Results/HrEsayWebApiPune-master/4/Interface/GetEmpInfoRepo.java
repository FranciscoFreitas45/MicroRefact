public interface GetEmpInfoRepo {

   public List<GetEmployeeInfo> getEmpListByCompanyId(int companyId);
   public List<GetEmployeeInfo> getEmpIdListByCompanyId(int companyId,List<GetEmployeeAuthorityWise> empIdList);
   public List<GetEmployeeInfo> getEmpListByCompanyIdForAuth(int companyId,List<Integer> locIdList);
   public List<GetEmployeeInfo> getEmpListByCompanyIdForAuthClaim(int companyId,List<Integer> locIdList);
   public List<GetEmployeeInfo> getEmpIdListByCompanyIdForClaim(int companyId,List<GetEmployeeAuthorityWise> empIdList);
   public GetEmployeeInfo getEmpByEmpId(int empId);
}