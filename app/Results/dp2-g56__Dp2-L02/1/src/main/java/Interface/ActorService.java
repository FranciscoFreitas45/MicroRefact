package Interface;
public interface ActorService {

   public Actor getActorByUsername(String a);
   public Actor save(Actor a);
   public void loggedAsActor();
   public Actor loggedActor();
}