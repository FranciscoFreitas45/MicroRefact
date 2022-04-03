package Interface;
public interface PersistentTokenRepository {

   public Object delete(Object Object);
   public List<PersistentToken> findByTokenDateBefore(LocalDate localDate);
   public List<PersistentToken> findByUser(User user);
}