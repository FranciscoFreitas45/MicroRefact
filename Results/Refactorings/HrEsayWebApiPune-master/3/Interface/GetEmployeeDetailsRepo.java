public interface GetEmployeeDetailsRepo {

   public List<GetEmployeeDetails> getEmplistForAssignAuthority();
   public List<GetEmployeeDetails> getEmpListByCompanyIdForAuth();
   public List<GetEmployeeDetails> getEmpInfoListForLeaveAuthLocId(List<Integer> locId);
   public List<GetEmployeeDetails> getEmpListByCompanyIdAndEmpIdList(List<Integer> empIdList);
   public List<GetEmployeeDetails> getAuthorityWiseEmpListByEmpId(int empId);
   public List<GetEmployeeDetails> getEmpListForClaimAuthByEmpId(int empId);
   public List<GetEmployeeDetails> getAuthorityWiseEmpListByEmpIdForApp(int empId);
}