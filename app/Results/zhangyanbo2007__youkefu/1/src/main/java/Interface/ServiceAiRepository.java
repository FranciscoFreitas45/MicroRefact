package Interface;
public interface ServiceAiRepository {

   public Page<ServiceAi> findByOrgi(String orgi,Pageable page);
}