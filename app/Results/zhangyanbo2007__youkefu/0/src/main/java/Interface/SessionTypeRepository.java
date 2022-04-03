package Interface;
public interface SessionTypeRepository {

   public List<SessionType> findByOrgiAndCtype(String orgi,String ctype);
   public SessionType findById(String id);
}