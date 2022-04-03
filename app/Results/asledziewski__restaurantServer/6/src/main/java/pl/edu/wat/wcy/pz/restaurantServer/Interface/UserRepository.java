package pl.edu.wat.wcy.pz.restaurantServer.Interface;
public interface UserRepository {

   public Optional<User> findByMail(String mail);
   public Boolean existsByMail(String mail);
   public Object save(Object Object);
}