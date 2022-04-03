package cn.gson.oasys.Interface;
public interface UserDao {

   public Page<User> findByFatherId(Long parentid,Pageable pa);
   public Object findOne(Object Object);
}