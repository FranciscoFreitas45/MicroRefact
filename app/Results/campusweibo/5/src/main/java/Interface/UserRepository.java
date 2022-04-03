package Interface;
public interface UserRepository {

   public Optional<User> findOneByLogin(String login);
}