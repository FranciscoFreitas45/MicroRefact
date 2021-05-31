public interface LeaveApplyRepository {

   public List<LeaveApply> getleavetListForAttndace(String fromDate,String toDate,int empId);
}