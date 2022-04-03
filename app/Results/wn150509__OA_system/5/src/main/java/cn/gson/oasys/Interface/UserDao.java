package cn.gson.oasys.Interface;
public interface UserDao {

   public Object findOne(Object Object);
   public List<User> findByDept(Dept dept);
   public Object save(Object Object);
}