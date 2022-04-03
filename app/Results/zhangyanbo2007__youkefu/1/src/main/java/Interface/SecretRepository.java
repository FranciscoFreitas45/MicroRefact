package Interface;
public interface SecretRepository {

   public List<Secret> findByOrgi(String orgi);
}