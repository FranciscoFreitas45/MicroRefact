package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import domain.Parade;
import domain.Segment;
public interface SegmentRepository extends JpaRepository<Segment, Integer>{


@Query("select p from Parade p where p.path.id= ?1")
public Parade getParadeByPath(Integer pathId)
;

}