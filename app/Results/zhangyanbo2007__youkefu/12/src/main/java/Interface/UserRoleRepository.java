package Interface;
public interface UserRoleRepository {

   public List<UserRole> findByOrgiAndUser(String orgi,User user);
}