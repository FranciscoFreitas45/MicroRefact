package run.halo.app.Interface;
public interface OneTimeTokenService {

   public Optional<String> get(String oneTimeToken);
   public void revoke(String oneTimeToken);
}