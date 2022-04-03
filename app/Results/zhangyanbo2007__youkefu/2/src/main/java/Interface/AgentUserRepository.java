package Interface;
public interface AgentUserRepository {

   public AgentUser findOneByAgentnoAndStatusAndOrgi(String id,String status,String orgi);
}