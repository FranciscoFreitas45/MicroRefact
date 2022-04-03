package repositories;
 import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Position;
@Repository
public interface PositionRepository extends JpaRepository<Position, Integer>{


@Query("select p from Position p")
public Collection<Position> getPositions()
;

@Query("select distinct(p) from Enrolment e join e.position p")
public Collection<Position> getUsedPositions()
;

}