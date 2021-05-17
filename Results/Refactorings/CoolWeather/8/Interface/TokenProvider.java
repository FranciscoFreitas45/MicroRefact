public interface TokenProvider {

   public String createToken(Authentication authentication,boolean rememberMe);
}