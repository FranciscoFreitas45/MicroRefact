package Interface;
public interface UserRepository {

   public List<User> findByOrgiAndAgentAndDatastatus(String orgi,boolean agent,boolean status);
   public List<User> findByOrgidAndAgentAndDatastatus(String orgid,boolean agent,boolean datastatus);
}