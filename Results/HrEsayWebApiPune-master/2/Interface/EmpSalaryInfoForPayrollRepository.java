public interface EmpSalaryInfoForPayrollRepository {

   public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendance(int month,int year,int isFixed,String sts,List<Integer> locId,int typeId,int deptId);
   public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendanceDeptId(int month,int year,int isFixed,String sts,List<Integer> locId,int deptId);
   public List<EmpSalaryInfoForPayroll> getListForfixunfixAttendanceTypeId(int month,int year,int isFixed,String sts,List<Integer> locId,int typeId);
}