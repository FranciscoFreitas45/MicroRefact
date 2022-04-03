package main.Interface;
public interface UserRepository {

   public User findByEmail(String email);
   public Object findById(Object Object);
   public Object saveAndFlush(Object Object);
   public User findByCode(String code);
}