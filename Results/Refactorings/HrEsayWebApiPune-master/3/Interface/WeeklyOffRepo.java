public interface WeeklyOffRepo {

   public List<WeeklyOff> findByExInt1AndDelStatus(int hoCatId,int i);
   public List<EmployeeMaster> findByExInt2AndDelStatus(int skillId,int i);
}