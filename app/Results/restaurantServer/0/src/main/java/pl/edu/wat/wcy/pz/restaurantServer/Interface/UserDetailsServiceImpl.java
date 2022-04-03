package pl.edu.wat.wcy.pz.restaurantServer.Interface;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsServiceImpl {

   public UserDetails loadUserByUsername(String s);
}