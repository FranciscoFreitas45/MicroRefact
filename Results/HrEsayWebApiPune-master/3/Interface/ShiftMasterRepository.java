public interface ShiftMasterRepository {

   public List<ShiftMaster> showShiftListByLocationIds(List<Integer> locationIds);
   public List<ShiftMaster> getShiftListByLpad();
   public List<ShiftMaster> getShiftListByLpadForShiftAllocation();
   public int deleteShiftTime(int shiftId);
   public List<ShiftMaster> findBySelfGroupIdAndStatus(int bonusId,int i);
   public Object save(Object Object);
   public List<ShiftMaster> getShiftListByGroupIdandlocId(int groupId);
}