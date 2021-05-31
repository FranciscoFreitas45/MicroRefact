public interface EmployeeMasterRepository {

   public int empEmpShiftGroupUpdate(List<Integer> empIdList,String upDateId);
   public int empEmpCategoryUpdate(List<Integer> empIdList,String upDateId);
   public int empSkillUpdate(List<Integer> empIdList,String skillId);
   public int weekHoliCat(List<Integer> empIdList,String holiCatId);
   public int assignShift(List<Integer> empIdList,String shiftId);
   public int assignLocation(List<Integer> empIdList,String locId);
   public int assignEmpType(List<Integer> empIdList,String typeId);
   public int assignDesignation(List<Integer> empIdList,String desnId);
   public int assignDept(List<Integer> empIdList,String deptId);
   public int assignComapny(List<Integer> empIdList,String compId);
   public int assignHoliCat(List<Integer> empIdList,String holiCatId);
   public List<EmployeeMaster> getEmpSalAssign();
   public List<EmployeeMaster> findByEmpTypeAndDelStatus(int empTypeId,int i);
}