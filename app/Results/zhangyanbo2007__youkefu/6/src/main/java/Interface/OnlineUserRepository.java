package Interface;
public interface OnlineUserRepository {

   public List<Object> findByOrgiAndStatus(String orgi,String status);
   public List<Object> findByOrgiAndAgentnoAndCreatetimeRange(String orgi,String agentno,Date start,Date end);
   public Long countByAgentForAgentUser(String orgi,String status,String agentno,Date start,Date end);
   public Long countByAgentForAvagTime(String orgi,String status,String agentno,Date start,Date end);
   public List<Object> findByOrgiAndCreatetimeRange(String orgi,String model,Date start,Date end);
   public List<Object> findByOrgiAndCreatetimeRangeForAgent(String orgi,Date start,Date end);
   public List<Object> findByOrgiAndCreatetimeRangeForClient(String orgi,Date start,Date end,String channel);
   public List<Object> findByOrgiAndCreatetimeRangeForBrowser(String orgi,Date start,Date end,String channel);
}