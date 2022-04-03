package cn.gson.oasys.Interface;
public interface UserDao {

   public Object findOne(Object Object);
   public Page<User> findByFatherId(Long parentid,Pageable pa);
}