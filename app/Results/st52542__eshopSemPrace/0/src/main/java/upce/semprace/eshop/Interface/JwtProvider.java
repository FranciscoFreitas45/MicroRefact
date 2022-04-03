package upce.semprace.eshop.Interface;
public interface JwtProvider {

   public String generateJwtToken(Authentication authentication);
}