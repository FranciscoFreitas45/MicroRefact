package repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Actor;
import domain.Box;
@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{


@Query("select a from Actor a")
public List<Actor> getActors()
;

@Query("select c.boxes from Actor c where c = ?1")
public List<Box> listOfBoxes(Actor actor)
;

@Query("select a from Actor a join a.userAccount b where b.username = ?1")
public Actor getActorByUserName(String a)
;

}