package pl.szymanski.sharelibrary.Interface;
public interface JwtTokenProvider {

   public String generateToken(Authentication authentication);
}