package Interface;
public interface AgentServiceRepository {

   public AgentService findByIdAndOrgi(String paramString,String orgi);
   public Object save(Object Object);
}