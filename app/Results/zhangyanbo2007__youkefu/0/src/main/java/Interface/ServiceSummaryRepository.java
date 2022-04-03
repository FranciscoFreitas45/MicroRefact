package Interface;
public interface ServiceSummaryRepository {

   public Page<AgentServiceSummary> findByOrgiAndUserid(String orgi,String userid,Pageable pageable);
   public Object save(Object Object);
   public AgentServiceSummary findByIdAndOrgi(String id,String orgi);
}