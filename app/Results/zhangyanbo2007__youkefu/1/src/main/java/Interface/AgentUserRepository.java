package Interface;
public interface AgentUserRepository {

   public List<AgentUser> findByAgentnoAndOrgi(String agentno,String orgi,Sort sort);
}