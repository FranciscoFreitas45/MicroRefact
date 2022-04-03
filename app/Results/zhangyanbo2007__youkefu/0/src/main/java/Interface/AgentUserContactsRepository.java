package Interface;
public interface AgentUserContactsRepository {

   public List<AgentUserContacts> findByUseridAndOrgi(String userid,String orgi);
   public Object save(Object Object);
}