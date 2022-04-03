package Interface;
public interface AgentServiceRepository {

   public Page<AgentService> findByOrgiAndSatisfaction(String orgi,boolean satisfaction,Pageable paramPageable);
   public List<AgentService> findAll(Specification<AgentService> spec);
}