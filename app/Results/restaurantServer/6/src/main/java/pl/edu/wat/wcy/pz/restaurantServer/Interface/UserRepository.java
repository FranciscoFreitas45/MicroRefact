package pl.edu.wat.wcy.pz.restaurantServer.Interface;
import java.util.*;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.User;
public interface UserRepository {

   public Optional<User> findByMail(String mail);
   public Boolean existsByMail(String mail);
   public Object save(Object Object);
}