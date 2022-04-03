package cn.gson.oasys.Interface;
public interface DaymanageDao {

   public Page<ScheduleList> findByUser(User user,Pageable pa);
   public Page<ScheduleList> findByUsers(List<User> users,Pageable pa);
}