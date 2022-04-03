package Interface;
public interface ActorService {

   public Actor getActorByUsername(String a);
   public List<Actor> findAll();
   public Actor save(Actor a);
}