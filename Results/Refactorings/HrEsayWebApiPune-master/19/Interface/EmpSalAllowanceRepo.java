public interface EmpSalAllowanceRepo {

   public List<EmpSalAllowance> findByDelStatus(int i);
   public List<EmpSalAllowance> findByDelStatusAndEmpId(int delStatus,List<Integer> empIds);
}