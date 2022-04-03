package ink.champ.Interface;
public interface UserRepository {

   public List<User> findUserByNameContainingIgnoreCaseOrUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String search1,String search2,String search3,Sort sort);
   public Object findById(Object Object);
   public User findByUsername(String username);
   public Object save(Object Object);
   public Object delete(Object Object);
}