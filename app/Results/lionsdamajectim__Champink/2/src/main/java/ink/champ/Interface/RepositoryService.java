package ink.champ.Interface;
public interface RepositoryService {

   public User getUserById(Long id);
   public void deleteUser(User user);
   public void saveUser(User user);
   public void addNewUser(User user);
}