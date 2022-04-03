package main.Interface;
public interface UserRepository {

   public Object getOne(Object Object);
   public Object findById(Object Object);
   public Object saveAndFlush(Object Object);
   public int isAdmin(int id);
   public User findByEmail(String email);
}