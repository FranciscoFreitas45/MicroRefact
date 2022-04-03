package run.halo.app.Interface;
public interface UserService {

   public User createBy(UserParam userParam);
   public void setPassword(User user,String plainPassword);
   public Object update(Object Object);
   public Optional<User> getCurrentUser();
   public Object map(Object Object);
}