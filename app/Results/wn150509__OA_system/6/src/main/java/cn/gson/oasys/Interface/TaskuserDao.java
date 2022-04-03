package cn.gson.oasys.Interface;
public interface TaskuserDao {

   public List<Taskuser> findByUserIdAndStatusId(User user,Integer id);
}