public interface ShiftAssignDailyRepository {

   public int updateAssignShiftByDate(List<Integer> empIdList,String fromDate,String toDate,int shiftId);
}