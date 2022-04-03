package Interface;
public interface UserRepository {

   public Page<User> findByIdAndOrgi(String id,String orgi,Pageable paramPageable);
   public List<User> findAll(Specification<User> spec);
}