package Interface;
public interface SystemMessageRepository {

   public List<SystemMessage> findByOrgi(String orgi);
}