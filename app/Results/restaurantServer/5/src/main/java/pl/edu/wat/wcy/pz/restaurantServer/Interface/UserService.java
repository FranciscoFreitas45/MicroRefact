package pl.edu.wat.wcy.pz.restaurantServer.Interface;
import java.util.*;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.User;

public interface UserService {

   public Optional<User> getUserById(Long id);
}