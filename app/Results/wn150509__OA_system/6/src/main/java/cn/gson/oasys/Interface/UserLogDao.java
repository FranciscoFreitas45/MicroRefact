package cn.gson.oasys.Interface;
public interface UserLogDao {

   public List<UserLog> findByUser(long userid);
}