package run.halo.app.Interface;
public interface UserService {

   public Optional<User> getByEmail(String email);
}