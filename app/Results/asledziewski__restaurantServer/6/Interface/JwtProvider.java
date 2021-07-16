public interface JwtProvider {

   public String generateJwtToken(Authentication authentication);
}