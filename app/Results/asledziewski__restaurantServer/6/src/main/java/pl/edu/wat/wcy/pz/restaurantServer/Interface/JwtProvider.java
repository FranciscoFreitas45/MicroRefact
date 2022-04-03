package pl.edu.wat.wcy.pz.restaurantServer.Interface;
public interface JwtProvider {

   public String generateJwtToken(Authentication authentication);
}