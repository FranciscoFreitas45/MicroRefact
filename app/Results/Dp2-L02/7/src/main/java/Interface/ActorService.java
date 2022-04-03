package Interface;
public interface ActorService {

   public void loggedAsActor();
   public Actor getActorByUsername(String a);
   public Actor save(Actor a);
   public Actor loggedActor();
}