public interface LeaveApplyRepository {

   public List<LeaveApply> findByCalYrIdAndDelStatusAndEmpId(int calYr,int i,int empId);
}