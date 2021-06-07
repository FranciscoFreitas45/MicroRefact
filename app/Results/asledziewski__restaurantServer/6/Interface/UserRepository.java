public interface UserRepository {

   public Optional<User> findByMail(String mail);
   public Boolean existsByMail(String mail);
   public Object save(Object Object);
}