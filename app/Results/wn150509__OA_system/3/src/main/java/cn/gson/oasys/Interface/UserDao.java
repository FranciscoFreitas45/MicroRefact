package cn.gson.oasys.Interface;
public interface UserDao {

   public Object findOne(Object Object);
   public User findid(String name);
   public Page<User> findbyUserNameLike(String name,Pageable pa);
   public Object findAll(Object Object);
   public Page<User> findbyFatherId(String name,Long parentid,Pageable pa);
   public Page<User> findByFatherId(Long parentid,Pageable pa);
}