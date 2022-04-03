package pl.edu.wat.wcy.pz.restaurantServer.Interface;
import org.springframework.security.core.Authentication;
public interface JwtProvider {

   public String generateJwtToken(Authentication authentication);
}