package Interface;
public interface UserRepository {

   public List<User> findAll(Specification<User> spec);
}