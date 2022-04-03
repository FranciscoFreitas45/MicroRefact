package pl.edu.wat.wcy.pz.restaurantServer.Interface;
import java.util.*;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.User;
public interface UserRepository {

   public List<User> findAll();
   public Optional<User> findById(Object Object);
   public Object save(Object Object);
   public Object deleteById(Object Object);
   public Optional<User> findByMail(String s);
}